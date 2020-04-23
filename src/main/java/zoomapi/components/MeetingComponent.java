package zoomapi.components;

import utils.ApiClient;
import utils.Throttled;
import utils.Utils;
import zoomapi.components.componentRequestData.CreateMeetingRequest;
import zoomapi.components.componentRequestData.UpdateMeetingRequest;

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

    public Map<String, String> listMeetings(Map<String, String> params) {
        Map<String, String> responseMap = null;
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
            responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public Map<String, String> createMeeting(Map<String, String> params, CreateMeetingRequest data) {
        Map<String, String> responseMap = null;
        if (createMeetingThrottler == null) {
            createMeetingThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/users/%s/meetings";
            url = String.format(url, params.get("userId"));
            String dataStr = gson.toJson(data);
            createMeetingThrottler.throttle();
            String response = ApiClient.getApiClient().postRequest(url, params, dataStr, null, null);
            responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public Map<String, String> getMeeting(Map<String, String> params) {
        Map<String, String> responseMap = null;
        if (getListMeetingThrottler == null) {
            getListMeetingThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/meetings/%s";
            url = String.format(url, params.get("meetingId"));
            url = Utils.appendToUrl(url, params);
            getListMeetingThrottler.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
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
