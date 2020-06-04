package services;

import org.json.JSONArray;
import services.data.DBResponseData;
import utils.Utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DataDMLService {

    Connection connection;

    public DataDMLService(Connection connection) {
        this.connection = connection;
    }

    public <T> DBResponseData get(T requestData) throws Exception {
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
        String finalSql = null;
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM " + tableName);
        Field[] fields = queryObj.getClass().getDeclaredFields();
        if (queryObj != null) {
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
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(finalSql);
        JSONArray jsonArray = Utils.convertToJSON(rs);
        return new DBResponseData(200, "success", jsonArray.toString());
    }

    public <T> DBResponseData insert(T requestData) throws Exception {
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
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql.toString());
        String response = Utils.convertToJSON(rs).toString();
        return new DBResponseData(0, null, response);
    }

    public <T> DBResponseData delete(T requestData) throws Exception {
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

        sql.append("DELETE FROM " + tableName);
        Field[] fields = queryObj.getClass().getDeclaredFields();
        if (queryObj != null) {
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
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(finalSql);
        JSONArray jsonArray = Utils.convertToJSON(rs);
        return new DBResponseData(200, "success", jsonArray.toString());
    }

    public <T> DBResponseData update(T requestData) throws Exception {
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
        StringBuilder sql = new StringBuilder();

        sql.append("UDATE " + tableName + " SET ");

        if (newValuesObj != null) {
            Field[] fields = newValuesObj.getClass().getDeclaredFields();
            List<String> condition = new ArrayList<>();
            for (Field field : fields) {
                String fieldName = field.getName();
                Object val = field.get(queryObj);
                condition.add(fieldName + " = " + val);
            }
            sql.append(String.join(",", condition));
        }

        if (queryObj != null) {
            sql.append("WHERE ");
            List<String> condition = new ArrayList<>();
            Field[] fields = queryObj.getClass().getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                Object val = field.get(queryObj);
                condition.add(fieldName + " = " + val);
            }
        }

        sql.append(";");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql.toString());
        JSONArray jsonArray = Utils.convertToJSON(rs);
        return new DBResponseData(200, "success", jsonArray.toString());
    }
}
