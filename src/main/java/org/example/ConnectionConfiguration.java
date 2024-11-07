package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionConfiguration {
    private static Connection conn;

    private ConnectionConfiguration(){}

    public static Connection getConnection(){
        if(conn == null){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ChatAppDB", "root", "");
                if(conn != null){
                    System.out.println("Connected to database");
                }
                else{
                    System.out.println("Not connected to database");
                }
            }
            catch(SQLException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }

        return conn;
    }
}

