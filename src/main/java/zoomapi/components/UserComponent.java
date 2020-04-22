package zoomapi.components;

import utils.ApiClient;
import utils.Utils;
import zoomapi.components.componentRequestData.CreateUserRequest;
import zoomapi.components.componentRequestData.UpdateUserRequest;

import java.io.IOException;
import java.util.Map;

public class UserComponent extends BaseComponent {

    public UserComponent(String baseUri, String token) {
        super(baseUri, token);
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

    public void listUsers(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/users";
            if (!params.containsKey("page_size"))
                params.put("page_size", "10");
            if (!params.containsKey("page_number"))
                params.put("page_number", "1");
            if (!params.containsKey("status"))
                params.put("status", "active");
            url = Utils.appendToUrl(url, params);
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void deleteUser(Map<String, String> params) {
        try {
            if (!params.containsKey("userId")) {
                throw new Exception("userId was not found");
            }
            String url = ApiClient.getApiClient().getBaseUri() + "/users/%s";
            url = String.format(url, params.get("userId"));
            if (!params.containsKey("action"))
                params.put("action", "disassociate");
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
