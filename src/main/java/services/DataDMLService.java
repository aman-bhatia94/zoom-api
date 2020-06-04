package services;

import models.Credentials;
import services.data.DBRequestData;
import services.data.DBResponseData;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.HashMap;

public class DataDMLService {

    Connection connection;

    public DataDMLService(Connection connection) {
        this.connection = connection;
    }

    public <T>  DBResponseData get(T requestData) {


        Class table = requestData.getClass();
        Field queryValues = null;
        Field newValues = null;
        Field tableName = null;
        try {
            queryValues = table.getDeclaredField("queryValues");
            newValues = table.getDeclaredField("newValues");
            tableName = table.getDeclaredField("tableName");

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        StringBuilder sql = new StringBuilder();


        sql.append("SELECT * FROM "+tableName+" ");
        if(queryValues == null){
            //This means we get all the data
            sql.append(";");
        }
        else{

        }


        return null;
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
