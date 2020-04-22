package zoomapi.components;

import utils.ApiClient;
import utils.Utils;

import java.util.Map;

public class RecordingComponent extends BaseComponent {

    public RecordingComponent(String baseUri, String token) {
        super(baseUri, token);
    }

    public void listAllRecordings(Map<String, String> params) {
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
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void getMeetingRecordings(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/meetings/%s/recordings";
            url = String.format(url, params.get("meetingId"));
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void deleteMeetingRecordings(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/meetings/%s/recordings";
            url = String.format(url, params.get("meetingId"));
            url = Utils.appendToUrl(url, params);
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }


}
