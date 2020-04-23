package bots;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.ini4j.Ini;
import xyz.dmanchon.ngrok.client.NgrokTunnel;
import zoomapi.OAuthZoomClient;
import zoomapi.components.ChatChannelComponent;
import zoomapi.components.ChatMessagesComponent;
import zoomapi.components.componentRequestData.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BotM2 {

    public static String clientId;
    public static String clientSecret;
    public static String port;
    public static String browserPath;
    public static String redirect_url;
    public static NgrokTunnel tunnel;

    public static void parseBotIniFile(String filepath) {

        try {
            Ini ini = new Ini(new File(filepath));
            clientId = ini.get("OAuth", "client_id");
            clientSecret = ini.get("OAuth", "client_secret");
            port = ini.get("OAuth", "port");
            browserPath = ini.get("OAuth", "browser_path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void connect() {
        try {
            int portInteger = Integer.parseInt(port);
            tunnel = new NgrokTunnel(portInteger);
            redirect_url = tunnel.url();


        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            tunnel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static void display() {
        System.out.println("Client ID: " + clientId);
        System.out.println("Client Secret: " + clientSecret);
        System.out.println("Port: " + port);
        System.out.println("Browser Path: " + browserPath);
        System.out.println("Redirect_URL: " + redirect_url);
    }

    public static void main(String[] args) {


        BotM2.parseBotIniFile("src/main/java/bots/bot.ini");
        BotM2.connect(); //creating ngrock tunnel
        BotM2.display();
        OAuthZoomClient client = new OAuthZoomClient(clientId, clientSecret, port, redirect_url, browserPath);
        //user_response = client.user.get(id='me')

        final String baseURL = "https://api.zoom.us/v2";
        final String accessToken = client.getAccessToken();


        //create channel
        ChatChannelComponent chatChannelComponent = new ChatChannelComponent(baseURL, accessToken);
        Map<String, String> params = new HashMap<>();
        params.put("userId", "me");
        Member member = new Member();
        member.setEmail("vaishakhipilankar@gmail.com");
        List<Member> memberList = new ArrayList<>();
        memberList.add(member);
        CreateChannelRequest createChannelRequest = new CreateChannelRequest();
        createChannelRequest.setMembers(memberList);
        createChannelRequest.setName("Test_04_22");
        createChannelRequest.setType(1);
        chatChannelComponent.createChannel(params, createChannelRequest);

        //update channel
        params = new HashMap<>();
        params.put("channelId", "c8fb7d4b-c83b-4e53-a516-6b313e845286");
        UpdateChannelRequest updateChannelRequest = new UpdateChannelRequest();
        updateChannelRequest.setName("updated_04_21");
        chatChannelComponent.updateChannel(params, updateChannelRequest);

        //list user channels
        params = new HashMap<>();
        params.put("userId", "me");

        //get channel
        params = new HashMap<>();
        params.put("channelId", "c8fb7d4b-c83b-4e53-a516-6b313e845286");
        chatChannelComponent.getChannel(params);

        //send chat message
        params = new HashMap<>();
        ChatMessagesComponent chatMessagesComponent = new ChatMessagesComponent(baseURL, accessToken);
        SendChatMessageRequest sendChatMessageRequest = new SendChatMessageRequest();
        sendChatMessageRequest.setMessage("Send now");
        sendChatMessageRequest.setTo_channel("240be840-4673-4211-8e99-53e3c9ad650c");
        chatMessagesComponent.sendChatMessage(params, sendChatMessageRequest);

        //lists the chat messages
        params = new HashMap<>();
        params.put("userId", "me");
        params.put("to_channel", "240be840-4673-4211-8e99-53e3c9ad650c");
        chatMessagesComponent.listUserChatMessages(params);

        //update message
        params = new HashMap<>();
        params.put("messageId", "772f9d26-4728-4086-9b5a-e7eabc7f9408");
        UpdateMessageRequest updateMessageRequest = new UpdateMessageRequest();
        updateMessageRequest.setMessage("updated!!!!");
        updateMessageRequest.setTo_channel("240be840-4673-4211-8e99-53e3c9ad650c");
        chatMessagesComponent.updateChatMessage(params, updateMessageRequest);

        //delete message
        params = new HashMap<>();
        params.put("to_channel", "240be840-4673-4211-8e99-53e3c9ad650c");
        params.put("messageId", "772f9d26-4728-4086-9b5a-e7eabc7f9408");
        chatMessagesComponent.deleteMessage(params);

        //invite channel members
        params = new HashMap<>();
        params.put("channelId", "b2c5df35-7f9a-42df-9b5d-793ad29ebdcb");
        member = new Member();
        member.setEmail("mnbhatia63@gmail.com");
        memberList = new ArrayList<>();
        memberList.add(member);
        InviteChannelMembersRequest data = new InviteChannelMembersRequest();
        data.setMembers(memberList);
        chatChannelComponent.inviteChannelMembers(params, data);

        //list channel members
        params = new HashMap<>();
        params.put("channelId", "b2c5df35-7f9a-42df-9b5d-793ad29ebdcb");
        chatChannelComponent.listChannelMember(params);

        //remove channel member
        params = new HashMap<>();
        params.put("channelId", "b2c5df35-7f9a-42df-9b5d-793ad29ebdcb");
        params.put("memberId", "fstkfao6qpwjcvtgqkrv9a");
        chatChannelComponent.removeMember(params);

        //join channel
        params = new HashMap<>();
        params.put("channelId", "109ab13498c64fd5911a42be1076ea6b");
        chatChannelComponent.joinChannel(params);

        //leave channel
        params = new HashMap<>();
        params.put("channelId", "109ab13498c64fd5911a42be1076ea6b");
        chatChannelComponent.leaveChannel(params);

        //delete channel
        params = new HashMap<>();
        params.put("channelId", "958333e98d93482aaf0c1a080a8306ff");
        chatChannelComponent.deleteChannel(params);
    }
}
