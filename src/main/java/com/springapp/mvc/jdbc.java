package com.springapp.mvc;

import javax.swing.*;
import java.sql.*;

public class jdbc {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/UserDB";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "poi000poi";
    static Connection conn = null;

    static {
        try {
//STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

//STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

// conn.close();
        } catch (SQLException se) {
//Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
//Handle errors for Class.forName
            e.printStackTrace();
        }
    }
    private static Connection getDBConnection() {

        Connection conn = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            //STEP 3: Open a connection

        } catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());

        }

        try {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            return conn;

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return conn;

    }
    public static void addUser(String name,String username, String password) throws SQLException, ClassNotFoundException {
        PreparedStatement st = null;
        try {
//STEP 3: Open a connection
            System.out.println("Connecting to database...");
            st = conn.prepareStatement("insert into User (name,username,password) values (?,?,?);");
//now you bind the data to your parameters
            st.setString(1, name);
            st.setString(2, username);
            st.setString(3, password);
            st.executeUpdate();

        } finally {
//finally block used to close resources
            try {
                if (st != null)
                    conn.close();
            } catch (SQLException ignored) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
//    public static int IfAuthUser(String f, String s) {
//        Statement statement = null;
//        try {
//            conn=getDBConnection();
//            String selectTableSQL = "SELECT username,password from User";
//            statement = conn.createStatement();
//            ResultSet rs = statement.executeQuery(selectTableSQL);
//            while (rs.next()) {
//                String username = rs.getString("username");
//                String password =  rs.getString("password");
//                if ((username.equals(username)) && (password.equals(password)))
//                    return 1;
//            }
//
//        }
//        catch(SQLException e) {
//            e.printStackTrace();
//        }finally {
//            //finally block used to close resources
//            try {
//                if (statement != null)
//                    conn.close();
//            } catch (SQLException ignored) {
//            }// do nothing
//            try {
//                if (conn != null)
//                    conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }//end finally try
//        }
//
//    }
//    public static void read() {
//// String sql = "SELECT usrname,email " +
//// "FROM employees";
//        String sql = "DELETE FROM Product " +
//                "WHERE username = 'bhawna'";
//        Statement stmt = null;
//        try {
//            stmt = conn.createStatement();
//// ResultSet rs = stmt.executeQuery(sql);
//            stmt.executeUpdate(sql);
//
//// loop through the result set
//// while (rs.next()) {
//// System.out.println(rs.getString("usrname") + "\t" +
//// rs.getString("email"));
////
//// }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        } finally {
////finally block used to close resources
//            try {
//                if (stmt != null)
//                    conn.close();
//            } catch (SQLException ignored) {
//            }// do nothing
//            try {
//                if (conn != null)
//                    conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }//end finally try
//        }//end try
//    }
}

