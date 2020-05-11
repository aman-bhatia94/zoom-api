package zoomapi.components;

import utils.ApiClient;
import utils.StatusCodes;
import utils.Utils;
import zoomapi.components.componentRequestData.CreateChannelRequest;
import zoomapi.components.componentRequestData.InviteChannelMembersRequest;
import zoomapi.components.componentRequestData.UpdateChannelRequest;
import zoomapi.components.componentResponseData.ChannelResponseData.*;

import java.util.Map;

public class ChatChannelComponent extends BaseComponent {

    public ChatChannelComponent(String baseUri, String token) {
        super(baseUri, token);
    }

    public ListUserChannelsResponse listUserChannels(Map<String, String> params) {
        ListUserChannelsResponse responseData = new ListUserChannelsResponse();
        try {
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/chat/users/me/channels", params, null, false);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, ListUserChannelsResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public CreateChannelResponse createChannel(Map<String, String> params, CreateChannelRequest data) {
        CreateChannelResponse responseData = null;
        try {
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/chat/users/me/channels", params, null, false);
            String dataStr = GSON.toJson(data);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().postRequest(url, params, dataStr, null, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, CreateChannelResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public GetChannelResponse getChannel(Map<String, String> params) {
        GetChannelResponse responseData = null;
        try {
            Utils.requireKeys(params, new String[]{"channelId"}, false);
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/chat/channels/%s", params,
                    new String[]{"channelId"}, false);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, GetChannelResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public int updateChannel(Map<String, String> params, UpdateChannelRequest data) {
        int statusCode = -1;
        try {
            Utils.requireKeys(params, new String[]{"channelId"}, false);
            if (data.getName() == null) {
                throw new Exception("parameter 'name' was not set");
            }
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/chat/channels/%s", params,
                    new String[]{"channelId"}, false);
            String dataStr = GSON.toJson(data);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().patchRequest(url, params, dataStr, null, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                statusCode = StatusCodes.CHANNEL_UPDATED;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return statusCode;
    }

    public int deleteChannel(Map<String, String> params) {
        int statusCode = -1;
        try {
            Utils.requireKeys(params, new String[]{"channelId"}, false);
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/chat/channels/%s", params,
                    new String[]{"channelId"}, false);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                statusCode = StatusCodes.CHANNEL_DELETED;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return statusCode;
    }

    public ListChannelMemberResponse listChannelMember(Map<String, String> params) {
        ListChannelMemberResponse responseData = null;
        try {
            Utils.requireKeys(params, new String[]{"channelId"}, false);
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/chat/channels/%s/members", params,
                    new String[]{"channelId"}, true);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, ListChannelMemberResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public InviteChannelMembersResponse inviteChannelMembers(Map<String, String> params, InviteChannelMembersRequest data) {
        InviteChannelMembersResponse responseData = null;
        try {
            Utils.requireKeys(params, new String[]{"channelId"}, false);
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/chat/channels/%s/members", params,
                    new String[]{"channelId"}, false);
            String dataStr = GSON.toJson(data);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().postRequest(url, params, dataStr, null, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, InviteChannelMembersResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public JoinChannelResponse joinChannel(Map<String, String> params) {
        JoinChannelResponse responseData = null;
        try {
            Utils.requireKeys(params, new String[]{"channelId"}, false);
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/chat/channels/%s/members/me", params,
                    new String[]{"channelId"}, false);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().postRequest(url, params, "", null, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, JoinChannelResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public int leaveChannel(Map<String, String> params) {
        int statusCode = -1;
        try {
            Utils.requireKeys(params, new String[]{"channelId"}, false);
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/chat/channels/%s/members/me", params,
                    new String[]{"channelId"}, false);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                statusCode = StatusCodes.LEFT_CHANNEL_SUCCESSFULLY;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return statusCode;
    }

    public int removeMember(Map<String, String> params) {
        int statusCode = -1;
        try {
            Utils.requireKeys(params, new String[]{"channelId", "memberId"}, false);
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/chat/channels/%s/members/%s", params,
                    new String[]{"channelId", "memberId"}, false);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                statusCode = StatusCodes.MEMBER_REMOVED;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return statusCode;
    }

}
