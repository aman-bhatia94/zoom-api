package zoomapi.components;

import utils.ApiClient;
import utils.Utils;
import zoomapi.components.componentRequestData.CreateWebinarRequest;
import zoomapi.components.componentRequestData.UpdateMeetingRequest;
import zoomapi.components.componentRequestData.UpdateWebinarStatus;

import java.util.Map;

public class WebinarComponent extends BaseComponent {

    public WebinarComponent(String baseUri, String token) {
        super(baseUri, token);
    }

    public void listWebinars(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/users/%s/webinars";
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

    public void createWebinar(Map<String, String> params, CreateWebinarRequest data) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/users/%s/webinars";
            url = String.format(url, params.get("userId"));
            String dataStr = gson.toJson(data);
            String response = ApiClient.getApiClient().postRequest(url, params, dataStr, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void getWebinar(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/webinars/%s";
            url = String.format(url, params.get("webinarId"));
            url = Utils.appendToUrl(url, params);
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void updateWebinar(Map<String, String> params, UpdateMeetingRequest data) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/webinars/%s";
            url = String.format(url, params.get("webinarId"));
            url = Utils.appendToUrl(url, params);
            String dataStr = gson.toJson(data);
            String response = ApiClient.getApiClient().patchRequest(url, params, dataStr, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void deleteWebinar(Map<String, String> params) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/webinars/%s";
            url = String.format(url, params.get("webinarId"));
            url = Utils.appendToUrl(url, params);
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void updateWebinarStatus(Map<String, String> params, UpdateWebinarStatus data) {
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/webinars/%s/status";
            url = String.format(url, params.get("webinarId"));
            String dataStr = gson.toJson(data);
            String response = ApiClient.getApiClient().putRequest(url, params, dataStr, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
