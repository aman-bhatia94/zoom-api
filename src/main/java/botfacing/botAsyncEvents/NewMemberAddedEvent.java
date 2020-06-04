package botfacing.botAsyncEvents;

import botfacing.BotEventListener;
import zoomapi.components.ChatChannelComponent;
import zoomapi.components.componentResponseData.ChannelData;
import zoomapi.components.componentResponseData.ChannelResponseData.ListChannelMemberResponse;
import zoomapi.components.componentResponseData.ChannelResponseData.ListUserChannelsResponse;
import zoomapi.components.componentResponseData.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NewMemberAddedEvent extends Thread {

    private static ChatChannelComponent chatChannelComponent;
    private BotEventListener listener;
    private String baseURL;
    private String accessToken;
    private List<ChannelData> userChannels;
    private Map<String, List<Member>> channelDataMemberMap;
    private String clientId;

    public void setEventListener(BotEventListener listener, String baseURL, String accessToken, String clientId) throws Exception {
        this.listener = listener;
        this.baseURL = baseURL;
        this.accessToken = accessToken;
        this.clientId = clientId;
        init();
    }

    private void init() throws Exception {
        //list user channels
        chatChannelComponent = new ChatChannelComponent(baseURL, accessToken, clientId);
        channelDataMemberMap = new HashMap<>();
        Map<String, String> params = new HashMap<>();
        params.put("userId", "me");
        params.put("page_size", "100");
        ListUserChannelsResponse response = chatChannelComponent.listUserChannels(params);
        userChannels = response.getChannels();
        for (ChannelData channel : userChannels) {
            channelDataMemberMap.put(channel.getId(), getChannelUsers(channel));
        }
    }

    private List<Member> getChannelUsers(ChannelData channel) {
        Map<String, String> params = new HashMap<>();
        params.put("channelId", channel.getId());
        params.put("page_size", "100");
        List<Member> users = new ArrayList<>();
        String nextToken = "";
        do {
            params.put("next_page_token", nextToken);
            ListChannelMemberResponse userChannelsResponse = chatChannelComponent.listChannelMember(params);
            if (userChannelsResponse == null || userChannelsResponse.getMembers() != null && userChannelsResponse.getMembers().size() > 0) {
                assert userChannelsResponse != null;
                users.addAll(userChannelsResponse.getMembers());
            }
            nextToken = userChannelsResponse.getNext_page_token();
        } while (nextToken != null && !nextToken.isEmpty());
        return users;
    }

    public void run() {
        while (true) {
            //This thread continuously runs, and fetches the new members every 10 seconds
            for (ChannelData channel : userChannels) {
                List<Member> newUsers = getChannelUsers(channel);
                List<String> newUserIds = newUsers.stream().map(Member::getId).collect(Collectors.toList());
                List<Member> oldUsers = channelDataMemberMap.getOrDefault(channel.getId(), new ArrayList<>());
                List<String> oldUserIds = oldUsers.stream().map(Member::getId).collect(Collectors.toList());
                channelDataMemberMap.put(channel.getId(), newUsers);
                newUserIds.removeAll(oldUserIds);
                if (newUserIds.size() > 0) {
                    newUsers = newUsers.stream().filter(obj -> newUserIds.contains(obj.getId())).collect(Collectors.toList());
                    oldUsers.addAll(newUsers);
                    for (Member eachUser : newUsers) {
                        listener.onNewChannelUserEvent(new Object[]{channel, eachUser});
                    }
                }
            }
            try {
                //This thread sleeps for 10 seconds between every check
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
