package com.phishingdetector.utils;

import java.net.URI;

public class UrlUtils {

    public static String extractHost(String url) {
        try {
            URI uri = new URI(url.trim());

            String host = uri.getHost();
            if (host == null) {
                // tenta corrigir URLs sem esquema
                uri = new URI("http://" + url.trim());
                host = uri.getHost();
            }

            return host != null ? host.toLowerCase() : "";
        } catch (Exception e) {
            return "";
        }
    }

    public static boolean isHttps(String url) {
        try {
            URI uri = new URI(url.trim());
            return "https".equalsIgnoreCase(uri.getScheme());
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean hasIP(String host) {
        if (host == null) return false;
        return host.matches("^\\d+\\.\\d+\\.\\d+\\.\\d+$");
    }
}
