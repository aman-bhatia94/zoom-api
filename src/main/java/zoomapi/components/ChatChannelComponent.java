package zoomapi.components;

import models.ChannelMembership;
import models.Channels;
import services.data.ChannelMembershipRequestData;
import services.data.ChannelsRequestData;
import utils.ApiClient;
import utils.DatabaseConnection;
import utils.StatusCodes;
import utils.Utils;
import zoomapi.components.componentRequestData.CreateChannelRequest;
import zoomapi.components.componentRequestData.InviteChannelMembersRequest;
import zoomapi.components.componentRequestData.UpdateChannelRequest;
import zoomapi.components.componentResponseData.ChannelData;
import zoomapi.components.componentResponseData.ChannelResponseData.*;
import zoomapi.components.componentResponseData.Member;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ChatChannelComponent extends BaseComponent {

    public ChatChannelComponent(String baseUri, String token, String clientId) throws Exception {
        super(baseUri, token, clientId);
    }

    public ListUserChannelsResponse listUserChannels(Map<String, String> params) {
        ListUserChannelsResponse responseData = new ListUserChannelsResponse();
        try {
            String dbResponse = DatabaseConnection.getDataDMLService().get(new ChannelsRequestData(null, null)).getResponseData();
            Channels[] channelsDBResponseArray = GSON.fromJson(dbResponse, Channels[].class);
            ListUserChannelsResponse dbResponseData = new ListUserChannelsResponse();
            dbResponseData.setChannels(MapChannelModelToChannelData(Arrays.asList(channelsDBResponseArray)));
            if (!DatabaseConnection.isTimeOut(DatabaseConnection.TimestampModeEnum.ChannelTimestamp)) {
                return dbResponseData;
            }
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/chat/users/me/channels", params, null, false);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                ListUserChannelsResponse apiResponseData = GSON.fromJson(response, ListUserChannelsResponse.class);
                responseData = apiResponseData;
                List<ChannelData> toBeAdded = new ArrayList<>(apiResponseData.getChannels());
                toBeAdded.removeAll(dbResponseData.getChannels());
                for (ChannelData channelData : apiResponseData.getChannels()) {
                    Channels channel = new Channels(null, channelData.getId(), channelData.getName(), channelData.getType().toString());
                    DatabaseConnection.getDataDMLService().insert(new ChannelsRequestData(channel, null));
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return responseData;
    }

    private List<ChannelData> MapChannelModelToChannelData(List<Channels> channelsList) {
        List<ChannelData> channelDataList = new ArrayList<>();
        if (channelsList == null || channelsList.size() == 0) return channelDataList;
        for (Channels channel : channelsList) {
            ChannelData channelData = new ChannelData();
            channelData.setId(channel.getChannel_id());
            channelData.setName(channel.getChannel_name());
            channelData.setType(Integer.parseInt(channel.getChannel_type()));
            channelDataList.add(channelData);
        }
        return channelDataList;
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
            String dbResponse = DatabaseConnection.getDataDMLService().get(new ChannelMembershipRequestData(null, null)).getResponseData();
            ChannelMembership[] channelDBResponseData = GSON.fromJson(dbResponse, ChannelMembership[].class);
            ListChannelMemberResponse dbResponseData = new ListChannelMemberResponse();
            dbResponseData.setMembers(MapChannelMembershipsToMembers(Arrays.asList(channelDBResponseData)));
            if (!DatabaseConnection.isTimeOut(DatabaseConnection.TimestampModeEnum.ChannelMembershipTimeStamp)) {
                return dbResponseData;
            }
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
                ListChannelMemberResponse apiResponseData = GSON.fromJson(response, ListChannelMemberResponse.class);
                responseData = apiResponseData;
                List<Member> toBeAdded = new ArrayList<>(apiResponseData.getMembers());
                for (Member member : toBeAdded) {
                    member.setChannelId(params.get("channelId"));
                }
                toBeAdded.removeAll(dbResponseData.getMembers());
                for (Member member : toBeAdded) {
                    ChannelMembership channelMembership = new ChannelMembership(null, params.get("channelId"),
                            member.getId(), member.getFirst_name(), member.getLast_name(), member.getEmail(), member.getRole());
                    DatabaseConnection.getDataDMLService().insert(new ChannelMembershipRequestData(channelMembership, null));
                }
//                List<Member> toBeDeleted = new ArrayList<>(dbResponseData.getMembers());
//                toBeDeleted

            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return responseData;
    }

    private List<Member> MapChannelMembershipsToMembers(List<ChannelMembership> channelMemberships) {
        List<Member> list = new ArrayList<>();
        if (channelMemberships == null || channelMemberships.size() == 0) return list;
        for (ChannelMembership membership : channelMemberships) {
            Member member = new Member();
            member.setEmail(membership.getEmail());
            member.setFirst_name(membership.getFirst_name());
            member.setId(membership.getMember_id());
            member.setLast_name(membership.getLast_name());
            member.setRole(membership.getRole());
            member.setChannelId(membership.getChannel_id());
            list.add(member);
        }
        return list;
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
