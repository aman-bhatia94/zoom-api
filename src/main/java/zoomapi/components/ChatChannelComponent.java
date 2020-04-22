package zoomapi.components;

import utils.ApiClient;
import utils.Utils;
import zoomapi.components.componentRequestData.CreateChannelRequest;
import zoomapi.components.componentRequestData.InviteChannelMembersRequest;
import zoomapi.components.componentRequestData.UpdateChannelRequest;

import java.util.Map;

public class ChatChannelComponent extends BaseComponent {

    public ChatChannelComponent(String baseUri, String token) {
        super(baseUri, token);
    }

    public void listUserChannels(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/users/me/channels";
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void createChannel(Map<String, String> params, CreateChannelRequest data) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/users/me/channels";
            String dataStr = gson.toJson(data);
            String response = ApiClient.getApiClient().postRequest(url, params, dataStr, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void getChannel(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/channels/%s";
            url = String.format(url, params.get("channelId"));
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void updateChannel(Map<String, String> params, UpdateChannelRequest data) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/channels/%s";
            url = String.format(url, params.get("channelId"));
            String dataStr = gson.toJson(data);
            String response = ApiClient.getApiClient().patchRequest(url, params, dataStr, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void deleteChannel(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/channels/%s";
            url = String.format(url, params.get("channelId"));
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void listChannelMember(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/channels/%s/members";
            if (!params.containsKey("page_size"))
                params.put("page_size", "10");
            url = String.format(url, params.get("channelId"));
            url = Utils.appendToUrl(url, params);
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void inviteChannelMembers(Map<String, String> params, InviteChannelMembersRequest data) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/channels/%s/members";
            url = String.format(url, params.get("channelId"));
            String dataStr = gson.toJson(data);
            String response = ApiClient.getApiClient().postRequest(url, params, dataStr, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void joinChannel(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/channels/%s/members/me";
            url = String.format(url, params.get("channelId"));
            String response = ApiClient.getApiClient().postRequest(url, params, null, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void leaveChannel(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/channels/%s/members/me";
            url = String.format(url, params.get("channelId"));
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void removeMember(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/channels/%s/members/%s";
            url = String.format(url, params.get("channelId"), params.get("memberId"));
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
