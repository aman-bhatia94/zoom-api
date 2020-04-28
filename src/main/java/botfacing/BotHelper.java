package botfacing;

import utils.Utils;
import zoomapi.components.ChatChannelComponent;
import zoomapi.components.ChatMessagesComponent;
import zoomapi.components.componentRequestData.SendChatMessageRequest;
import zoomapi.components.componentResponseData.ChannelData;
import zoomapi.components.componentResponseData.ListUserChannelsResponse;
import zoomapi.components.componentResponseData.ListUserChatMessagesResponse;
import zoomapi.components.componentResponseData.Message;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BotHelper {

    private String baseURL;
    private String accessToken;
    private String clientId;
    public String clientSecret;
    public String port;
    public String browserPath;
    public String redirect_url;

    public BotHelper(String baseURL, String accessToken, String clientId, String clientSecret, String port, String browserPath, String redirect_url) {
        this.baseURL = baseURL;
        this.accessToken = accessToken;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.port = port;
        this.browserPath = browserPath;
        this.redirect_url = redirect_url;
    }

    public Map<String, String> sendChatMessages(String channelName, String message) {

        //list user channels
        ChatChannelComponent chatChannelComponent = new ChatChannelComponent(baseURL, accessToken);
        //System.out.println("List User Channels");
        Map<String, String> params = new HashMap<>();
        params = new HashMap<>();
        params.put("userId", "me");
        ListUserChannelsResponse response = chatChannelComponent.listUserChannels(params);
        //System.out.println("Response: " + chatChannelComponent.listUserChannels(params));
        List<ChannelData> channelDataList = response.getChannels();

        ChannelData channelData = null;

        //iterating over channels list to get required channel data
        for (ChannelData data : channelDataList) {
            if (data.getName().equalsIgnoreCase(channelName)) {
                channelData = data;
            }
        }

        if (channelData != null) {
            //send chat message
            ChatMessagesComponent chatMessagesComponent = new ChatMessagesComponent(baseURL, accessToken);
            SendChatMessageRequest sendChatMessageRequest = new SendChatMessageRequest();
            sendChatMessageRequest.setMessage(message);
            sendChatMessageRequest.setTo_channel(channelData.getId());
            Map<String, String> sendChatResponse = chatMessagesComponent.sendChatMessage(params, sendChatMessageRequest);
            return sendChatResponse;
        } else {
            //If nothing works
            return null;
        }
    }

    public List<Message> getChatHistory(String channelName, LocalDate fromDate, LocalDate toDate) {
        List<Message> chatHistory = new ArrayList<>();
        try {
            ChatChannelComponent chatChannelComponent = new ChatChannelComponent(baseURL, accessToken);
            ChatMessagesComponent chatMessagesComponent = new ChatMessagesComponent(baseURL, accessToken);
            Map<String, String> params = new HashMap<>();
            params.put("userId", "me");
            ListUserChannelsResponse response = chatChannelComponent.listUserChannels(params);
            if (response == null || response.getChannels() == null) {
                throw new Exception("User is not associated with any channels");
            }
            List<ChannelData> channelDataList = response.getChannels();
            ChannelData channelData = channelDataList.stream()
                    .filter(channel -> channel.getName().equals(channelName))
                    .findFirst()
                    .orElse(null);
            if (channelData == null) {
                throw new Exception("The channel name was not found!");
            }
            //todo please check if more than one channel with same name exists

            for (LocalDate date = fromDate; fromDate.equals(date); date = date.plusDays(1)) {
                String dateString = Utils.dateToString(date);
                params.put("to_channel", channelData.getId());
                params.put("date", dateString);
                //todo page_size do for all pages
                ListUserChatMessagesResponse listUserChatMessagesResponse = chatMessagesComponent.listUserChatMessages(params);
                chatHistory.addAll(listUserChatMessagesResponse.getMessages());
            }
            System.out.println("All messages: " + chatHistory);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return chatHistory;
    }


}
