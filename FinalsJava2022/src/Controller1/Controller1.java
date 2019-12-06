///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Controller1;
//import java.awt.HeadlessException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import javax.swing.JOptionPane;
//import Model1.Model1;
///**
// *
// * @author mariscalyu_SD2022
// */
//public class Controller1 {
//     Model1 acc = new Model1();
//
//    public boolean registerVerification(String username, String password, String confirmPassword, String age1, String money1) {
//        boolean success = false;
//        if (username.length() >= 5) {
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jframe", "root", "");
//                Statement stmt = con.createStatement();
//                ResultSet rs = stmt.executeQuery("SELECT * FROM `users` WHERE username='" + username + "'");
//                if (rs.next()) {
//                    JOptionPane.showMessageDialog(null, "Username is already taken!");
//                } else {
//                    if (password.equals(confirmPassword)) {
//                        try {
//                            int age = Integer.parseInt(age1);
//                            if (age >= 18) {
//                                try {
//                                    double money = Double.parseDouble(money1);
//
//                                    success = acc.register(username, password, age, money);
//                                    return success;
//
//                                } catch (NumberFormatException e) {
//                                    JOptionPane.showMessageDialog(null, "Money should be number!");
//                                }
//                            } else {
//                                JOptionPane.showMessageDialog(null, "Age should be legal!");
//                            }
//                        } catch (NumberFormatException e) {
//                            JOptionPane.showMessageDialog(null, "Age should be number!");
//                        }
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Password do not match!");
//                    }
//                }
//            } catch (HeadlessException | ClassNotFoundException | SQLException e) {
//                JOptionPane.showMessageDialog(null, "Error connecting to database!");
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Username length should be atleast 5!");
//        }
//        return success;
//    }
//
//    public int logInVerification(String username, String password) {
//        int success = 400;
//        return acc.login(username, password);
//    }
//
//    public void viewBalance(String username) {
//        acc.viewBalance(username);
//    }
//
//    public boolean addMedicine(String genName, String bName, String cost1, String qty, String value) {
//        boolean success = false;
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jframe", "root", "");
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM `medicine` WHERE brandname='" + bName + "'");
//
//            if (rs.next()) {
//                JOptionPane.showMessageDialog(null, "Brand name already exist!");
//            } else {
//                try {
//                    double cost = Double.parseDouble(cost1);
//                    try {
//                        int quantity = Integer.parseInt(qty);
//                        success = acc.addMedicine(genName, bName, cost, quantity, value);
//                        return success;
//
//                    } catch (HeadlessException | NumberFormatException e) {
//                        JOptionPane.showMessageDialog(null, "Stock should be a number!");
//                    }
//
//                } catch (HeadlessException | NumberFormatException e) {
//                    JOptionPane.showMessageDialog(null, "Price should be a number!");
//                }
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error connecting to database!");
//
//        }
//        return success;
//    }
//
//    public boolean removeMedicine(String brandname) {
//        boolean success = false;
//        boolean exist = false;
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jframe", "root", "");
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM `medicine` WHERE brandname='" + brandname + "'"); // only choose the medicine inputted from the view
//
//            while (rs.next()) {
//                if (rs.getString("brandname").equals(brandname)) {
//                    try {
//                        exist = true; // signifies that medicine existed
//
//                        return success = acc.removeMedicine(brandname);
//
//                    } catch (HeadlessException e) {
//                        JOptionPane.showMessageDialog(null, "Error removing!!"); // This is something error while removing
//                    }
//                }
//            }
//            if (exist == false) {
//                JOptionPane.showMessageDialog(null, "Brand name do not exist!");  // if brand name you entered do not exist from the database              
//            }
//        } catch (ClassNotFoundException | SQLException | HeadlessException e) {
//            JOptionPane.showMessageDialog(null, "Error connecting to database!");
//        }
//        return success;
//    }
//
//    public boolean order(String uname, String bname, String quantity) {
//        boolean success = false;
//        boolean exist = false;
//
//        try {
//            int qty = Integer.parseInt(quantity);
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jframe", "root", "");
//                Statement stmt = con.createStatement();
//                ResultSet rs = stmt.executeQuery("SELECT * FROM `medicine` WHERE brandname='" + bname + "'");
//                
//                while(rs.next()){
//                    int stock = rs.getInt("stock");
//                    double price = rs.getDouble("price");
//                    if (rs.getString("brandname").equals(bname)) {
//                        exist = true;                      
//                        return success = acc.order(uname, bname, qty);                  
//                    }
//                    break;
//                }
//                
//                if(exist == false){
//                    JOptionPane.showMessageDialog(null, "Medicine do not exist!");               
//                }
//
//            } catch (ClassNotFoundException | SQLException e) {
//                JOptionPane.showMessageDialog(null, "Error connecting to database!");               
//            }
//
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Quantity should be a number!");
//        }
//
//        return success;
//    }
//
//}
