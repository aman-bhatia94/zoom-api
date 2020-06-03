package services;

import services.data.DBRequestData;
import services.data.DBResponseData;

import java.sql.Connection;

public class DataDMLService {

    Connection connection;

    public DataDMLService(Connection connection) {
        this.connection = connection;
    }

    public DBResponseData get(DBRequestData requestData) {
        DBResponseData responseData = new DBResponseData();

        return responseData;
    }

    public Object update(DBRequestData requestData) {
        DBResponseData responseData = new DBResponseData();
        return responseData;
    }

    public Object delete(DBRequestData requestData) {
        DBResponseData responseData = new DBResponseData();
        return responseData;
    }
}
