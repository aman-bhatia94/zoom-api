package utils;

import services.DataDDLService;
import services.DataDMLService;
import services.DatabaseConnectionService;

import java.sql.Connection;

public class DatabaseConnection {

    private static DataDDLService dataDDLService;
    private static DataDMLService dataDMLService;
    private static Connection connection;

    public DatabaseConnection() {
    }

    public static void init() {
        connection = DatabaseConnectionService.connect();
        dataDDLService = new DataDDLService();
        dataDDLService.createAllTables(connection);
        dataDMLService = new DataDMLService(connection);
    }
}
