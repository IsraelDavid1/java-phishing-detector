package com.phishingdetector.ml;


public class TrainModelMain {
    public static void main(String[] args) throws Exception {
        String csvPath = "src/main/java/com/phishingdetector/data/urls.csv";
        String modelOut = "src/main/resources/model/phishing-model.bin";

        ModelTrainer Trainer = new ModelTrainer();

        Trainer.trainAndSave(csvPath, modelOut);

        System.out.println("training finished!");
    }
}
