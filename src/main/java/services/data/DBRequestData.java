package services.data;

public class DBRequestData {
    String tableName;
    String[] fieldName;
    String[] fieldValues;

    public DBRequestData(String tableName, String[] fieldName, String[] fieldValues) {
        this.tableName = tableName;
        this.fieldName = fieldName;
        this.fieldValues = fieldValues;
    }
}
