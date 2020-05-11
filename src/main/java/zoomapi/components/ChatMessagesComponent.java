package zoomapi.components;

import utils.ApiClient;
import utils.StatusCodes;
import utils.Utils;
import zoomapi.components.componentRequestData.SendChatMessageRequest;
import zoomapi.components.componentRequestData.UpdateMessageRequest;
import zoomapi.components.componentResponseData.ChannelResponseData.ListUserChatMessagesResponse;
import zoomapi.components.componentResponseData.ChatMessagesResponseData.SendChatMessageResponse;

import java.util.Map;

public class ChatMessagesComponent extends BaseComponent {

    public ChatMessagesComponent(String baseUri, String token) {
        super(baseUri, token);
    }

    public ListUserChatMessagesResponse listUserChatMessages(Map<String, String> params) {
        ListUserChatMessagesResponse responseData = null;
        try {
            Utils.requireKeys(params, new String[]{"userId"}, false);
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/chat/users/%s/messages", params,
                    new String[]{"userId"}, true);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, ListUserChatMessagesResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public SendChatMessageResponse sendChatMessage(Map<String, String> params, SendChatMessageRequest data) {
        SendChatMessageResponse responseData = null;
        try {
            Utils.requireKeys(params, new String[]{"userId"}, false);
            if (data.getMessage() == null) {
                throw new Exception("parameter 'message' not set");
            }
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/chat/users/%s/messages", params,
                    new String[]{"userId"}, false);
            String dataString = GSON.toJson(data);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().postRequest(url, params, dataString, null, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, SendChatMessageResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public int updateChatMessage(Map<String, String> params, UpdateMessageRequest data) {
        int statusCode = -1;
        try {
            Utils.requireKeys(params, new String[]{"messageId"}, false);
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/chat/users/me/messages/%s", params,
                    new String[]{"messageId"}, false);
            String dataString = GSON.toJson(data);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().putRequest(url, params, dataString, null, null);
            Map responseMap = GSON.fromJson(response, Map.class);
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
        try {
            Utils.requireKeys(params, new String[]{"messageId"}, false);
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/chat/users/me/messages/%s", params,
                    new String[]{"messageId"}, true);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            Map responseMap = GSON.fromJson(response, Map.class);
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
