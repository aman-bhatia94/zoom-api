package bots;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.ini4j.Ini;
import xyz.dmanchon.ngrok.client.NgrokTunnel;
import zoomapi.OAuthZoomClient;
import zoomapi.components.ChatChannelComponent;
import zoomapi.components.ChatMessagesComponent;
import zoomapi.components.UserComponent;
import zoomapi.components.componentRequestData.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

        Scanner input = new Scanner(System.in);
        BotM2.parseBotIniFile("src/main/java/bots/bot.ini");
        BotM2.connect(); //creating ngrock tunnel
        BotM2.display();
        OAuthZoomClient client = new OAuthZoomClient(clientId, clientSecret, port, redirect_url, browserPath);
        //user_response = client.user.get(id='me')

        final String baseURL = "https://api.zoom.us/v2";
        final String accessToken = client.getAccessToken();

        /*
        //get User details
        UserComponent userComponent = new UserComponent(baseURL, accessToken);
        Map<String, String> params = new HashMap<>();
        params.put("userId", "me");
        Map<String, String> getUserReponse = userComponent.getUser(params);
        String email = getUserReponse.get("email");

        //create channel
        System.out.println("Create Channel");
        System.out.println("Enter name: ");
        String channelName = input.nextLine();
        ChatChannelComponent chatChannelComponent = new ChatChannelComponent(baseURL, accessToken);
        params = new HashMap<>();
        params.put("userId", "me");
        Member member = new Member();
        member.setEmail(email);
        List<Member> memberList = new ArrayList<>();
        memberList.add(member);
        CreateChannelRequest createChannelRequest = new CreateChannelRequest();
        createChannelRequest.setMembers(memberList);
        createChannelRequest.setName(channelName);
        createChannelRequest.setType(1);
        Map<String, String> createChannelResponse = chatChannelComponent.createChannel(params, createChannelRequest);
        System.out.println("Response: " + createChannelResponse);

        System.out.println("Press enter to continue: ");
        input.next();

        //update channel
        System.out.println("Update Channel");
        params = new HashMap<>();
        String newChannelId = createChannelResponse.get("id");
        params.put("channelId", newChannelId);
        UpdateChannelRequest updateChannelRequest = new UpdateChannelRequest();
        System.out.println("Enter new channel name");
        String newChannelName = input.next();
        updateChannelRequest.setName(newChannelName);
        System.out.println("Response: " + chatChannelComponent.updateChannel(params, updateChannelRequest));

        System.out.println("Press enter to continue: ");
        input.next();

        //list user channels
        System.out.println("List User Channels");
        params = new HashMap<>();
        params.put("userId", "me");
        System.out.println("Response: " + chatChannelComponent.listUserChannels(params));

        System.out.println("Press enter to continue: ");
        input.next();

        //get channel
        String publicChannelId = "109ab13498c64fd5911a42be1076ea6b";
        params = new HashMap<>();
        params.put("channelId", publicChannelId);
        System.out.println("Response: " + chatChannelComponent.getChannel(params));

        System.out.println("Press enter to continue: ");
        input.next();

        //send chat message
        params = new HashMap<>();
        ChatMessagesComponent chatMessagesComponent = new ChatMessagesComponent(baseURL, accessToken);
        SendChatMessageRequest sendChatMessageRequest = new SendChatMessageRequest();
        System.out.println("Enter Message: ");
        String msg = input.next();
        sendChatMessageRequest.setMessage(msg);
        sendChatMessageRequest.setTo_channel(newChannelId);
        Map<String, String> sendChatResponse = chatMessagesComponent.sendChatMessage(params, sendChatMessageRequest);
        System.out.println("Response: " + sendChatResponse);

        System.out.println("Press enter to continue: ");
        input.next();

        //lists the chat messages
        params = new HashMap<>();
        params.put("userId", "me");
        params.put("to_channel", newChannelId);
        System.out.println("Response: " + chatMessagesComponent.listUserChatMessages(params));

        System.out.println("Press enter to continue: ");
        input.next();

        //update message
        params = new HashMap<>();
        String msgId = sendChatResponse.get("id");
        params.put("messageId", msgId);
        UpdateMessageRequest updateMessageRequest = new UpdateMessageRequest();
        System.out.println("Enter new msg: ");
        String newMsg = input.next();
        updateMessageRequest.setMessage(newMsg);
        updateMessageRequest.setTo_channel(newChannelId);
        System.out.println("Response: " + chatMessagesComponent.updateChatMessage(params, updateMessageRequest));

        System.out.println("Press enter to continue: ");
        input.next();

        //delete message
        params = new HashMap<>();
        params.put("to_channel", newChannelId);
        params.put("messageId", msgId);
        System.out.println("Response: " + chatMessagesComponent.deleteMessage(params));

        System.out.println("Press enter to continue: ");
        input.next();

        //invite channel members
        params = new HashMap<>();
        params.put("channelId", newChannelId);
        member = new Member();
        System.out.println("Enter email: ");
        String memberEmail = input.next();
        member.setEmail(memberEmail);
        memberList = new ArrayList<>();
        memberList.add(member);
        InviteChannelMembersRequest data = new InviteChannelMembersRequest();
        data.setMembers(memberList);
        System.out.println("Response: " + chatChannelComponent.inviteChannelMembers(params, data));

        System.out.println("Press enter to continue: ");
        input.next();

        //list channel members
        params = new HashMap<>();
        params.put("channelId", newChannelId);
        Map<String, String> response = chatChannelComponent.listChannelMember(params);
        System.out.println("Response: " + response);
        System.out.println("Press enter to continue: ");
        input.next();

        //remove channel member
        params = new HashMap<>();
        System.out.println("Enter id to be removed: ");
        String memberIdToBeRemoved = input.next();
        params.put("channelId", newChannelId);
        params.put("memberId", memberIdToBeRemoved);
        System.out.println("Response: " + chatChannelComponent.removeMember(params));

        System.out.println("Press enter to continue: ");
        input.next();

        //join channel
        params = new HashMap<>();
        params.put("channelId", publicChannelId);
        System.out.println("Response: " + chatChannelComponent.joinChannel(params));

        System.out.println("Press enter to continue: ");
        input.next();

        //leave channel
        params = new HashMap<>();
        params.put("channelId", publicChannelId);
        System.out.println("Response: " + chatChannelComponent.leaveChannel(params));

        System.out.println("Press enter to continue: ");
        input.next();

        //delete channel
        params = new HashMap<>();
        params.put("channelId", newChannelId);
        System.out.println("Response: " + chatChannelComponent.deleteChannel(params));


         */

        //-----------------Milestone 3 testing------------------------------
        //list user channels
        ChatChannelComponent chatChannelComponent = new ChatChannelComponent(baseURL, accessToken);
        System.out.println("List User Channels");
        Map<String, String> params = new HashMap<>();
        params = new HashMap<>();
        params.put("userId", "me");
        System.out.println("Response: " + chatChannelComponent.listUserChannels(params));

        System.out.println("Press enter to continue: ");
        input.next();

    }
}
