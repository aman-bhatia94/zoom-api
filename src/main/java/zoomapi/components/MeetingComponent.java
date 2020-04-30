package zoomapi.components;

import utils.ApiClient;
import utils.Throttled;
import utils.Utils;
import zoomapi.components.componentRequestData.CreateMeetingRequest;
import zoomapi.components.componentRequestData.UpdateMeetingRequest;
import zoomapi.components.componentResponseData.MeetingResponseData.CreateMeetingResponse;
import zoomapi.components.componentResponseData.MeetingResponseData.GetMeetingResponse;
import zoomapi.components.componentResponseData.MeetingResponseData.ListMeetingsResponse;

import java.util.Map;

public class MeetingComponent extends BaseComponent {
    Throttled listMeetingThrottler = null;
    Throttled createMeetingThrottler = null;
    Throttled getListMeetingThrottler = null;
    Throttled updateMeetingThrottler = null;
    Throttled deleteMeetingThrottler = null;

    public MeetingComponent(String baseUri, String token) {
        super(baseUri, token);
    }

    public ListMeetingsResponse listMeetings(Map<String, String> params) {
        ListMeetingsResponse responseData = null;
        if (listMeetingThrottler == null) {
            listMeetingThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/users/%s/meetings";
            url = String.format(url, params.get("userId"));
            if (!params.containsKey("page_size"))
                params.put("page_size", "10");
            if (!params.containsKey("page_number"))
                params.put("page_number", "1");
            url = Utils.appendToUrl(url, params);
            listMeetingThrottler.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = gson.fromJson(response, ListMeetingsResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public CreateMeetingResponse createMeeting(Map<String, String> params, CreateMeetingRequest data) {
        CreateMeetingResponse responseData = null;
        if (createMeetingThrottler == null) {
            createMeetingThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/users/%s/meetings";
            url = String.format(url, params.get("userId"));
            String dataStr = gson.toJson(data);
            createMeetingThrottler.throttle();
            String response = ApiClient.getApiClient().postRequest(url, params, dataStr, null, null);
            Map responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = gson.fromJson(response, CreateMeetingResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public GetMeetingResponse getMeeting(Map<String, String> params) {
        GetMeetingResponse responseData = null;
        if (getListMeetingThrottler == null) {
            getListMeetingThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/meetings/%s";
            url = String.format(url, params.get("meetingId"));
            url = Utils.appendToUrl(url, params);
            getListMeetingThrottler.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = gson.fromJson(response, GetMeetingResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public Map<String, String> updateMeeting(Map<String, String> params, UpdateMeetingRequest data) {
        Map<String, String> responseMap = null;
        if (updateMeetingThrottler == null) {
            updateMeetingThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/meetings/%s";
            url = String.format(url, params.get("meetingId"));
            String dataStr = gson.toJson(data);
            updateMeetingThrottler.throttle();
            String response = ApiClient.getApiClient().patchRequest(url, params, dataStr, null, null);
            responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public Map<String, String> deleteMeeting(Map<String, String> params) {
        Map<String, String> responseMap = null;
        if (deleteMeetingThrottler == null) {
            deleteMeetingThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/meetings/%s";
            url = String.format(url, params.get("meetingId"));
            url = Utils.appendToUrl(url, params);
            deleteMeetingThrottler.throttle();
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

}
