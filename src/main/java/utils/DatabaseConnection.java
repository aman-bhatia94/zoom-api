package utils;

import com.google.gson.Gson;
import models.Credentials;
import services.DataDDLService;
import services.DataDMLService;
import services.DatabaseConnectionService;
import services.data.CredentialsRequestData;

import java.sql.Connection;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DatabaseConnection {

    static final Gson GSON = new Gson();
    //cache after every 15 minutes
    private static final long TIMEOUT_IN_MIN = 15;
    private static DataDDLService dataDDLService;
    private static DataDMLService dataDMLService;
    private static Connection connection;
    private static LocalDateTime latestTimeStamp;
    private static String clientId;
    private static String token;
    private static Credentials credentials;

    public DatabaseConnection() {

    }

    public static DataDDLService getDataDDLService() {
        return dataDDLService;
    }

    public static DataDMLService getDataDMLService() {
        return dataDMLService;
    }

    public static void init(String clientId1, String token1) throws Exception {
        connection = DatabaseConnectionService.connect();
        dataDDLService = new DataDDLService();
        dataDDLService.createAllTables(connection);
        dataDMLService = new DataDMLService(connection);
        latestTimeStamp = Utils.getDateTime(getLatestTimestamp());
        clientId = clientId1;
        token = token1;
    }

    private static String getLatestTimestamp() throws Exception {
        String dbResponse = dataDMLService.get(new CredentialsRequestData(null, null)).getResponseData();
        Credentials credentialsResponse = GSON.fromJson(dbResponse, Credentials.class);
        credentials = credentialsResponse;
        return credentialsResponse.getTime_stamp();
    }

    private static void setLatestTimeStamp() throws Exception {
        LocalDateTime currentTimeStamp = LocalDateTime.now(ZoneOffset.UTC);
        Credentials newValues = new Credentials(credentials.getId(), credentials.getClient_id(), credentials.getOauth_token(), Utils.getTimeStampString(currentTimeStamp));
        String dbResponse = dataDMLService.update(new CredentialsRequestData(credentials, newValues)).getResponseData();
        credentials = GSON.fromJson(dbResponse, Credentials.class);
    }

    public static boolean isTimeOut() throws Exception {
        LocalDateTime currentTimeStamp = LocalDateTime.now(ZoneOffset.UTC);
        long duration = Duration.between(latestTimeStamp, currentTimeStamp).toMinutes();
        if (duration >= TIMEOUT_IN_MIN) {
            setLatestTimeStamp();
            return true;
        }
        return false;
    }
}
