package zoomapi.components.componentRequestData;

public class UserInfo {
    String first_name;
    String last_name;
    String email;
    int type;

    public UserInfo() {
        first_name = null;
        last_name = null;
        email = null;
        type = 1;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


}
