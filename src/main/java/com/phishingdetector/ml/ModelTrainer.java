package com.phishingdetector.ml;

import com.phishingdetector.service.FeatureExtractor;
import com.phishingdetector.utils.CsvLoader;

import smile.classification.RandomForest;
import smile.data.DataFrame;
import smile.data.formula.Formula;
import smile.data.Tuple;
import smile.data.type.DataTypes;
import smile.data.type.StructField;
import smile.data.type.StructType;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ModelTrainer {

    public void trainAndSave(String csvPath, String modelOut) throws Exception {

        List<String[]> rows = CsvLoader.readCsv(csvPath);
        rows.remove(0);

        FeatureExtractor fe = new FeatureExtractor();

        List<double[]> featureList = new ArrayList<>();
        List<Integer> labels = new ArrayList<>();

        for (String[] row : rows) {
            featureList.add(fe.extractFeatures(row[0]));
            labels.add(Integer.parseInt(row[1]));
        }

        int numFeatures = featureList.get(0).length;
        String[] featureNames = new String[numFeatures];
        for (int i = 0; i < numFeatures; i++) {
            featureNames[i] = "f" + i;
        }

        List<StructField> fields = new ArrayList<>();
        for (String name : featureNames) {
            fields.add(new StructField(name, DataTypes.DoubleType));
        }
        fields.add(new StructField("label", DataTypes.IntegerType));
        StructType schema = DataTypes.struct(fields);
        
        List<Tuple> tuples = new ArrayList<>();
        for (int i = 0; i < featureList.size(); i++) {
            Object[] rowValues = new Object[numFeatures + 1];

            for (int f = 0; f < numFeatures; f++) {
                rowValues[f] = featureList.get(i)[f];
            }

            rowValues[numFeatures] = labels.get(i);
            tuples.add(Tuple.of(rowValues, schema));
        }

        DataFrame data = DataFrame.of(tuples, schema);

        RandomForest model = RandomForest.fit(
            Formula.lhs("label"),
            data
        );

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(modelOut))) {
            oos.writeObject(model);
        }

        System.out.println("Model saved to: " + modelOut);
    }
}
