package botfacing;

import botfacing.botAsyncEvents.NewMemberAddedEvent;
import botfacing.botAsyncEvents.NewMessageEvent;
import botfacing.botAsyncEvents.UpdateMessageEvent;
import utils.Utils;
import zoomapi.components.ChatChannelComponent;
import zoomapi.components.ChatMessagesComponent;
import zoomapi.components.componentRequestData.SendChatMessageRequest;
import zoomapi.components.componentResponseData.ChannelData;
import zoomapi.components.componentResponseData.ChannelResponseData.ListUserChannelsResponse;
import zoomapi.components.componentResponseData.ChannelResponseData.ListUserChatMessagesResponse;
import zoomapi.components.componentResponseData.ChatMessagesResponseData.SendChatMessageResponse;
import zoomapi.components.componentResponseData.Member;
import zoomapi.components.componentResponseData.Message;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class BotHelper implements BotEventListener {

    private final String baseURL;
    private final String accessToken;
    private final String clientId;
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

    public SendChatMessageResponse sendMessages(String channelName, String message) throws Exception {

        //list user channels
        ChatChannelComponent chatChannelComponent = new ChatChannelComponent(baseURL, accessToken, clientId);
        Map<String, String> params = new HashMap<>();
        params.put("userId", "me");
        ListUserChannelsResponse response = chatChannelComponent.listUserChannels(params);
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
            ChatMessagesComponent chatMessagesComponent = new ChatMessagesComponent(baseURL, accessToken, clientId);
            SendChatMessageRequest sendChatMessageRequest = new SendChatMessageRequest();
            sendChatMessageRequest.setMessage(message);
            sendChatMessageRequest.setTo_channel(channelData.getId());
            return chatMessagesComponent.sendChatMessage(params, sendChatMessageRequest);
        } else {
            //If nothing works
            return null;
        }
    }

    public List<Message> history(String channelName, LocalDate fromDate, LocalDate toDate) {
        List<Message> chatHistory = new ArrayList<>();
        try {
            ChatChannelComponent chatChannelComponent = new ChatChannelComponent(baseURL, accessToken, clientId);
            ChatMessagesComponent chatMessagesComponent = new ChatMessagesComponent(baseURL, accessToken, clientId);
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
            for (LocalDate date = fromDate; !toDate.equals(date); date = date.plusDays(1)) {
                String dateString = Utils.dateToString(date);
                params.put("to_channel", channelData.getId());
                params.put("date", dateString);
                params.put("page_size", "10");
                String nextToken = "";
                do {
                    params.put("next_page_token", nextToken);
                    ListUserChatMessagesResponse listUserChatMessagesResponse = chatMessagesComponent.listUserChatMessages(params);
                    chatHistory.addAll(listUserChatMessagesResponse.getMessages());
                    nextToken = listUserChatMessagesResponse.getNext_page_token();
                } while (nextToken != null && nextToken.length() > 0);

            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return chatHistory;
    }

    public List<Message> search(String channelName, LocalDate toDate, LocalDate fromDate, Predicate<Message> searchPredicate) {
        List<Message> messageList = new ArrayList<>();
        try {
            List<Message> history = history(channelName, fromDate, toDate);
            for (Message message : history) {
                if (searchPredicate.test(message)) {
                    messageList.add(message);
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return messageList;
    }

    public void registerNewMessageEvent(String channelName) throws Exception {
        NewMessageEvent newMessageEvent = new NewMessageEvent();
        newMessageEvent.setEventListener(this, channelName, baseURL, accessToken, clientId);
        newMessageEvent.start();
    }

    public void registerUpdateMessageEvent(String channelName) throws Exception {

        UpdateMessageEvent updateMessageEvent = new UpdateMessageEvent();
        updateMessageEvent.setEventListener(this, channelName, baseURL, accessToken, clientId);
        updateMessageEvent.start();
    }

    public void registerNewMemberAddedEvent() throws Exception {
        NewMemberAddedEvent newMemberAddedEvent = new NewMemberAddedEvent();
        newMemberAddedEvent.setEventListener(this, baseURL, accessToken, clientId);
        newMemberAddedEvent.start();
    }


    @Override
    public void onNewMessageEvent(Object[] arg) {
        Message message = (Message) arg[0];
        System.out.println("New Messages: " + message);
    }

    @Override
    public void onMessageUpdateEvent(Object[] arg) {
        Message updatedMessage = (Message) arg[0];
        System.out.println("Updated Messages" + updatedMessage);
    }

    @Override
    public void onNewChannelUserEvent(Object[] arg) {
        ChannelData channelData = (ChannelData) arg[0];
        Member channelMember = (Member) arg[1];
        System.out.println("New Member in channel (" + channelData.getName() + "): " + channelMember);
    }


}
