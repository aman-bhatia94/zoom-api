package zoomapi.components;

import utils.ApiClient;

import java.util.Map;

public class UserComponent {

    ApiClient client;

    public UserComponent(String baseUri, String token) {
        client = new ApiClient(baseUri, token);
    }

    public void getUser(Map<String, String> params) {
        try {
            if (!params.containsKey("userId")) {
                throw new Exception("userId was not found");
            }

            String url = client.getBaseUri() + "/users/%s";
            url = String.format(url, params.get("userId"));
            String response = client.getRequest(url, params, null);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
