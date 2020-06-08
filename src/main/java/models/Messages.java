package models;

public class Messages {

    Integer id;
    String message;
    String message_id;
    //    String sender;
    String date_time;
    String timestamp;

    public Messages(Integer id, String message, String message_id, String date_time, String timestamp) {
        this.id = id;
        this.message = message;
        this.message_id = message_id;
        this.date_time = date_time;
        this.timestamp = timestamp;
    }

//    public Messages(Integer id, String message, String message_id, String sender, String date_time, String timestamp) {
//        this.id = id;
//        this.message = message;
//        this.message_id = message_id;
//        this.sender = sender;
//        this.date_time = date_time;
//        this.timestamp = timestamp;
//    }

    public Messages() {
    }

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage_id() {
        return message_id;
    }

//    public String getSender() {
//        return sender;
//    }

    public String getDate_time() {
        return date_time;
    }

    public String getTimestamp() {
        return timestamp;
    }

}
