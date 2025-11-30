package com.phishingdetector.model;

public class FeatureVector {
    private double[] features;

    public FeatureVector(double[] features) { this.features = features; }
    public double[] getFeatures() { return features; }
    public void setFeatures(double[] features) { this.features = features; }
}
