package com.idm.pj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.Logistic;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

@Service
public class Cross10FoldsService {

    @Autowired
    LoadDataService loadDataService;

    @Autowired
    EvaluateService evaluateService;

    public String nB10Folds() throws Exception {
        Instances data = loadDataService.loadTrainSet();
        NaiveBayes nb = new NaiveBayes();
        nb.buildClassifier(data);
        return evaluateService.evaluate(nb,data,10,1);
    }

    public String j4810Folds() throws Exception {
        Instances data = loadDataService.loadTrainSet();
        J48 j48 = new J48();
        j48.buildClassifier(data);
        return evaluateService.evaluate(j48,data,10,1);
    }

    public String lR10Folds() throws Exception {
        Instances data = loadDataService.loadTrainSet();
        Logistic lr = new Logistic();
        lr.buildClassifier(data);
        return evaluateService.evaluate(lr,data,10,1);
    }

    public String rF10Folds() throws Exception {
        Instances data = loadDataService.loadTrainSet();
        RandomForest rf = new RandomForest();
        rf.buildClassifier(data);
        return evaluateService.evaluate(rf,data,10,1);
    }

}
