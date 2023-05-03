package com.idm.pj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.Logistic;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

import java.util.List;
import java.util.Random;

@Service
public class ClassificationService {

    @Autowired
    LoadDataService loadDataService;

    @Autowired
    EvaluateService evaluateService;

    public String naiveBayes() throws Exception {
        Instances trainSet = loadDataService.loadTrainSet();
        Instances testSet = loadDataService.loadTestSet();
        NaiveBayes nb = new NaiveBayes();
        nb.buildClassifier(trainSet);
        return evaluateService.evaluteTest(trainSet,testSet,nb);
    }

    public String evaluteTest(Instances data, Instances testData, Classifier cls) throws Exception {
        Evaluation eval = new Evaluation(data);
        eval.evaluateModel(cls,testData);
        return eval.toSummaryString();
    }

    public String j48Classi() throws Exception{
        Instances trainSet = loadDataService.loadTrainSet();
        Instances testSet = loadDataService.loadTestSet();
        J48 j48 = new J48();
        j48.buildClassifier(trainSet);
        return evaluateService.evaluteTest(trainSet,testSet,j48);
    }

    public String rFClassi() throws Exception{
        Instances trainSet = loadDataService.loadTrainSet();
        Instances testSet = loadDataService.loadTestSet();
        RandomForest randomForest = new RandomForest();
        randomForest.buildClassifier(trainSet);
        return evaluateService.evaluteTest(trainSet,testSet,randomForest);
    }

    public String lRClassi() throws Exception{
        Instances trainSet = loadDataService.loadTrainSet();
        Instances testSet = loadDataService.loadTestSet();
        Logistic logistic = new Logistic();
        logistic.buildClassifier(trainSet);
        return evaluateService.evaluteTest(trainSet,testSet,logistic);
    }

}
