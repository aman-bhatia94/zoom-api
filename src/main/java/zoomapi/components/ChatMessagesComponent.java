package zoomapi.components;

import utils.ApiClient;
import utils.Throttled;
import utils.Utils;
import zoomapi.components.componentRequestData.SendChatMessageRequest;
import zoomapi.components.componentRequestData.UpdateMessageRequest;
import zoomapi.components.componentResponseData.ChatMessagesResponseData.SendChatMessageResponse;
import zoomapi.components.componentResponseData.ListUserChannelsResponse;
import zoomapi.components.componentResponseData.ListUserChatMessagesResponse;

import java.util.Map;

public class ChatMessagesComponent extends BaseComponent {

    Throttled listUserThrottler = null;
    Throttled sendChatThrottler = null;
    Throttled updateMessageThrottler = null;
    Throttled deleteMessageThrottler = null;

    public ChatMessagesComponent(String baseUri, String token) {
        super(baseUri, token);
    }

    public ListUserChatMessagesResponse listUserChatMessages(Map<String, String> params) {
        Map responseMap = null;
        ListUserChatMessagesResponse responseData = null;
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
            System.out.println("Response: " + response);
            responseMap = gson.fromJson(response, Map.class);
            responseData = gson.fromJson(response, ListUserChatMessagesResponse.class);

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public SendChatMessageResponse sendChatMessage(Map<String, String> params, SendChatMessageRequest data) {
        Map responseMap = null;
        SendChatMessageResponse responseData = new SendChatMessageResponse();
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
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = gson.fromJson(response, SendChatMessageResponse.class);
//                System.out.println("Response: " + response);
            }
            //System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public Map<String, String> updateChatMessage(Map<String, String> params, UpdateMessageRequest data) {
        Map responseMap = null;
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
        Map responseMap = null;
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
