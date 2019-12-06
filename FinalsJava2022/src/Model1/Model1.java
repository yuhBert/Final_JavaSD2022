///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Model1;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import javax.swing.JOptionPane;
////import View.Home1;
////import View.adminView;
////import View.customerView;
////import View.register;
//import java.awt.HeadlessException;
//import java.sql.ResultSet;
//
///**
// *
// * @author mariscalyu_SD2022
// */
//public class Model1 {
////   public boolean register(String username, String password, int age, double money) {
////        boolean success = false;
////        try {
////            Statement stmt = null;
////            Class.forName("com.mysql.jdbc.Driver");
////            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/luciferpharmacy", "root", "");
////            stmt = (Statement) con.createStatement();
////
////            String synt = "'" + username + "','" + password + "'," + age + "," + money;
////            String sql = "INSERT INTO `users`(`username`, `password`, `age`, `money`) VALUES (" + synt + ")";
////            stmt.executeUpdate(sql);
////
////            con.close();
////            success = true;
////        } catch (ClassNotFoundException | SQLException e) {
////            JOptionPane.showMessageDialog(null, "Error connecting to database!");
////        }
////        return success;
////    }
////
////    public int login(String username, String password) {
////        int success = 400;
////        boolean loggedIn = false;
////        try {
////            Class.forName("com.mysql.jdbc.Driver");
////            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jframe", "root", "");
////            java.sql.Statement stmt = con.createStatement();
////            ResultSet rs1 = stmt.executeQuery("SELECT * FROM `admin` where username = '" + username + "' and password = '" + password + "'");
////
////            if (rs1.next()) {
////                loggedIn = true;
////                return success = 500;
////            } else {
////                ResultSet rs = stmt.executeQuery("SELECT * FROM `users` where username = '" + username + "'");
////                if (rs.next()) {
////                    if (rs.getString("password").equals(password)) {
////                        loggedIn = true;
////                        return success = 600;
////                    }
////                }
////            }
////            if (loggedIn == false) {
////                return success;
////            }
////            con.close();
////        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
////            JOptionPane.showMessageDialog(null, "Error while connecting!");
////        }
////        return success;
////    }
//
//    public void viewBalance(String username) {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jframe", "root", "");
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM `users` WHERE username='" + username + "'");
//            if (rs.next()) {
//                JOptionPane.showMessageDialog(null, "Your balance is: " + rs.getDouble("money"));
//            }
//            con.close();
//        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error connecting to database!");
//        }
//    }
//
//    public boolean addMedicine(String genName, String bName, double cost1, int qty, String value) {
//        boolean success = false;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jframe", "root", "");
//            Statement stmt = con.createStatement();
//            String sql = "INSERT INTO `medicine`(`genericname`, `brandname`, `medicinetype`, `price`, `stock`) VALUES ('" + genName + "','" + bName + "','" + value + "'," + cost1 + "," + qty + ")";
//            stmt.executeUpdate(sql);
//            success = true;
//            con.close();
//        } catch (ClassNotFoundException | SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error connecting to database!");
//        }
//        return success;
//    }
//
//    public boolean removeMedicine(String brandname) {
//        boolean success = false;
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jframe", "root", "");
//            Statement stmt = con.createStatement();
//            //ResultSet rs = stmt.executeQuery("SELECT * FROM `medicine` WHERE brandname='" + brandname + "'"); // only choose the medicine inputted from the view
//            String sql = "DELETE FROM `medicine` WHERE brandname='" + brandname + "'"; // query here         
//            stmt.executeUpdate(sql); // delete the medicine you've inputted earlier
//            success = true;
//            con.close();
//        } catch (ClassNotFoundException | SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error connecting to database!");
//        }
//        return success;
//    }
//
//    public boolean order(String uname, String bname, int qty) {
//        boolean success = false;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jframe", "root", "");
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM `medicine` WHERE brandname='" + bname + "'");
//
//            while (rs.next()) {
//                int stock = rs.getInt("stock");
//                double price = rs.getDouble("price");
//                if (rs.getString("brandname").equals(bname)) {
//                    ResultSet rs1 = stmt.executeQuery("SELECT * FROM `users`");
//                    while (rs1.next()) {
//                        int age = rs1.getInt("age");
//                        double money = rs1.getDouble("money");
//                        double moneyLeftSenior = money - ((stock * price) * .80);
//                        double moneyLeftAdult = money - (stock * price);
//                        if (rs1.getString("username").equals(uname)) {
//                            System.out.println("prinitnig");
//                            if (stock < qty) {
//                                JOptionPane.showMessageDialog(null, "Insufficient stock!");
//                            } else if (stock == qty) {
//                                if (money < price) {
//                                    JOptionPane.showMessageDialog(null, "Insufficient money!");
//                                } else {
//                                    if (age >= 18 && age <= 59) {
//                                        JOptionPane.showMessageDialog(null, "The amount is: " + (qty * price));
//                                        String sql = "DELETE FROM `medicine` WHERE brandname='" + bname + "'";
//                                        String sql1 = "UPDATE `users` SET `money`=" + moneyLeftAdult + " WHERE username='" + uname + "'";
//                                        stmt.addBatch(sql);
//                                        stmt.addBatch(sql1);
//                                        stmt.executeBatch();
//                                        con.close();
//                                        return success = true;
//                                    } else {
//                                        JOptionPane.showMessageDialog(null, "The amount is: " + ((qty * price) * .80));
//                                        String sql = "DELETE FROM `medicine` WHERE brandname='" + bname + "'";
//                                        String sql1 = "UPDATE `users` SET `money`=" + moneyLeftSenior + " WHERE username='" + uname + "'";
//                                        stmt.addBatch(sql);
//                                        stmt.addBatch(sql1);
//                                        stmt.executeBatch();
//                                        con.close();
//                                        return success = true;
//                                    }
//                                }
//                            }
//                        } else {
//                            if (money < price) {
//                                JOptionPane.showMessageDialog(null, "Insufficient money!");
//                            } else {
//                                if (age >= 18 && age <= 59) {
//                                    JOptionPane.showMessageDialog(null, "The amount is: " + (qty * price));
//                                    String sql = "UPDATE `medicine` SET `stock`=" + (stock - qty) + " WHERE brandname='" + bname + "'";
//                                    String sql1 = "UPDATE `users` SET `money`=" + moneyLeftAdult + " WHERE username='" + uname + "'";
//                                    stmt.addBatch(sql);
//                                    stmt.addBatch(sql1);
//                                    stmt.executeBatch();
//                                    con.close();
//                                    return success = true;
//
//                                } else {
//                                    JOptionPane.showMessageDialog(null, "The amount is: " + ((qty * price) * .80));
//                                    String sql = "UPDATE `medicine` SET `stock`=" + (stock - qty) + " WHERE brandname='" + bname + "'";
//                                    String sql1 = "UPDATE `users` SET `money`=" + moneyLeftSenior + " WHERE username='" + uname + "'";
//                                    stmt.addBatch(sql);
//                                    stmt.addBatch(sql1);
//                                    stmt.executeBatch();
//                                    con.close();
//                                    return success = true;
//                                }
//                            }
//                        }
//                    }
//                }
//                break;
//            }
//            con.close();
//        } catch (ClassNotFoundException | SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error connecting to database!");
//        }
//
//        return success;
//    }
//
//}
