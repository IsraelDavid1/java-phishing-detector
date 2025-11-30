package com.phishingdetector.service;

import com.phishingdetector.model.PredictionResponse;
import org.springframework.stereotype.Service;
import smile.classification.RandomForest;
import smile.data.Tuple;
import smile.data.type.DataTypes;
import smile.data.type.StructField;
import smile.data.type.StructType;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ModelService {

    private RandomForest model;
    private FeatureExtractor fe = new FeatureExtractor();

    private String[] featureNames;
    private StructType schema;

    public void loadModel(String path) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            model = (RandomForest) ois.readObject();
        }
        System.out.println("model loaded");
    }

    public PredictionResponse predict(String url) {
        double[] features = fe.extractFeatures(url);

        if (featureNames == null) {
            featureNames = new String[features.length + 1];
            for (int i = 0; i < features.length; i++) {
                if (i == features.length - 1) {
                    featureNames[i + 1] = "label";
                    featureNames[i] = "f" + i;
                } else {
                    featureNames[i] = "f" + i;
                }                
            }
        }

        if (schema == null) {
            List<StructField> fields = new ArrayList<>();
            for (String name : featureNames) {
                fields.add(new StructField(name, DataTypes.DoubleType));
            }
            schema = DataTypes.struct(fields);
        }

        Tuple t = Tuple.of(features, schema);

        int result = model.predict(t);

        return new PredictionResponse(
                result == 1 ? "phishing" : "legit",
                0.0 // to implement later
        );
    }
}
