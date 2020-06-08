package utils;

import services.DataDDLService;
import services.DataDMLService;
import services.DatabaseConnectionService;

import java.sql.Connection;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DatabaseConnection {

    //cache after every 15 minutes
    private static final long TIMEOUT_IN_MIN = 1;
    private static DataDDLService dataDDLService;
    private static DataDMLService dataDMLService;
    private static Connection connection;
    private static LocalDateTime ChannelMembershipTimeStamp;
    private static LocalDateTime ChannelTimestamp;
    private static LocalDateTime CredentialsTimestamp;
    private static LocalDateTime MessagesTimestamp;

    public DatabaseConnection() {

    }

    public static DataDDLService getDataDDLService() {
        return dataDDLService;
    }

    public static DataDMLService getDataDMLService() {
        return dataDMLService;
    }

    public static void init() {
        connection = DatabaseConnectionService.connect();
        dataDDLService = new DataDDLService();
        dataDDLService.createAllTables(connection);
        dataDMLService = new DataDMLService(connection);
    }

    private static String getLatestTimestamp(TimestampModeEnum timeoutMode) {
        if (timeoutMode == TimestampModeEnum.ChannelMembershipTimeStamp) {
            return Utils.getTimeStampString(ChannelMembershipTimeStamp);
        } else if (timeoutMode == TimestampModeEnum.ChannelTimestamp) {
            return Utils.getTimeStampString(ChannelTimestamp);
        } else if (timeoutMode == TimestampModeEnum.CredentialsTimestamp) {
            return Utils.getTimeStampString(CredentialsTimestamp);
        } else if (timeoutMode == TimestampModeEnum.MessagesTimestamp) {
            return Utils.getTimeStampString(MessagesTimestamp);
        }
        return null;
    }

    private static void setLatestTimeStamp(TimestampModeEnum timeoutMode) {
        LocalDateTime currentTimeStamp = LocalDateTime.now(ZoneOffset.UTC);
        switch (timeoutMode) {
            case ChannelMembershipTimeStamp:
                ChannelMembershipTimeStamp = currentTimeStamp;
                break;
            case ChannelTimestamp:
                ChannelTimestamp = currentTimeStamp;
                break;
            case CredentialsTimestamp:
                CredentialsTimestamp = currentTimeStamp;
                break;
            case MessagesTimestamp:
                MessagesTimestamp = currentTimeStamp;
                break;
        }
    }

    public static boolean isTimeOut(TimestampModeEnum timeoutMode) throws Exception {
        LocalDateTime latestTimeStamp = null;
        if (timeoutMode == TimestampModeEnum.ChannelMembershipTimeStamp) {
            latestTimeStamp = ChannelMembershipTimeStamp;
        } else if (timeoutMode == TimestampModeEnum.ChannelTimestamp) {
            latestTimeStamp = ChannelTimestamp;
        } else if (timeoutMode == TimestampModeEnum.CredentialsTimestamp) {
            latestTimeStamp = CredentialsTimestamp;
        } else if (timeoutMode == TimestampModeEnum.MessagesTimestamp) {
            latestTimeStamp = MessagesTimestamp;
        }
        if (latestTimeStamp == null) {
            setLatestTimeStamp(timeoutMode);
            return true;
        }
        LocalDateTime currentTimeStamp = LocalDateTime.now(ZoneOffset.UTC);
        long duration = Duration.between(latestTimeStamp, currentTimeStamp).toMinutes();
        if (duration >= TIMEOUT_IN_MIN) {
            setLatestTimeStamp(timeoutMode);
            return true;
        }
        return false;
    }

    public enum TimestampModeEnum {
        ChannelMembershipTimeStamp,
        ChannelTimestamp,
        CredentialsTimestamp,
        MessagesTimestamp
    }
}
