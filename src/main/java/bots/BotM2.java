package bots;

import botfacing.BotHelper;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.ini4j.Ini;
import utils.Utils;
import xyz.dmanchon.ngrok.client.NgrokTunnel;
import zoomapi.OAuthZoomClient;
import zoomapi.components.componentResponseData.Message;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

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

        BotHelper helper = new BotHelper(baseURL, accessToken, clientId, clientSecret, port, redirect_url, browserPath);
        System.out.println("---SEND MESSAGES---");
        System.out.println("Enter the channel name where you want to send the message");
        String channelName = input.nextLine();
        System.out.println("Enter the message");
        String messageToSend = input.nextLine();
        helper.sendMessages(channelName, messageToSend);

        System.out.println("---HISTORY---");
        System.out.println("Enter the channel name: ");
        channelName = input.nextLine();
        System.out.println("Enter start date in format yyyy-MM-dd: ");
        String fromDate = input.nextLine();
        System.out.println("Enter end date in format yyyy-MM-dd: ");
        String endDate = input.nextLine();
        List<Message> result = helper.history(channelName, Utils.stringToLocaleDate(fromDate), Utils.stringToLocaleDate(endDate));
        System.out.println("History: " + result);

        System.out.println("---SEARCH---");
        System.out.println("Enter the channel name: ");
        channelName = input.nextLine();
        System.out.println("Enter start date in format yyyy-MM-dd: ");
        fromDate = input.nextLine();
        System.out.println("Enter end date in format yyyy-MM-dd: ");
        endDate = input.nextLine();
        System.out.println("Enter message to be searched: ");
        String searchText = input.nextLine();
        result = helper.search(channelName, Utils.stringToLocaleDate(fromDate), Utils.stringToLocaleDate(endDate),
                message -> (message.getMessage().toLowerCase().contains(searchText.toLowerCase())));
        System.out.println("Search: " + result);

       /*
        //get User details
        UserComponent userComponent = new UserComponent(baseURL, accessToken);
        Map<String, String> params = new HashMap<>();
        params.put("userId", "me");
        GetUserResponse getUserResponse = userComponent.getUser(params);
        String email = getUserResponse.getEmail();

        //create channel
        System.out.println("---Create Channel---");
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
        CreateChannelResponse createChannelResponse = chatChannelComponent.createChannel(params, createChannelRequest);
        System.out.println("Response: " + createChannelResponse);


        //update channel
        System.out.println("---Update Channel---");
        params = new HashMap<>();
        String newChannelId = createChannelResponse.getId();
        params.put("channelId", newChannelId);
        UpdateChannelRequest updateChannelRequest = new UpdateChannelRequest();
        System.out.println("Enter new channel name");
        String newChannelName = input.nextLine();
        updateChannelRequest.setName(newChannelName);
        System.out.println("Response: " + chatChannelComponent.updateChannel(params, updateChannelRequest));

        //list user channels
        System.out.println("---List User Channels---");
        params = new HashMap<>();
        params.put("userId", "me");
        System.out.println("Response: " + chatChannelComponent.listUserChannels(params));

        //get channel
        System.out.println("---Get Channel---");
        String publicChannelId = "109ab13498c64fd5911a42be1076ea6b";
        params = new HashMap<>();
        params.put("channelId", publicChannelId);
        System.out.println("Response: " + chatChannelComponent.getChannel(params));

        //send chat message
        System.out.println("---Send Chat Message---");
        params = new HashMap<>();
        params.put("userId", "me");
        ChatMessagesComponent chatMessagesComponent = new ChatMessagesComponent(baseURL, accessToken);
        SendChatMessageRequest sendChatMessageRequest = new SendChatMessageRequest();
        System.out.println("Enter Message: ");
        String msg = input.nextLine();
        sendChatMessageRequest.setMessage(msg);
        sendChatMessageRequest.setTo_channel(newChannelId);
        SendChatMessageResponse sendChatResponse = chatMessagesComponent.sendChatMessage(params, sendChatMessageRequest);
        System.out.println("Response: " + sendChatResponse.toString());

        //lists the chat messages
        System.out.println("---List Chat Messages---");
        params = new HashMap<>();
        params.put("userId", "me");
        params.put("to_channel", newChannelId);
        System.out.println("Response: " + chatMessagesComponent.listUserChatMessages(params));

        //update message
        System.out.println("---Update Message---");
        params = new HashMap<>();
        String msgId = sendChatResponse.getId();
        params.put("messageId", msgId);
        UpdateMessageRequest updateMessageRequest = new UpdateMessageRequest();
        System.out.println("Enter new msg: ");
        String newMsg = input.nextLine();
        updateMessageRequest.setMessage(newMsg);
        updateMessageRequest.setTo_channel(newChannelId);
        System.out.println("Response: " + chatMessagesComponent.updateChatMessage(params, updateMessageRequest));

        //delete message
        System.out.println("---Delete Message---");
        params = new HashMap<>();
        params.put("to_channel", newChannelId);
        params.put("messageId", msgId);
        System.out.println("Response: " + chatMessagesComponent.deleteMessage(params));

        //invite channel members
        System.out.println("---Invite Channel Members---");
        params = new HashMap<>();
        params.put("channelId", newChannelId);
        member = new Member();
        System.out.println("Enter email: ");
        String memberEmail = input.nextLine();
        member.setEmail(memberEmail);
        memberList = new ArrayList<>();
        memberList.add(member);
        InviteChannelMembersRequest data = new InviteChannelMembersRequest();
        data.setMembers(memberList);
        System.out.println("Response: " + chatChannelComponent.inviteChannelMembers(params, data));

        //list channel members
        System.out.println("---List Channel Members---");
        params = new HashMap<>();
        params.put("channelId", newChannelId);
        ListChannelMemberResponse response = chatChannelComponent.listChannelMember(params);
        System.out.println("Response: " + response);

        //remove channel member
        System.out.println("---Remove Channel Members---");
        params = new HashMap<>();
        System.out.println("Enter id to be removed: ");
        String memberIdToBeRemoved = input.nextLine();
        params.put("channelId", newChannelId);
        params.put("memberId", memberIdToBeRemoved);
        System.out.println("Response: " + chatChannelComponent.removeMember(params));

        //join channel
        System.out.println("---Join Channel---");
        params = new HashMap<>();
        params.put("channelId", publicChannelId);
        System.out.println("Response: " + chatChannelComponent.joinChannel(params));


        //leave channel
        System.out.println("---Leave Channel---");
        params = new HashMap<>();
        params.put("channelId", publicChannelId);
        System.out.println("Response: " + chatChannelComponent.leaveChannel(params));


        //delete channel
        System.out.println("---Delete Channel---");
        params = new HashMap<>();
        params.put("channelId", newChannelId);
        System.out.println("Response: " + chatChannelComponent.deleteChannel(params));


         */


    }
}
