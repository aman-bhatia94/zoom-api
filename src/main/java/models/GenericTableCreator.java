package models;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//This class has a generic method that is used to create SQL queries for table creation
public class GenericTableCreator {

    public <T> String create(T table){

        Class tableClass = table.getClass();
        try {
            Method method = tableClass.getDeclaredMethod("create");
            Object sql = method.invoke(table);
            String sqlToReturn = (String) sql;
            return sqlToReturn;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
