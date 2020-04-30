package zoomapi.components;

import utils.ApiClient;
import utils.Throttled;
import utils.Utils;
import zoomapi.components.componentResponseData.Meeting;
import zoomapi.components.componentResponseData.RecordingComponentData.ListAllRecordingsResponse;

import java.util.Map;

public class RecordingComponent extends BaseComponent {
    Throttled listThrottler = null;
    Throttled getRecordingThrottler = null;
    Throttled deleteMeetingRecordingThrottler = null;

    public RecordingComponent(String baseUri, String token) {
        super(baseUri, token);
    }

    public ListAllRecordingsResponse listAllRecordings(Map<String, String> params) {
        Map responseMap = null;
        ListAllRecordingsResponse responseData = new ListAllRecordingsResponse();
        if (listThrottler == null) {
            listThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/users/%s/recordings";
            url = String.format(url, params.get("userId"));
            if (!params.containsKey("page_size"))
                params.put("page_size", "10");
            if (!params.containsKey("mc"))
                params.put("mc", "false");
            if (!params.containsKey("trash_type"))
                params.put("trash_type", "meeting_recordings");
            url = Utils.appendToUrl(url, params);
            listThrottler.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = gson.fromJson(response, ListAllRecordingsResponse.class);
//                System.out.println("Response: " + response);
            }
            //System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public Meeting getMeetingRecordings(Map<String, String> params) {
        Meeting responseData = new Meeting();
        Map responseMap = null;
        if (getRecordingThrottler == null) {
            getRecordingThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/meetings/%s/recordings";
            url = String.format(url, params.get("meetingId"));
            getRecordingThrottler.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = gson.fromJson(response, Meeting.class);
//                System.out.println("Response: " + response);
            }
            //System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public Map<String, String> deleteMeetingRecordings(Map<String, String> params) {
        Map responseMap = null;
        if (deleteMeetingRecordingThrottler == null) {
            deleteMeetingRecordingThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/meetings/%s/recordings";
            url = String.format(url, params.get("meetingId"));
            url = Utils.appendToUrl(url, params);
            deleteMeetingRecordingThrottler.throttle();
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }
}
