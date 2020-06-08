package models;

public class Credentials {

    Integer id;
    String client_id;
    String oauth_token;
    String time_stamp;

    public Credentials() {
    }

    public Credentials(Integer id, String client_id, String oauth_token, String time_stamp) {
        this.id = id;
        this.client_id = client_id;
        this.oauth_token = oauth_token;
        this.time_stamp = time_stamp;
    }

    public Integer getId() {
        return id;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getOauth_token() {
        return oauth_token;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }
}
