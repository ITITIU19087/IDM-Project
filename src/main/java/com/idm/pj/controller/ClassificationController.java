package com.idm.pj.controller;

import com.idm.pj.service.ClassificationService;
import com.idm.pj.service.EvaluateService;
import com.idm.pj.service.LoadDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

import java.io.IOException;
import java.util.List;

@RestController
public class ClassificationController {

    @Autowired
    ClassificationService service;

    @Autowired
    EvaluateService evaluateService;

    @Autowired
    LoadDataService loadDataService;

    @GetMapping("/nbclassi")
    public String naiveBayes10Classify() throws Exception {
        return service.naiveBayes();
    }

    @GetMapping("/test10folds")
    public void j4810Classify() throws Exception {
        loadDataService.loadTrainSet();
        loadDataService.loadTestSet();
    }
    @GetMapping("/j48classi")
    public String j48Classify() throws Exception {
        return service.j48Classi();
    }

    @GetMapping("/rfclassi")
    public String rFClassify() throws Exception {
        return service.rFClassi();
    }

    @GetMapping("/lrclassi")
    public String lRClassify() throws Exception {
        return service.lRClassi();
    }

    @GetMapping("/b10folds")
    public void getDataTest() throws Exception {
        loadDataService.splitDataToTest();
    }

    @GetMapping("/convert")
    public void convertToArff() throws IOException {
        String source = "src/main/resources/train.csv";
        String des = "src/main/resources/train.arff";
        String source1 = "src/main/resources/test.csv";
        String des1 = "src/main/resources/test.arff";
        loadDataService.convertToArff(source,des);
        loadDataService.convertToArff(source1,des1);
    }
}
