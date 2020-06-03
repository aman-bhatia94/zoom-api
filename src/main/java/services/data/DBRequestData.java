package services.data;

import java.util.HashMap;

public class DBRequestData {
    String tableName;
    HashMap<String, String> fieldValues;
    HashMap<String, String> newFieldValues;

    public DBRequestData(String tableName, HashMap<String, String> fieldValues, HashMap<String, String> newFieldValues) {
        this.tableName = tableName;
        this.fieldValues = fieldValues;
        this.newFieldValues = newFieldValues;
    }
}
