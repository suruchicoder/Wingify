package com.springapp.mvc;

import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;

import static java.sql.Statement.*;

/**
* Created by suruchi on 23/4/16.
*/
public class DbConn
{
    private static Gson gson = new Gson();
    public static final String USER_table = "User";

private static final String sqlInsertUs = "INSERT INTO " + USER_table + " (name,username,password) VALUES (?,?,?)";

    public static void insertUser(Connection connection,String name, String username,String password) throws SQLException {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUs);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            System.out.print("Databaseconnection"+name);
            return ;
        } catch (SQLException e) {
            throw e;
        }

      }

    //private static String TABLE_NAME = "User";
    private static final String selectTableSQL =  "SELECT username,password from User";
    public static int IfAuthUser(Connection connection,  String username, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
//


            preparedStatement =  connection.prepareStatement(selectTableSQL);
            ResultSet rs = preparedStatement.executeQuery(selectTableSQL);
            while (rs.next()) {
               String  usern = rs.getString("username");
                String passw =  rs.getString("password");
                if ((username.equals(usern) )&& (password.equals(passw))) {
                    return 1;
                }
            }
            return 0;
        } catch (SQLException e) {
            throw e;
        }
    }
    private static String TABLE_NAME = "Product";
    public static void updateProduct(Connection connection,int idx,String name,String description) throws SQLException {
       // String sql = "UPDATE " + USER_EDUCATION_TABLE_NAME + "  SET school_name=?,time_from=?, time_to=?,degree=?,field_of_study=? where global_user_id=? and order_id=?";
         String sqlele =  "UPDATE " +  TABLE_NAME + "  SET name=?,description=?  where id=? ";

        boolean success = false;
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = connection.prepareStatement(sqlele);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, idx);
            preparedStatement.executeUpdate();


        }catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            preparedStatement.close();
        }
        return ;
    }
    private static String TABLE_Pro = "Product";
    private static final String sqlInsertPro = "INSERT INTO " + TABLE_Pro + " (name,description) VALUES (?,?)";
    public static void addProduct(Connection connection, String name, String description) throws SQLException  {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sqlInsertPro);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, description);
            preparedStatement.executeUpdate();
          return;
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {

            preparedStatement.close();
        }

    }




//        try {
//            PreparedStatement statement = connection.prepareStatement(sqlInsertPro,
//                    Statement.RETURN_GENERATED_KEYS);
//            )
//            preparedStatement = connection.prepareStatement(sqlInsertPro);
//            preparedStatement.setString(1,name);
//            preparedStatement.setString(2, description);
//
//
//            preparedStatement.executeUpdate();
//            Statement stmt = connection.createStatement();
//            number = stmt.executeUpdate(sqlInsertPro, Statement.RETURN_GENERATED_KEYS);
//
//            System.out.print("id is"+number);
//
//        } catch (SQLException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } finally {
//
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
     //   }


    private static final String sqlDeletePro = "DELETE FROM " + TABLE_Pro + " where id = ?";

    public static String deleteProduct(Connection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlDeletePro);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return "";
        } catch (SQLException e) {
            throw e;
        }

    }
    private static final String selectS =  "SELECT id,name,description from Product";
    public static Product lastCreatedProduct(Connection connection, Integer idx) throws SQLException {
        PreparedStatement preparedStatement = null;
        Product p=new Product();
        try {
            preparedStatement = connection.prepareStatement(selectS);
            ResultSet rs = preparedStatement.executeQuery(selectS);
            while (rs.next()) {

                String RsId=rs.getString("id");
                int id= Integer.parseInt(RsId);
                Product pro = new Product();
                pro.name = rs.getString("name");
                pro.discription = rs.getString("description");
                pro.id = id;
                p=pro;
            }
           // System.out.print("RsId"+pro.id);

            return p;
        } catch (SQLException e) {
            throw e;
        }
    }
    private static final String selectSQL =  "SELECT id,name,description from Product";
    public static Product findProduct(Connection connection, Integer idx) throws SQLException {
        PreparedStatement preparedStatement = null;
        Product pro = new Product();
        try {
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery(selectSQL);
            while (rs.next()) {

                String RsId = rs.getString("id");
                System.out.println(RsId+"\n");
                String y = Integer.toString(idx);
                if (y.equals(RsId)) {
                    // String  name = rs.getString("name");
                    //String  des = rs.getString("description");
                    System.out.println(pro.name+"\n");
                    pro.name = rs.getString("name");
                    pro.discription = rs.getString("description");
                    pro.id = idx;
                    System.out.println(pro.name+pro.id+"\n");
                    return pro;
                }
            }
            return null;
        } catch (SQLException e) {
            throw e;
        }
    }

    private static final String SQLS =  "SELECT id,name ,description from Product";
    public static  ArrayList<Product> findAllProduct(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        ArrayList<Product> s = new ArrayList<Product>();
        try {
            preparedStatement =  connection.prepareStatement(SQLS);
            ResultSet rs = preparedStatement.executeQuery(SQLS);
            while (rs.next()) {
                String RsId=rs.getString("id");
                int id= Integer.parseInt(RsId);
                Product pro = new Product();
                pro.name = rs.getString("name");
                pro.discription = rs.getString("description");
                pro.id = id;

                s.add(pro);
            }
            return s;
        } catch (SQLException e) {
            throw e;
        }
    }
    private static final String SQLStat =  "SELECT username from User";
    public static int IfUserExist(Connection connection, String username) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement =  connection.prepareStatement(SQLStat);
            ResultSet rs = preparedStatement.executeQuery(SQLStat);
            while (rs.next()) {
                String usern = rs.getString("username");

                if ((username.equals(usern) )) {
                    return 1;
                }
            }
            return 0;
        }   catch (SQLException e) {
            throw e;
        }

    }
}
