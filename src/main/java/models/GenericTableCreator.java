package models;

import java.lang.reflect.Field;

//This class has a generic method that is used to create SQL queries for table creation
public class GenericTableCreator {

    public <T> String create(T table) {

        Class tableClass = table.getClass();

        Field[] fields = tableClass.getDeclaredFields();

        StringBuilder sql = new StringBuilder();
        String tableName = tableClass.getName().split("\\.")[1];
        sql.append("CREATE TABLE IF NOT EXISTS ").append(tableName.toLowerCase()).append(" ( ").append("id INTEGER PRIMARY KEY AUTOINCREMENT, ");


        int num_fields = fields.length;

        for (int i = 0; i < num_fields; i++) {

            Field field = fields[i];
            String fieldType = String.valueOf(field.getType());
            String column_type = "";
            if (fieldType.equalsIgnoreCase("int") || fieldType.contains("Integer")) {
                column_type = "INTEGER";
            } else if (fieldType.equalsIgnoreCase("String") || fieldType.contains("String")) {
                column_type = "VARCHAR(100)";
            }

            if (field.getName().equalsIgnoreCase("id")) {
                //we have already added id, and by convention it is PRIMARY KEY AUTO INCREMENT
                continue;
            }
            String columnToAppend;
            if (i == num_fields - 1) {
                columnToAppend = field.getName() + " " + column_type + " NOT NULL ); ";
            } else {
                columnToAppend = field.getName() + " " + column_type + " NOT NULL, ";
            }
            sql.append(columnToAppend);
        }
        return sql.toString();
    }

}
