package com.springapp.mvc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
* User: rishabh
* Date: 30/8/14
* Time: 4:53 PM
*/
public class DatabaseUtil {
    private static DatabaseConnectionPool databaseConnectionPool;

    static {
        try {
            databaseConnectionPool = new DatabaseConnectionPool("jdbc:mysql://localhost:3306/UserDB", "root", "poi000poi", 40, 50);
           // databaseConnectionPool = new DatabaseConnectionPool("jdbc:mysql://127.3.222.2:3306/UserDB", "admin1jlKkX2", "6EXDv53FUuKi", 40, 50);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnectionPool getDataseBaseConnectionPool() {
        return databaseConnectionPool;
    }
//    public Product getUserById(long global_user_id) {
//        Connection connection = null;
//        Product product = null;
//        try {
//            connection =getConnection();
//            return dbConn.selectUser(connection, global_user_id);
//            return dbConn.insert();
//        } catch (SQLException e) {
//
//        } finally {
//            try {
//                leaveConnection(connection);
//            } catch (SQLException e) {
//            }
//        }
//        return null;
//    }

    private Connection getConnection() throws SQLException {

        return getDataseBaseConnectionPool().getConnection();
    }
    private void leaveConnection(Connection connection) throws SQLException {

        getDataseBaseConnectionPool().leaveConnection(connection);
    }
    public void insertU(String name, String username, String password){
        Connection connection = null;
        try {
            connection =getConnection();
            System.out.print("DatabaseUtil"+name);
             DbConn.insertUser(connection, name, username, password);
            return;
        } catch (SQLException e) {

        } finally {
            try {
                leaveConnection(connection);
            } catch (SQLException e) {
            }
        }
        return;
    }
    public  int IfAuth(String username,String password){
        Connection connection = null;
        try {
            connection =getConnection();
            int flag=DbConn.IfAuthUser(connection, username, password);
            return flag;
        } catch (SQLException e) {

        } finally {
            try {
                leaveConnection(connection);
            } catch (SQLException e) {
            }
        }
        return 0;
    }
    public void addProduct(String name, String description){
        Connection connection = null;
        int p=-1;
        try {
            connection =getConnection();

            DbConn.addProduct(connection, name, description);

        } catch (SQLException e) {

        } finally {
            try {
                leaveConnection(connection);
            } catch (SQLException e) {
            }
        }

    }
    public void updateProduct(int id,String name, String description){
        Connection connection = null;
        try {
            connection =getConnection();

            DbConn.updateProduct(connection,id, name, description);
            return;
        } catch (SQLException e) {

        } finally {
            try {
                leaveConnection(connection);
            } catch (SQLException e) {
            }
        }
        return;
    }
    public void deleteProduct(int id) {
        Connection connection = null;
        try {
            connection =getConnection();
            DbConn.deleteProduct(connection, id);
            return;
        } catch (SQLException e) {

        } finally {
            try {
                leaveConnection(connection);
            } catch (SQLException e) {
            }
        }
        return;
    }

    public Product findProduct(Integer id) {
        Connection connection = null;
        Product pro=new Product();
        try {
            connection =getConnection();
            pro=DbConn.findProduct(connection, id);
            return pro;
        } catch (SQLException e) {

        } finally {
            try {
                leaveConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }
    public Product lastCreated(Integer id) {
        Connection connection = null;
        Product pro=new Product();
        try {
            connection =getConnection();
            pro=DbConn.lastCreatedProduct(connection, id);
            return pro;
        } catch (SQLException e) {

        } finally {
            try {
                leaveConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    public  ArrayList<Product> AllProduct() {
        Connection connection = null;
        ArrayList<Product> s;

        try {
            connection =getConnection();
            s=DbConn.findAllProduct(connection);
            return s;
        } catch (SQLException e) {

        } finally {
            try {
                leaveConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    public int checkIfUsernameExist(String username) {
        Connection connection = null;
        try {
            connection =getConnection();
            int IfUserExist= DbConn.IfUserExist(connection, username);
            return IfUserExist;
        } catch (SQLException e) {

        } finally {
            try {
                leaveConnection(connection);
            } catch (SQLException e) {
            }
        }

        return 0;
    }


}
