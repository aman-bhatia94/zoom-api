package zoomapi.components;

import com.google.gson.Gson;
import utils.ApiClient;
import zoomapi.components.componentRequestData.CreateUserRequest;
import zoomapi.components.componentRequestData.UpdateUserRequest;

import java.io.IOException;
import java.util.Map;

public class UserComponent {

    //    ApiClient client;
    private static final Gson gson = new Gson();
    String baseUri;
    String token;

    public UserComponent(String baseUri, String token) {
        ApiClient.init(baseUri, token);
    }

    public void getUser(Map<String, String> params) {
        try {
            if (!params.containsKey("userId")) {
                throw new Exception("userId was not found");
            }
            String url = ApiClient.getApiClient().getBaseUri() + "/users/%s";
            url = String.format(url, params.get("userId"));
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void createUser(Map<String, String> params, CreateUserRequest data) throws IOException, InterruptedException {
        String url = ApiClient.getApiClient().getBaseUri() + "/users";
        String dataString = gson.toJson(data);
        String response = ApiClient.getApiClient().postRequest(url, params, dataString, null, null);
        System.out.println("Response: " + response);
    }

    public void updateUser(Map<String, String> params, UpdateUserRequest data) throws IOException, InterruptedException {
        String url = ApiClient.getApiClient().getBaseUri() + "/users/%s";
        url = String.format(url, params.get("userId"));
        String dataString = gson.toJson(data);
        String response = ApiClient.getApiClient().patchRequest(url, params, dataString, null, null);
        System.out.println("Response: " + response);
    }
}
