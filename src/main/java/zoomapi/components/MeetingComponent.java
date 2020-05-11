package zoomapi.components;

import utils.ApiClient;
import utils.Utils;
import zoomapi.components.componentRequestData.CreateMeetingRequest;
import zoomapi.components.componentRequestData.UpdateMeetingRequest;
import zoomapi.components.componentResponseData.MeetingResponseData.CreateMeetingResponse;
import zoomapi.components.componentResponseData.MeetingResponseData.GetMeetingResponse;
import zoomapi.components.componentResponseData.MeetingResponseData.ListMeetingsResponse;

import java.util.Map;

public class MeetingComponent extends BaseComponent {
    public MeetingComponent(String baseUri, String token) {
        super(baseUri, token);
    }

    public ListMeetingsResponse listMeetings(Map<String, String> params) {
        ListMeetingsResponse responseData = null;
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/users/%s/meetings";
            url = String.format(url, params.get("userId"));
            url = Utils.appendToUrl(url, params);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, ListMeetingsResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public CreateMeetingResponse createMeeting(Map<String, String> params, CreateMeetingRequest data) {
        CreateMeetingResponse responseData = null;
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/users/%s/meetings";
            url = String.format(url, params.get("userId"));
            String dataStr = GSON.toJson(data);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().postRequest(url, params, dataStr, null, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, CreateMeetingResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public GetMeetingResponse getMeeting(Map<String, String> params) {
        GetMeetingResponse responseData = null;
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/meetings/%s";
            url = String.format(url, params.get("meetingId"));
            url = Utils.appendToUrl(url, params);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, GetMeetingResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public Map<String, String> updateMeeting(Map<String, String> params, UpdateMeetingRequest data) {
        Map<String, String> responseMap = null;
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/meetings/%s";
            url = String.format(url, params.get("meetingId"));
            String dataStr = GSON.toJson(data);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().patchRequest(url, params, dataStr, null, null);
            responseMap = GSON.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public Map<String, String> deleteMeeting(Map<String, String> params) {
        Map<String, String> responseMap = null;
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/meetings/%s";
            url = String.format(url, params.get("meetingId"));
            url = Utils.appendToUrl(url, params);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            responseMap = GSON.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

}
