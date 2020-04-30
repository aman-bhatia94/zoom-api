package zoomapi.components.componentResponseData.UserResponseData;

public class CreateUserResponse {

    String id;
    String first_name;
    String last_name;
    String email;
    Integer type;

    public String getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getType() {
        return type;
    }

    @Override
    public String toString() {
        return "CreateUserResponse{" +
                "id='" + id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                '}';
    }
}
