package zoomapi.components;

import utils.ApiClient;
import utils.Utils;
import zoomapi.components.componentRequestData.CreateMeetingRequest;
import zoomapi.components.componentRequestData.UpdateMeetingRequest;

import java.util.Map;

public class MeetingComponent extends BaseComponent {

    public MeetingComponent(String baseUri, String token) {
        super(baseUri, token);
    }

    public void listMeetings(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/users/%s/meetings";
            url = String.format(url, params.get("userId"));
            if (!params.containsKey("page_size"))
                params.put("page_size", "10");
            if (!params.containsKey("page_number"))
                params.put("page_number", "1");
            url = Utils.appendToUrl(url, params);
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void createMeeting(Map<String, String> params, CreateMeetingRequest data) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/users/%s/meetings";
            url = String.format(url, params.get("userId"));
            String dataStr = gson.toJson(data);
            String response = ApiClient.getApiClient().postRequest(url, params, dataStr, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void getMeeting(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/meetings/%s";
            url = String.format(url, params.get("meetingId"));
            url = Utils.appendToUrl(url, params);
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void updateMeeting(Map<String, String> params, UpdateMeetingRequest data) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/meetings/%s";
            url = String.format(url, params.get("meetingId"));
            String dataStr = gson.toJson(data);
            String response = ApiClient.getApiClient().patchRequest(url, params, dataStr, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void deleteMeeting(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/meetings/%s";
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
