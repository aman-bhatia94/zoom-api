package zoomapi.components;

import utils.ApiClient;
import utils.Utils;
import zoomapi.components.componentResponseData.Meeting;
import zoomapi.components.componentResponseData.RecordingComponentData.ListAllRecordingsResponse;

import java.util.Map;

public class RecordingComponent extends BaseComponent {
    public RecordingComponent(String baseUri, String token, String clientId) {
        super(baseUri, token, clientId);
    }

    public ListAllRecordingsResponse listAllRecordings(Map<String, String> params) {
        ListAllRecordingsResponse responseData = new ListAllRecordingsResponse();
        try {
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/users/%s/recordings", params,
                    new String[]{"userId"}, true);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, ListAllRecordingsResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public Meeting getMeetingRecordings(Map<String, String> params) {
        Meeting responseData = new Meeting();
        try {
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/meetings/%s/recordings", params,
                    new String[]{"meetingId"}, false);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, Meeting.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public Map<String, String> deleteMeetingRecordings(Map<String, String> params) {
        Map<String, String> responseMap = null;
        try {
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/meetings/%s/recordings", params,
                    new String[]{"meetingId"}, true);
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
