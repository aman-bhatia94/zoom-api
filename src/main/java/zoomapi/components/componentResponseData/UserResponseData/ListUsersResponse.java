package zoomapi.components.componentResponseData.UserResponseData;

import zoomapi.components.componentResponseData.Response;
import zoomapi.components.componentResponseData.Users;

import java.util.ArrayList;

public class ListUsersResponse extends Response {

    ArrayList<Users> users;

    public ArrayList<Users> getUsers() {
        return users;
    }


}
