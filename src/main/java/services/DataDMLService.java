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
        Class queryValues = null;
        Class newValues = null;
        try {
            Field[] fields = table.getFields();
            for(Field field: fields){
                String fieldName = field.getName();
                Class type = field.getType();
                String value = table.get

            }
            queryValues = table.getField()
            //queryValues = table.getDeclaredField("queryValues");
            //newValues = table.getDeclaredField("newValues");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        StringBuilder sql = new StringBuilder();
        String tableName = table.getName().split("\\.")[1];

        sql.append("SELECT * FROM "+tableName+" ");
        if(queryValues == null){
            //This means we get all the data
            sql.append(";");
        }
        else{
            Field[] fields =
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
