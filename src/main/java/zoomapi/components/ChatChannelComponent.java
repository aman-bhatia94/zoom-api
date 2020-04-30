package zoomapi.components;

import utils.ApiClient;
import utils.Throttled;
import utils.Utils;
import zoomapi.components.componentRequestData.CreateChannelRequest;
import zoomapi.components.componentRequestData.InviteChannelMembersRequest;
import zoomapi.components.componentRequestData.UpdateChannelRequest;
import zoomapi.components.componentResponseData.*;
import zoomapi.components.componentResponseData.ChannelResponseData.*;

import java.util.Map;

public class ChatChannelComponent extends BaseComponent {

    Throttled listUserThrottler = null;
    Throttled createChannelThrottler = null;
    Throttled getChannelThrottler = null;
    Throttled updateChannelThrottler = null;
    Throttled deleteChannelThrottler = null;
    Throttled listChannelMemberThrottler = null;
    Throttled inviteChannelMemberThrottler = null;
    Throttled joinChannelThrottler = null;
    Throttled leaveChannelThrottler = null;
    Throttled removeMemberThrottler = null;

    public ChatChannelComponent(String baseUri, String token) {
        super(baseUri, token);
    }

    public ListUserChannelsResponse listUserChannels(Map<String, String> params) {
        ListUserChannelsResponse responseData = new ListUserChannelsResponse();
        if (listUserThrottler == null) {
            listUserThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/users/me/channels";
            listUserThrottler.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = gson.fromJson(response, ListUserChannelsResponse.class);
//                System.out.println("Response: " + response);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public CreateChannelResponse createChannel(Map<String, String> params, CreateChannelRequest data) {
        CreateChannelResponse responseData = null;
        if (createChannelThrottler == null) {
            createChannelThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/users/me/channels";
            String dataStr = gson.toJson(data);
            createChannelThrottler.throttle();
            String response = ApiClient.getApiClient().postRequest(url, params, dataStr, null, null);
            Map responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = gson.fromJson(response, CreateChannelResponse.class);
//                System.out.println("Response: " + response);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public GetChannelResponse getChannel(Map<String, String> params) {
        GetChannelResponse responseData = null;
        if (getChannelThrottler == null) {
            getChannelThrottler = new Throttled();
        }
        try {
            Utils.requireKeys(params, new String[]{"channelId"}, false);
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/channels/%s";
            url = String.format(url, params.get("channelId"));
            getChannelThrottler.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = gson.fromJson(response, GetChannelResponse.class);
//                System.out.println("Response: " + response);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public Map updateChannel(Map<String, String> params, UpdateChannelRequest data) {
        Map responseMap = null;
        if (updateChannelThrottler == null) {
            updateChannelThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/channels/%s";
            Utils.requireKeys(params, new String[]{"channelId"}, false);
            url = String.format(url, params.get("channelId"));
            String dataStr = gson.toJson(data);
            updateChannelThrottler.throttle();
            String response = ApiClient.getApiClient().patchRequest(url, params, dataStr, null, null);
            responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
//                System.out.println("Response: " + response);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public Map deleteChannel(Map<String, String> params) {
        Map responseMap = null;
        if (deleteChannelThrottler == null) {
            deleteChannelThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/channels/%s";
            Utils.requireKeys(params, new String[]{"channelId"}, false);
            url = String.format(url, params.get("channelId"));
            deleteChannelThrottler.throttle();
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
//                System.out.println("Response: " + response);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public ListChannelMemberResponse listChannelMember(Map<String, String> params) {
        ListChannelMemberResponse responseData = null;
        if (listChannelMemberThrottler == null) {
            listChannelMemberThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/channels/%s/members";
            Utils.requireKeys(params, new String[]{"channelId"}, false);
            if (!params.containsKey("page_size"))
                params.put("page_size", "10");
            url = String.format(url, params.get("channelId"));
            url = Utils.appendToUrl(url, params);
            listChannelMemberThrottler.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = gson.fromJson(response, ListChannelMemberResponse.class);
//                System.out.println("Response: " + response);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public InviteChannelMembersResponse inviteChannelMembers(Map<String, String> params, InviteChannelMembersRequest data) {
        InviteChannelMembersResponse responseData = null;
        if (inviteChannelMemberThrottler == null) {
            inviteChannelMemberThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/channels/%s/members";
            Utils.requireKeys(params, new String[]{"channelId"}, false);
            url = String.format(url, params.get("channelId"));
            String dataStr = gson.toJson(data);
            inviteChannelMemberThrottler.throttle();
            String response = ApiClient.getApiClient().postRequest(url, params, dataStr, null, null);
            Map responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = gson.fromJson(response, InviteChannelMembersResponse.class);
//                System.out.println("Response: " + response);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public JoinChannelResponse joinChannel(Map<String, String> params) {
        JoinChannelResponse responseData = null;
        if (joinChannelThrottler == null) {
            joinChannelThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/channels/%s/members/me";
            Utils.requireKeys(params, new String[]{"channelId"}, false);
            url = String.format(url, params.get("channelId"));
            joinChannelThrottler.throttle();
            String response = ApiClient.getApiClient().postRequest(url, params, "", null, null);
            Map responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = gson.fromJson(response, JoinChannelResponse.class);
//                System.out.println("Response: " + response);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public Map leaveChannel(Map<String, String> params) {
        Map<String, String> responseMap = null;
        if (leaveChannelThrottler == null) {
            leaveChannelThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/channels/%s/members/me";
            Utils.requireKeys(params, new String[]{"channelId"}, false);
            url = String.format(url, params.get("channelId"));
            leaveChannelThrottler.throttle();
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            responseMap = gson.fromJson(response, Map.class);
//            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public Map removeMember(Map<String, String> params) {
        Map responseMap = null;
        if (removeMemberThrottler == null) {
            removeMemberThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/chat/channels/%s/members/%s";
            Utils.requireKeys(params, new String[]{"channelId", "memberId"}, false);
            url = String.format(url, params.get("channelId"), params.get("memberId"));
            removeMemberThrottler.throttle();
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            responseMap = gson.fromJson(response, Map.class);
//            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

}
