package zoomapi.components.componentRequestData;

public class CreateUserRequest {
    final String action;
    UserInfo user_info;

    public CreateUserRequest() {
        action = "create";
        user_info = new UserInfo();
    }

    public void setUser_info(UserInfo user_info) {
        this.user_info = user_info;
    }
}
