package zoomapi.components;

import utils.ApiClient;
import utils.StatusCodes;
import utils.Throttled;
import utils.Utils;
import zoomapi.components.componentRequestData.SendChatMessageRequest;
import zoomapi.components.componentRequestData.UpdateMessageRequest;
import zoomapi.components.componentResponseData.ChannelResponseData.ListUserChatMessagesResponse;
import zoomapi.components.componentResponseData.ChatMessagesResponseData.SendChatMessageResponse;

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
            Map responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = gson.fromJson(response, ListUserChatMessagesResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public SendChatMessageResponse sendChatMessage(Map<String, String> params, SendChatMessageRequest data) {
        SendChatMessageResponse responseData = null;
        if (sendChatThrottler == null) {
            sendChatThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/users/%s/messages";
            Utils.requireKeys(params, new String[]{"userId"}, false);
            url = String.format(url, params.get("userId"));
            if (data.getMessage() == null) {
                throw new Exception("parameter 'message' not set");
            }
            String dataString = gson.toJson(data);
            sendChatThrottler.throttle();
            String response = ApiClient.getApiClient().postRequest(url, params, dataString, null, null);
            Map responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = gson.fromJson(response, SendChatMessageResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public int updateChatMessage(Map<String, String> params, UpdateMessageRequest data) {
        int statusCode = -1;
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
            Map responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                statusCode = StatusCodes.MESSAGE_UPDATED_SUCCESSFULLY;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return statusCode;
    }

    public int deleteMessage(Map<String, String> params) {
        int statusCode = -1;
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
            Map responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                statusCode = StatusCodes.MESSAGE_UPDATED_SUCCESSFULLY;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return statusCode;
    }
}
