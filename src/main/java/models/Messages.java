package models;

public class Messages {

    String id;
    String message;
    String sender;
    String date_time;
    String timestamp;

    public Messages(String id, String message, String sender, String date_time, String timestamp) {
        this.id = id;
        this.message = message;
        this.sender = sender;
        this.date_time = date_time;
        this.timestamp = timestamp;
    }

    public Messages() {
    }

}
