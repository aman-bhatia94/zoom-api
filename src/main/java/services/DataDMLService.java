package services;

import services.data.DBRequestData;
import services.data.DBResponseData;

import java.lang.reflect.Field;
import java.sql.Connection;

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

        System.out.println("Query: " + queryObj);

        Field[] fields = queryObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Object val = field.get(queryObj);
            System.out.println("Name: " + fieldName + "\nval: " + val.toString());
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
