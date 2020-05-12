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

    private BotEventListener listener;
    private String channelName;
    private String channelId;
    //creating a map of userchatmessage response, we will check timestamps in this list
    private LocalDateTime latestTimeStamp;
    HashMap<String, Message> messageHashMap;
    private ChatMessagesComponent chatMessagesComponent;
    private String baseURL;
    private String accessToken;

    public void setEventListener(BotEventListener listener, String channelName, String baseURL, String accessToken) {
        this.listener = listener;
        this.channelName = channelName;
        this.chatMessagesComponent = new ChatMessagesComponent(baseURL, accessToken);
        this.baseURL = baseURL;
        this.accessToken = accessToken;
        init();
    }

    //sets the required channel
    private void init() {
        //list user channels
        ChatChannelComponent chatChannelComponent = new ChatChannelComponent(baseURL, accessToken);
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

        while(true){

            if (latestTimeStamp == null) {
                latestTimeStamp = LocalDateTime.now(ZoneOffset.UTC);
            }


            Map<String, String> params = new HashMap<>();
            params.put("userId", "me");
            params.put("to_channel", channelId);
            params.put("date", Utils.getTimeStampString(latestTimeStamp));
            params.put("page_size", "1");
            ListUserChatMessagesResponse messagesResponse = chatMessagesComponent.listUserChatMessages(params);
            if (messagesResponse != null && messagesResponse.getMessages() != null && messagesResponse.getMessages().size() > 0) {
                //first check if messae hashmap is already populated or not
                if(messageHashMap == null){
                    //iterate over messages in messageResponse and add to the map
                    for(Message message : messagesResponse.getMessages()){
                        messageHashMap.put(message.getId(),message);
                    }
                }
                //Now check if timestamps are different for stored messages
                List<Message> updatedMessages = messagesResponse.getMessages().parallelStream().filter(obj -> {
                    if(messageHashMap.containsKey(obj.getId())){
                        //we first see if our map record contains this data, if not we add
                        //but if it does, we will check if timestamp of the message is same or different
                        //if it is different, it means we have an updated message
                        Message previousMessage = messageHashMap.get(obj.getId());
                        if(!previousMessage.getTimestamp().equals(obj.getTimestamp())){
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                    else{
                        messageHashMap.put(obj.getId(),obj);
                        return false;
                    }
                }).collect(Collectors.toList());
                if (listener != null && updatedMessages.size() > 0) {
                    listener.onNewMessageEvent(new Object[]{updatedMessages});
                    //latestTimeStamp = LocalDateTime.now(ZoneOffset.UTC);
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
