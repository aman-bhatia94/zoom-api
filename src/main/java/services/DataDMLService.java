package services;

import services.data.DBRequestData;
import services.data.DBResponseData;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;


public class DataDMLService {

    Connection connection;

    public DataDMLService(Connection connection) {
        this.connection = connection;
    }

    public <T> HashMap<String, String> get(T requestData) throws IllegalAccessException {
        Class table = requestData.getClass();

        Field[] mainFields = table.getDeclaredFields();
        Object queryObj = null;
        Object newValuesObj = null;
        String tableName = null;
        for (Field mainField : mainFields) {
            if (mainField.getName().contains("queryValues")) {
                queryObj = mainField.get(requestData);
            } else if (mainField.getName().contains("newValues")) {
                newValuesObj = mainField.get(requestData);
            } else if (mainField.getName().contains("tableName")) {
                tableName = mainField.get(requestData).toString();
            }
        }
        String finalSql = null;
        StringBuilder sql = new StringBuilder();

        sql.append( "SELECT * FROM "+tableName);
        Field[] fields = queryObj.getClass().getDeclaredFields();
        if(queryObj != null){
            sql.append(" WHERE ");
            for (Field field : fields) {
                String fieldName = field.getName();
                Object val = field.get(queryObj);
                sql.append(fieldName + " = " + val.toString() + " AND ");
                System.out.println("Name: " + fieldName + "\nval: " + val.toString());
            }
            String sqlToRun = sql.toString();
            int lastIndexOfAnd = sqlToRun.lastIndexOf("AND");
            StringBuilder temp = new StringBuilder();
            temp.append(sqlToRun, 0, lastIndexOfAnd);
            temp.append(";");
            finalSql = temp.toString();
        } else {
            sql.append(";");
            finalSql = sql.toString();
        }
        HashMap<String,String> dataToSend = new HashMap<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(finalSql);
            while(rs.next()){
                for(Field field: fields){
                    String fieldName = field.getName();
                    if(fieldName.equalsIgnoreCase("id")){
                        dataToSend.put("id",String.valueOf(rs.getInt("id")));
                        continue;
                    }
                    dataToSend.put(fieldName,rs.getString(fieldName));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataToSend;
    }

    public Object update(DBRequestData requestData) {
        DBResponseData responseData = new DBResponseData();
        return responseData;
    }

    public <T> DBResponseData insert(T requestData) throws IllegalAccessException {
        Class table = requestData.getClass();

        Field[] mainFields = table.getDeclaredFields();
        Object queryObj = null;
        String tableName = null;
        for (Field mainField : mainFields) {
            if (mainField.getName().contains("queryValues")) {
                queryObj = mainField.get(requestData);
            } else if (mainField.getName().contains("tableName")) {
                tableName = mainField.get(requestData).toString();
            }
        }
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO " + tableName + "(");
        List<String> fieldNames = new ArrayList<>();
        List<String> values = new ArrayList<>();
        Field[] fields = queryObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Object val = field.get(queryObj);
            fieldNames.add(fieldName);
            values.add(val.toString());
        }
        sql.append(String.join(",", fieldNames));
        sql.append(") \n");
        sql.append("values (default, ");
        sql.append(String.join(",", values));
        sql.append(")");
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DBResponseData(0, null, rs);
    }

    public Object delete(DBRequestData requestData) {
        DBResponseData responseData = new DBResponseData();
        return responseData;
    }
}
