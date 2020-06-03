package models;

public class Messages implements CreateTableInterface {

    String id;
    String message;
    String sender;
    String date_time;
    String timestamp;

    @Override
    public String create() {
        //TODO
        return null;
    }
}
