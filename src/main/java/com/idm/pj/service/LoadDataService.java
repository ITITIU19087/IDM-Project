package com.idm.pj.service;

import org.springframework.stereotype.Service;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.Randomize;
import weka.filters.unsupervised.instance.RemovePercentage;

import java.io.File;
import java.io.IOException;
import java.util.Random;

@Service
public class LoadDataService {
    public void convertToArff(String source, String des) throws IOException {
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(source));
        Instances data = loader.getDataSet();

        ArffSaver saver = new ArffSaver();
        saver.setInstances(data);
        saver.setFile(new File(des));

        saver.writeBatch();
    }

    public Instances loadDataSource() throws Exception {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource("src/main/resources/cleaned.arff");
        Instances dataset = source.getDataSet();
        dataset.setClassIndex(dataset.numAttributes()-1);
        return dataset;
    }

    public void splitDataToTest() throws Exception {
        Instances data = loadDataSource();
        Randomize randomizeFilter = new Randomize();
        randomizeFilter.setInputFormat(data);
        Instances randomizedData = Filter.useFilter(data, randomizeFilter);

        int trainSize = (int) Math.round(randomizedData.numInstances() * 0.82);
        int testSize = randomizedData.numInstances() - trainSize;
        Instances trainSet = new Instances(randomizedData, 0, trainSize);
        Instances testSet = new Instances(randomizedData, trainSize, testSize);

        System.out.println("Number of instances in training set: " + trainSet.numInstances());
        System.out.println("Number of instances in test set: " + testSet.numInstances());

        weka.core.converters.CSVSaver saver = new weka.core.converters.CSVSaver();
        saver.setInstances(testSet);
        saver.setFile(new File("src/main/resources/test.csv"));
        saver.writeBatch();

        saver.setInstances(trainSet);
        saver.setFile(new File("src/main/resources/train.csv"));
        saver.writeBatch();
    }

    public Instances loadTrainSet() throws Exception{
        ConverterUtils.DataSource source = new ConverterUtils.DataSource("src/main/resources/train.arff");
        Instances dataset = source.getDataSet();
        dataset.setClassIndex(dataset.numAttributes()-1);
        return dataset;
    }

    public Instances loadTestSet() throws Exception{
        ConverterUtils.DataSource source = new ConverterUtils.DataSource("src/main/resources/test.arff");
        Instances dataset = source.getDataSet();
        dataset.setClassIndex(dataset.numAttributes()-1);
        return dataset;
    }
}
