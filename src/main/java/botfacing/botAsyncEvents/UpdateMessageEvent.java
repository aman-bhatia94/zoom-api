package botfacing.botAsyncEvents;

import botfacing.BotEventListener;
import utils.Utils;
import zoomapi.components.ChatChannelComponent;
import zoomapi.components.ChatMessagesComponent;
import zoomapi.components.componentResponseData.ChannelData;
import zoomapi.components.componentResponseData.ChannelResponseData.ListUserChannelsResponse;
import zoomapi.components.componentResponseData.ChannelResponseData.ListUserChatMessagesResponse;
import zoomapi.components.componentResponseData.Message;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UpdateMessageEvent extends Thread {

    HashMap<String, Message> messageHashMap;
    private BotEventListener listener;
    private String channelName;
    private String channelId;
    //creating a map of userchatmessage response, we will check timestamps in this list
    private String date;
    private ChatMessagesComponent chatMessagesComponent;
    private String baseURL;
    private String accessToken;
    private String clientId;

    public void setEventListener(BotEventListener listener, String channelName, String baseURL, String accessToken, String clientId) throws Exception {
        this.listener = listener;
        this.channelName = channelName;
        this.chatMessagesComponent = new ChatMessagesComponent(baseURL, accessToken, clientId);
        this.baseURL = baseURL;
        this.accessToken = accessToken;
        this.clientId = clientId;
        init();
    }

    //sets the required channel
    private void init() throws Exception {
        //list user channels
        date = Utils.getTimeStampString(LocalDateTime.now(ZoneOffset.UTC));
        ChatChannelComponent chatChannelComponent = new ChatChannelComponent(baseURL, accessToken, clientId);
        Map<String, String> params = new HashMap<>();
        params.put("userId", "me");
        ListUserChannelsResponse response = chatChannelComponent.listUserChannels(params);
        List<ChannelData> channelDataList = response.getChannels();
        for (ChannelData data : channelDataList) {
            if (data.getName().equalsIgnoreCase(channelName)) {
                channelId = data.getId();
                break;
            }
        }
    }

    @Override
    public void run() {

        while (true) {

            Map<String, String> params = new HashMap<>();
            params.put("userId", "me");
            params.put("to_channel", channelId);
            params.put("date", date);
            params.put("page_size", "50");
            ListUserChatMessagesResponse messagesResponse = chatMessagesComponent.listUserChatMessages(params);
            if (messagesResponse != null && messagesResponse.getMessages() != null && messagesResponse.getMessages().size() > 0) {
                //first check if messae hashmap is already populated or not
                if (messageHashMap == null) {
                    messageHashMap = new HashMap<>();
                    //iterate over messages in messageResponse and add to the map
                    for (Message message : messagesResponse.getMessages()) {
                        messageHashMap.put(message.getId(), message);
                    }
                }
                //Now check if timestamps are different for stored messages
                List<Message> updatedMessages = messagesResponse.getMessages().parallelStream().filter(obj -> {
                    if (messageHashMap.containsKey(obj.getId())) {
                        //we first see if our map record contains this data, if not we add
                        //but if it does, we will check if timestamp of the message is same or different
                        //if it is different, it means we have an updated message
                        Message previousMessage = messageHashMap.get(obj.getId());
                        return !previousMessage.getMessage().equals(obj.getMessage());
                    } else {
                        messageHashMap.put(obj.getId(), obj);
                        return false;
                    }
                }).collect(Collectors.toList());
                if (listener != null && updatedMessages.size() > 0) {
                    for (Message msg : updatedMessages) {
                        listener.onMessageUpdateEvent(new Object[]{msg});
                    }
                }
                //This thread sleeps for 10 seconds between every check
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
