package zoomapi.components;

import utils.ApiClient;
import utils.Utils;
import zoomapi.components.componentRequestData.SendChatMessageRequest;
import zoomapi.components.componentRequestData.UpdateMessageRequest;

import java.util.Map;

public class ChatMessagesComponent extends BaseComponent {

    public ChatMessagesComponent(String baseUri, String token) {
        super(baseUri, token);
    }

    public void listUserChatMessages(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/users/%s/messages";
            url = String.format(url, params.get("userId"));
            url = Utils.appendToUrl(url, params);
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void sendChatMessage(Map<String, String> params, SendChatMessageRequest data) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/users/%s/messages";
            url = String.format(url, params.get("userId"));
            String dataString = gson.toJson(data);
            String response = ApiClient.getApiClient().postRequest(url, params, dataString, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void updateChatMessage(Map<String, String> params, UpdateMessageRequest data) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/users/me/messages/%s";
            url = String.format(url, params.get("messageId"));
            String dataString = gson.toJson(data);
            String response = ApiClient.getApiClient().putRequest(url, params, dataString, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void deleteMessage(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/users/me/messages/%s";
            url = String.format(url, params.get("messageId"));
            url = Utils.appendToUrl(url, params);
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
