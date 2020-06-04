package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionService {

    public static Connection connect() {

        Connection connection = null;
        String url = "jdbc:sqlite:C:\\Users\\guds\\Desktop\\MSWE\\CourseWork\\Spring_2020\\262P\\zoom-api\\zoomdb.db";
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to zoom database is established");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void disconnect(Connection connection) {
        try {
            connection.close();
            System.out.println("Connection to the database is closed now");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
