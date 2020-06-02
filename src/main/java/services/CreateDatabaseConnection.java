package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDatabaseConnection {



    public static Connection connect(){

        Connection connection = null;
        String url = "jdbc:sqlite:/Users/amanbhatia/Documents/Coursework/Quarter3/Programming Styles/zoomapi/zoomdb.db";
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to zoom database is established");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void disconnect(Connection connection){
        try {
            connection.close();
            System.out.println("Connection to the database is closed now");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
