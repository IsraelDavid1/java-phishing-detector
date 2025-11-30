package com.phishingdetector;
import com.phishingdetector.service.ModelService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class PhishingDetectorApplication {

    private final ModelService modelService;

    public PhishingDetectorApplication(ModelService modelService) {
        this.modelService = modelService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PhishingDetectorApplication.class, args);
    }

    @PostConstruct
    public void init() {
        try {
            modelService.loadModel("src/main/resources/model/phishing-model.bin");
            System.out.println("Model loaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error when loading models: " + e.getMessage());
        }
    }
}
