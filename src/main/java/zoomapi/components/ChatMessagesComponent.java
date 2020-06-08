package zoomapi.components;

import models.Messages;
import services.data.MessagesRequestData;
import utils.ApiClient;
import utils.DatabaseConnection;
import utils.StatusCodes;
import utils.Utils;
import zoomapi.components.componentRequestData.SendChatMessageRequest;
import zoomapi.components.componentRequestData.UpdateMessageRequest;
import zoomapi.components.componentResponseData.ChannelResponseData.ListUserChatMessagesResponse;
import zoomapi.components.componentResponseData.ChatMessagesResponseData.SendChatMessageResponse;
import zoomapi.components.componentResponseData.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ChatMessagesComponent extends BaseComponent {

    public ChatMessagesComponent(String baseUri, String token, String clientId) throws Exception {
        super(baseUri, token, clientId);
    }

    public ListUserChatMessagesResponse listUserChatMessages(Map<String, String> params) {
        ListUserChatMessagesResponse responseData = null;
        try {
            //get cached data
            String dbResponse = DatabaseConnection.getDataDMLService().get(new MessagesRequestData(null, null)).getResponseData();
            Messages[] messagesDBResponseData = GSON.fromJson(dbResponse, Messages[].class);
            ListUserChatMessagesResponse dbResponseData = new ListUserChatMessagesResponse();
            dbResponseData.setMessages(MapMessagesModelToMessage(Arrays.asList(messagesDBResponseData)));
            if (!DatabaseConnection.isTimeOut(DatabaseConnection.TimestampModeEnum.MessagesTimestamp)) {
                //if not timeout then return data as it is
                return dbResponseData;
            }
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
                ListUserChatMessagesResponse apiResponseData = GSON.fromJson(response, ListUserChatMessagesResponse.class);
                //to return new results
                responseData = apiResponseData;
                List<Message> toBeAdded = new ArrayList<>(apiResponseData.getMessages());
                toBeAdded.removeAll(dbResponseData.getMessages());
                //find new records and cache them
                for (Message message : toBeAdded) {
                    Messages messages = new Messages(null, message.getMessage(), message.getId(), message.getDate_time(), message.getTimestamp());
                    DatabaseConnection.getDataDMLService().insert(new MessagesRequestData(messages, null));
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return responseData;
    }

    private List<Message> MapMessagesModelToMessage(List<Messages> messagesList) {
        List<Message> list = new ArrayList<>();
        if (messagesList == null || messagesList.size() == 0) return list;
        for (Messages messageModel : messagesList) {
            Message message = new Message();
            message.setId(messageModel.getMessage_id());
            message.setMessage(messageModel.getMessage());
            message.setDate_time(messageModel.getDate_time());
            message.setTimestamp(messageModel.getTimestamp());
            list.add(message);
        }
        return list;
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
            ex.printStackTrace();
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
