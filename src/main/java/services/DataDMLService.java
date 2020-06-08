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
        Field[] mainFields = table.getFields();
        Object queryObj = null;
        String tableName = null;
        for (Field mainField : mainFields) {
            if (mainField.getName().contains("queryValues")) {
                queryObj = mainField.get(requestData);
            } else if (mainField.getName().contains("tableName")) {
                tableName = mainField.get(requestData).toString();
            }
        }
        String finalSql;
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM ").append(tableName);
        if (queryObj != null) {
            Field[] fields = queryObj.getClass().getDeclaredFields();
            sql.append(" WHERE ");
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object val = field.get(queryObj);
                if (val == null) continue;
                sql.append(fieldName).append(" = '").append(val.toString()).append("' AND ");
            }
            String sqlToRun = sql.toString();
            int lastIndexOfAnd = sqlToRun.lastIndexOf("AND");
            finalSql = sqlToRun.substring(0, lastIndexOfAnd) + ";";
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
        sql.append("INSERT INTO ").append(tableName).append("(");
        List<String> fieldNames = new ArrayList<>();
        List<String> values = new ArrayList<>();
        Field[] fields = queryObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object val = field.get(queryObj);
            if (val == null) continue;
            fieldNames.add(fieldName);
            values.add("'" + val.toString() + "'");
        }

        sql.append(String.join(",", fieldNames));
        sql.append(") ");
        sql.append("values (");
        sql.append(String.join(",", values));
        sql.append(")");
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql.toString());
        return new DBResponseData(200, null, null);
    }

    public <T> DBResponseData delete(T requestData) throws Exception {
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
        String finalSql;
        StringBuilder sql = new StringBuilder();

        sql.append("DELETE FROM ").append(tableName);
        Field[] fields = queryObj.getClass().getDeclaredFields();
        if (queryObj != null) {
            sql.append(" WHERE ");
            for (Field field : fields) {
                String fieldName = field.getName();
                Object val = field.get(queryObj);
                sql.append(fieldName).append(" = ").append(val.toString()).append(" AND ");
                System.out.println("Name: " + fieldName + "\nval: " + val.toString());
            }
            String sqlToRun = sql.toString();
            int lastIndexOfAnd = sqlToRun.lastIndexOf("AND");
            finalSql = sqlToRun.substring(0, lastIndexOfAnd) + ";";
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

        sql.append("UPDATE ").append(tableName).append(" SET ");

        if (newValuesObj != null) {
            Field[] fields = newValuesObj.getClass().getDeclaredFields();
            List<String> condition = new ArrayList<>();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object val = field.get(newValuesObj);
                if (val == null) continue;
                condition.add(fieldName + " = '" + val + "'");
            }
            sql.append(String.join(",", condition));
        }

        if (queryObj != null) {
            sql.append("WHERE ");
            List<String> condition = new ArrayList<>();
            Field[] fields = queryObj.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object val = field.get(queryObj);
                if (val == null) continue;
                condition.add(fieldName + " = '" + val + "'");
            }
            sql.append(String.join(",", condition));
        }

        sql.append(";");
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql.toString());
        return new DBResponseData(200, "success", null);
    }
}
