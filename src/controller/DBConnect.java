/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class DBConnect {
    
    public static Connection connectDB(){
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\MAHBUB\\Documents\\testDB.sqlite");
//            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\hridoy hossain\\Documents\\testDB.sqlite");
//            JOptionPane.showMessageDialog(null, "Connection established");
            return connection;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
        }
        return  connection;
    }
    
}
