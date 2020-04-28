package botfacing;

import zoomapi.components.ChatChannelComponent;
import zoomapi.components.ChatMessagesComponent;
import zoomapi.components.componentRequestData.SendChatMessageRequest;
import zoomapi.components.componentResponseData.ChannelData;
import zoomapi.components.componentResponseData.ListUserChannelsResponse;

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

    public Map<String, String> sendChatMessages(String channelName, String message){

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
        for(ChannelData data : channelDataList){
            if(data.getName().equalsIgnoreCase(channelName)){
                channelData = data;
            }
        }

        if(channelData != null)
        {
            //send chat message
            ChatMessagesComponent chatMessagesComponent = new ChatMessagesComponent(baseURL, accessToken);
            SendChatMessageRequest sendChatMessageRequest = new SendChatMessageRequest();
            sendChatMessageRequest.setMessage(message);
            sendChatMessageRequest.setTo_channel(channelData.getId());
            Map<String, String> sendChatResponse = chatMessagesComponent.sendChatMessage(params, sendChatMessageRequest);
            return sendChatResponse;
        }
        else {
            //If nothing works
            return null;
        }
    }
}
