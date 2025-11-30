package com.phishingdetector.controller;

import com.phishingdetector.model.UrlRequest;
import com.phishingdetector.model.PredictionResponse;
import com.phishingdetector.service.ModelService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PredictionController {

    private final ModelService modelService;

    public PredictionController(ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping("/predict")
    public PredictionResponse predict(@RequestBody UrlRequest req) {
        return modelService.predict(req.getUrl());
    }
}
