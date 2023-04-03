/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fees_tracking_system;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import weka.core.Instances;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;

public class AddFeesArff {
    public Instances createArffFile() throws Exception {
        // Connect to the database
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/fees_management","root","1234");

        // Query the database for the fee details
        String query = "SELECT fee_name, fee_name1, fee_name2, amount, amount1, amount2, total_amount FROM fees_details";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        // Create the attributes for the instances
        FastVector attributes = new FastVector();
        Attribute fee_name = new Attribute("fee_name", (FastVector) null);
        Attribute fee_name1 = new Attribute("fee_name1", (FastVector) null);
        Attribute fee_name2 = new Attribute("fee_name2", (FastVector) null);
        Attribute amount = new Attribute("amount");
        Attribute amount1 = new Attribute("amount1");
        Attribute amount2 = new Attribute("amount2");
        Attribute total = new Attribute("total_amount");
        attributes.addElement(fee_name);
        attributes.addElement(fee_name1);
        attributes.addElement(fee_name2);
        attributes.addElement(amount);
        attributes.addElement(amount1);
        attributes.addElement(amount2);
        attributes.addElement(total);

        // Create the instances and add them to the dataset
        Instances data = new Instances("fees_details", attributes, 0);
        while (rs.next()) {
            double[] values = new double[data.numAttributes()];
            values[0] = data.attribute(0).addStringValue(rs.getString("fee_name"));
            values[1] = data.attribute(1).addStringValue(rs.getString("fee_name1"));
            values[2] = data.attribute(2).addStringValue(rs.getString("fee_name2"));
            values[3] = rs.getDouble("amount");
            values[4] = rs.getDouble("amount1");
            values[5] = rs.getDouble("amount2");
            values[6] = rs.getDouble("total_amount");
            data.add(new DenseInstance(1.0, values));
        }

        // Write the instances to the ARFF file
        try {
            FileWriter writer = new FileWriter("fees_details.arff");
            writer.write(data.toString());
            writer.flush();
            writer.close();
            System.out.println("Data written to fees_details.arff file");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return the instances
        return data;
    }
}