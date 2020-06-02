package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDatabaseConnection {



    public static void connect(){

        Connection connection = null;
        String url = "jdbc:sqlite:/Users/amanbhatia/Documents/Coursework/Quarter3/Programming Styles/zoomapi/zoomdb.db";
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to zoom database is established");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
