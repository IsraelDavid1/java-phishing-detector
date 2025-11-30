package com.phishingdetector.utils;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvLoader {
    public static List<String[]> readCsv(String path) throws Exception {
        List<String[]> list = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                list.add(line);
            }
        }
        return list;
    }
}