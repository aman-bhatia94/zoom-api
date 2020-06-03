package models;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

//This class has a generic method that is used to create SQL queries for table creation
public class GenericTableCreator {

    public <T> String create(T table){

        Class tableClass = table.getClass();

        Field[] fields = tableClass.getDeclaredFields();

        System.out.println("Fields: "+fields.length);

        /*String createTableSQL = "CREATE TABLE IF NOT EXISTS "+ tableClass.getName().toLowerCase() +" (\n"
                + "	id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "	channel_id VARCHAR(200) NOT NULL,\n"
                + "	channel_name VARCHAR(200) NOT NULL,\n"
                + " channel_type VARCHAR(10)  NOT NULL\n"
                + ");";

         */

        StringBuilder sql = new StringBuilder();
        String tableName = tableClass.getName().split("\\.")[1];
        sql.append("CREATE TABLE IF NOT EXISTS "+ tableName.toLowerCase() +" ( ");
        sql.append("id INTEGER PRIMARY KEY AUTOINCREMENT, ");


        int num_fields = fields.length;

        for (int i = 0; i < num_fields; i++) {


            Field field = fields[i];
            String fieldType = String.valueOf(field.getType());
            String column_type = "";
            if(fieldType.equalsIgnoreCase("int") || fieldType.contains("Integer")){
                column_type = "INTEGER";
            }
            else if(fieldType.equalsIgnoreCase("String") || fieldType.contains("String")){
                column_type = "VARCHAR(100)";
            }


            if(field.getName().equalsIgnoreCase("id")){
                //we have already added id, and by convention it is PRIMARY KEY AUTO INCREMENT
                continue;
            }
            if(i == num_fields - 1){

                String columnToAppend = field.getName()+" "+column_type+" NOT NULL ); ";
                sql.append(columnToAppend);
            }
            else{
                String columnToAppend = field.getName()+" "+column_type+" NOT NULL, ";
                sql.append(columnToAppend);
            }
        }
        return sql.toString();
    }


}
