package com.idm.pj.service;

import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class EvaluateService {
    public String evaluate(Classifier classifier, Instances data, int folds, int random) throws Exception {
        List<String> list = new ArrayList<>();
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(classifier,data,folds, new Random(random));
        list.add(eval.toSummaryString());
        list.add(eval.toClassDetailsString());
        list.add(eval.toMatrixString());
        return list.toString();
    }

    public String evaluteTest(Instances data, Instances testData, Classifier cls) throws Exception {
        Evaluation eval = new Evaluation(data);
        eval.evaluateModel(cls,testData);
        return eval.toSummaryString();
    }
}
