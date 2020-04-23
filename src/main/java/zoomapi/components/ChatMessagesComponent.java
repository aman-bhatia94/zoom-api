package zoomapi.components;

import utils.ApiClient;
import utils.Throttled;
import utils.Utils;
import zoomapi.components.componentRequestData.SendChatMessageRequest;
import zoomapi.components.componentRequestData.UpdateMessageRequest;

import java.util.Map;

public class ChatMessagesComponent extends BaseComponent {

    Throttled listUserThrottler = null;
    Throttled sendChatThrottler = null;
    Throttled updateMessageThrottler = null;
    Throttled deleteMessageThrottler = null;

    public ChatMessagesComponent(String baseUri, String token) {
        super(baseUri, token);
    }

    public Map<String, String> listUserChatMessages(Map<String, String> params) {
        Map<String, String> responseMap = null;
        if (listUserThrottler == null) {
            listUserThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/users/%s/messages";
            Utils.requireKeys(params, new String[]{"userId"}, false);
            url = String.format(url, params.get("userId"));
            url = Utils.appendToUrl(url, params);
            listUserThrottler.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public Map<String, String> sendChatMessage(Map<String, String> params, SendChatMessageRequest data) {
        Map<String, String> responseMap = null;
        if (sendChatThrottler == null) {
            sendChatThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/users/%s/messages";
            Utils.requireKeys(params, new String[]{"userId"}, false);
            url = String.format(url, params.get("userId"));
            String dataString = gson.toJson(data);
            sendChatThrottler.throttle();
            String response = ApiClient.getApiClient().postRequest(url, params, dataString, null, null);
            responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public Map<String, String> updateChatMessage(Map<String, String> params, UpdateMessageRequest data) {
        Map<String, String> responseMap = null;
        if (updateMessageThrottler == null) {
            updateMessageThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/users/me/messages/%s";
            Utils.requireKeys(params, new String[]{"messageId"}, false);
            url = String.format(url, params.get("messageId"));
            String dataString = gson.toJson(data);
            updateMessageThrottler.throttle();
            String response = ApiClient.getApiClient().putRequest(url, params, dataString, null, null);
            responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public Map<String, String> deleteMessage(Map<String, String> params) {
        Map<String, String> responseMap = null;
        if (deleteMessageThrottler == null) {
            deleteMessageThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/users/me/messages/%s";
            Utils.requireKeys(params, new String[]{"messageId"}, false);
            url = String.format(url, params.get("messageId"));
            url = Utils.appendToUrl(url, params);
            deleteMessageThrottler.throttle();
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }
}
