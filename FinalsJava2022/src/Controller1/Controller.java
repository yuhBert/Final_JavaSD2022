/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller1;

import Frames.*;
import Model1.*;
import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author mariscalyu_SD2022
 */
public class Controller {

    Model acc = new Model();

    // For Registratin Verification
    public boolean registerVerification(String user, String fname, String lname, String pass, String age1) {
        JRegister r = new JRegister();
        boolean success = false;
        if (user.length() > 0 && pass.length() >= 8 && fname.length() > 0 && lname.length() > 0) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/luciferpharmacy", "root", "");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM `account` WHERE username='" + user + "'");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(r, "Username is already taken!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int age = Integer.parseInt(age1);
                        if (age >= 18 && age <= 130) {
                            success = acc.register(user, fname, lname, pass, age);
                            return success;
                        } else {
                            JOptionPane.showMessageDialog(r, "Minor is not ALLOWED", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(r, "Age should be number!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (HeadlessException | ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(r, "Error connecting to database!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(r, "Fill Up Everything & Password must be at least 8 characters", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return success;
    }

    // For LogIN Verification
    public int logInVerification(String username, String password) {
        int success = 400;
        return acc.login(username, password);
    }

    // For Admin Add Medicine
    public boolean addMedicine(String name, String bname, String gname, String type, String price1, String stock1) {
        JAdminAdd a = new JAdminAdd();
        boolean success = false;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/luciferpharmacy", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `medicine` WHERE name='" + name + "'");

            if (rs.next()) {
                JOptionPane.showMessageDialog(a, "Medicine Name already existed!\nUpdate " + name, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (name.length() > 0 && bname.length() > 0 && gname.length() > 0 && type.length() > 0) {
                    if (type.matches("Headache Medicine") || type.matches("Allergies Medicine") || type.matches("Body Pain Medicine") || type.matches("Cough Medicine")) {
                        try {
                            double price = Double.parseDouble(price1);
                            try {
                                int stock = Integer.parseInt(stock1);
                                success = acc.addMedicine(name, bname, gname, type, price, stock);
                                return success;

                            } catch (HeadlessException | NumberFormatException e) {
                                JOptionPane.showMessageDialog(a, "Stock should be a number!", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        } catch (HeadlessException | NumberFormatException e) {
                            JOptionPane.showMessageDialog(a, "Price should be a number!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(a, "For Type\nChoose of the three 'Allergies Medicine' or 'Body Pain Medicine' or 'Headache Medicine' ", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(a, "Fill Up Everything", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(a, "Error connecting to database!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return success;
    }

    // For Admin Remove Medicine
    public boolean removeMedicine(String ID) {
        JAdminRem r = new JAdminRem();
        boolean success = false;
        boolean exist = false;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/luciferpharmacy", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `medicine` WHERE Id='" + ID + "'");

            while (rs.next()) {
                try {
                    int id = Integer.parseInt(ID);
                    if (rs.getString("Id").equals(ID)) {
                        try {
                            exist = true;
                            return success = acc.removeMedicine(id);
                        } catch (HeadlessException e) {
                            JOptionPane.showMessageDialog(r, "Error Removing", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (HeadlessException | NumberFormatException e) {
                    JOptionPane.showMessageDialog(r, "ID should be a number!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (exist == false) {
                JOptionPane.showMessageDialog(r, "Medicine ID Number does not Exist", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ClassNotFoundException | SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(r, "Error connecting to database", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return success;
    }

    // For Admin Update Medicine
    public boolean updateMed(String ID, String name, String bname, String gname, String type, String price1, String stock1) {
        JAdminUpd u = new JAdminUpd();
        boolean success = false;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/luciferpharmacy", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `medicine` WHERE `Id`=" + ID);

        } catch (ClassNotFoundException | SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(u, "Error connecting to database", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return success;
    }

//if (rs.next()) {
//                System.out.println("Successfully connected");
//                if (name.length() > 0 && bname.length() > 0 && gname.length() > 0 && type.length() > 0) {
//                    if (type.equals("Headache Medicine") || type.equals("Allergies Medicine") || type.equals("Body Pain Medicine")) {
//                        try {
//                            price = Double.parseDouble(priceF.getText());
//                            priceF.requestFocusInWindow();
//                            stock = Integer.parseInt(stockF.getText());
//                            stockF.requestFocusInWindow();
//                            if (price > 0 && stock > 0) {
////                            String sql = "INSERT INTO `medicine`( `name`, `bname`, `gname`, `type`, `price`, `stock`) VALUES ('" + name + "','" + bname + "','" + gname + "','" + type + "','" + price + "'," + stock + ")";
//                            String sql = "UPDATE `medicine` SET `name`='" + name + "',`bname`='" + bname + "',`gname`='" + gname + "',`type`='" + type + "',`price`=" + price + ",`stock`="+stock;
//                            stmt.executeUpdate(sql);
//                            JOptionPane.showMessageDialog(rootPane, "Medicine Updated Successfully");
//                            JViewMed view = new JViewMed();
//                            view.setVisible(true);
//                            this.setVisible(false);
//                            con.close();
//
//                        } else {
//                            JOptionPane.showMessageDialog(rootPane, "Number less than zero is not valid", "Error", JOptionPane.ERROR_MESSAGE);
//                        }
//                        } catch (NumberFormatException z) {
//                            JOptionPane.showMessageDialog(rootPane, "Numbers Only", "Error", JOptionPane.ERROR_MESSAGE);
//                            priceF.setText("");
//                            priceF.requestFocusInWindow();
//                            stockF.setText("");
//                            stockF.requestFocusInWindow();
//                            idF.setText("");
//                            idF.requestFocusInWindow();
//                        }
//                        
//                    } else {
//                        JOptionPane.showMessageDialog(rootPane, "For the Type\nChoose of the three 'Allergies Medicine' or 'Body Pain Medicine' or 'Headache Medicine' ", "Error", JOptionPane.ERROR_MESSAGE);
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(rootPane, "Fill Up Everything", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//                con.close();
//            } else {
////                JOptionPane.showMessageDialog(rootPane, "Medicine Name already existed!\nUpdate " + name);
//            }
//        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
//            System.out.println(e);
//        }
//            
//            
//            
//            
//            
//            
//        } catch(NumberFormatException e){
//            JOptionPane.showMessageDialog(rootPane,"ID must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
//        }
// Order Medicine
    public boolean order(String uname, String id2, String quantity) {
        boolean success = false;
        boolean exist = false;

        try {

            int qty = Integer.parseInt(quantity);
            try {
                int id = Integer.parseInt(id2);
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/luciferpharmacy", "root", "");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM `medicine` WHERE id='" + id + "'");

                    while (rs.next()) {
                        int stock = rs.getInt("stock");
                        double price = rs.getDouble("price");
                        if (rs.getInt("id") == id) {
                            exist = true;
                            return success = acc.order(uname, id, qty);
                        }
                        break;
                    }
                    if (exist == false) {
                        JOptionPane.showMessageDialog(null, "Medicine do not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "ID should be a number!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Error connecting to database!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantity should be a number!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return success;
    }
}
