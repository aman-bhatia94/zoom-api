package services;

import services.data.DBRequestData;
import services.data.DBResponseData;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataDMLService {

    Connection connection;

    public DataDMLService(Connection connection) {
        this.connection = connection;
    }

    public <T> DBResponseData get(T requestData) throws IllegalAccessException {
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
        if(queryObj != null){
            sql.append(" WHERE ");
            Field[] fields = queryObj.getClass().getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                Object val = field.get(queryObj);
                sql.append(fieldName+ " = "+val.toString() + " AND ");
                System.out.println("Name: " + fieldName + "\nval: " + val.toString());
            }
            String sqlToRun = sql.toString();
            int lastIndexOfAnd = sqlToRun.lastIndexOf("AND");
            StringBuilder temp = new StringBuilder();
            temp.append(sqlToRun.substring(0,lastIndexOfAnd));
            temp.append(";");
            finalSql = temp.toString();
        }
        else{
            sql.append(";");
            finalSql = sql.toString();
        }

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(finalSql);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println("Query: " + queryObj);



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
