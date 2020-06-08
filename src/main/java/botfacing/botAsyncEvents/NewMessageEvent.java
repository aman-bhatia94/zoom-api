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

public class NewMessageEvent extends Thread {

    private BotEventListener listener;
    private String channelName;
    private String channelId;
    private LocalDateTime latestTimeStamp;
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

    private void init() {
        //list user channels
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

    public void run() {
        while (true) {
            //This thread continuously runs, and fetches the message list every 10 seconds
            try {
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
                    List<Message> newMessages = messagesResponse.getMessages().parallelStream().filter(obj -> {
                        try {
                            if (Utils.getDateTime(obj.getDate_time()).isAfter(latestTimeStamp)) {
                                return true;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return false;
                    }).collect(Collectors.toList());
                    if (listener != null && newMessages.size() > 0) {
                        for (Message msg : newMessages) {
                            listener.onNewMessageEvent(new Object[]{msg});
                        }
                        latestTimeStamp = LocalDateTime.now(ZoneOffset.UTC);
                    }
                }
                //This thread sleeps for 10 seconds between every check
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
