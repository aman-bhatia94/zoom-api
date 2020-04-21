package zoomapi.components;

import com.google.gson.Gson;
import utils.ApiClient;

import java.util.Map;

public class ChatChannelComponent {

    private static final Gson gson = new Gson();

    public ChatChannelComponent(String baseUri, String token) {
        ApiClient.init(baseUri, token);
    }

    public void listUserChannels(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/users/me/channels";
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
