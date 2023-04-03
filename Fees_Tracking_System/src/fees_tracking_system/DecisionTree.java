/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fees_tracking_system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;

public class DecisionTree {
    public static void main(String[] args) {
        try {
            // Load the dataset
            BufferedReader reader = new BufferedReader(new FileReader("fees_details.arff"));
            Instances data = new Instances(reader);
            reader.close();

            // Set the class index to the last attribute
            data.setClassIndex(data.numAttributes() - 1);

            // Build the decision tree model
            J48 tree = new J48();
            tree.buildClassifier(data);

            // Print the decision tree
            System.out.println(tree);

            // Evaluate the model using cross-validation
            Evaluation eval = new Evaluation(data);
            eval.crossValidateModel(tree, data, 10, new Random(1));
            System.out.println(eval.toSummaryString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}