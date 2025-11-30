package com.phishingdetector.model;

public class PredictionResponse {
    private String result;
    private double confidence;

    public PredictionResponse() {}

    public PredictionResponse(String result, double confidence) {
        this.result = result;
        this.confidence = confidence;
    }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public double getConfidence() { return confidence; }
    public void setConfidence(double confidence) { this.confidence = confidence; }
}
