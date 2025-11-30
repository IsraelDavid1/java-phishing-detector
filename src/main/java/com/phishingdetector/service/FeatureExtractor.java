package com.phishingdetector.service;

import com.phishingdetector.utils.UrlUtils;

public class FeatureExtractor {

    public double[] extractFeatures(String url) {
        double[] features = new double[7];

        String host = UrlUtils.extractHost(url);
        features[0] = url.length();
        features[1] = countChar(url, '/');
        features[2] = url.contains("@") ? 1.0 : 0.0;
        features[3] = UrlUtils.isHttps(url) ? 1.0 : 0.0;
        features[4] = host.contains("-") ? 1.0 : 0.0;
        features[5] = UrlUtils.hasIP(host) ? 1.0 : 0.0;
        features[6] = countSubdomains(host);

        return features;
    }

    private int countChar(String s, char c) {
        if (s == null) return 0;
        int count = 0;
        for (char ch : s.toCharArray()) if (ch == c) count++;
        return count;
    }

    private int countSubdomains(String host) {
        if (host == null || host.isEmpty()) return 0;
        String[] parts = host.split("\\.");
        return Math.max(0, parts.length - 2);
    }
}
