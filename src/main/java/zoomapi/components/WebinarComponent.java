package zoomapi.components;

import utils.ApiClient;
import utils.Throttled;
import utils.Utils;
import zoomapi.components.componentRequestData.CreateWebinarRequest;
import zoomapi.components.componentRequestData.UpdateMeetingRequest;
import zoomapi.components.componentRequestData.UpdateWebinarStatus;

import java.util.Map;

public class WebinarComponent extends BaseComponent {
    Throttled listWebinarThrottler = null;
    Throttled createWebinarThrottler = null;
    Throttled getWebinarThrottler = null;
    Throttled updateWebinarThrottler = null;
    Throttled deleteWebinarThrottler = null;
    Throttled updateStatusThrottler = null;

    public WebinarComponent(String baseUri, String token) {
        super(baseUri, token);
    }

    public void listWebinars(Map<String, String> params) {
        if(listWebinarThrottler == null){
            listWebinarThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/users/%s/webinars";
            url = String.format(url, params.get("userId"));
            if (!params.containsKey("page_size"))
                params.put("page_size", "10");
            if (!params.containsKey("page_number"))
                params.put("page_number", "1");
            url = Utils.appendToUrl(url, params);
            listWebinarThrottler.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void createWebinar(Map<String, String> params, CreateWebinarRequest data) {
        if(createWebinarThrottler == null){
            createWebinarThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/users/%s/webinars";
            url = String.format(url, params.get("userId"));
            String dataStr = gson.toJson(data);
            createWebinarThrottler.throttle();
            String response = ApiClient.getApiClient().postRequest(url, params, dataStr, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void getWebinar(Map<String, String> params) {
        if(getWebinarThrottler == null){
            getWebinarThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/webinars/%s";
            url = String.format(url, params.get("webinarId"));
            url = Utils.appendToUrl(url, params);
            getWebinarThrottler = new Throttled();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void updateWebinar(Map<String, String> params, UpdateMeetingRequest data) {
        if(updateWebinarThrottler == null){
            updateWebinarThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/webinars/%s";
            url = String.format(url, params.get("webinarId"));
            url = Utils.appendToUrl(url, params);
            String dataStr = gson.toJson(data);
            updateWebinarThrottler.throttle();
            String response = ApiClient.getApiClient().patchRequest(url, params, dataStr, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void deleteWebinar(Map<String, String> params) {
        if(deleteWebinarThrottler == null){
            deleteWebinarThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/webinars/%s";
            url = String.format(url, params.get("webinarId"));
            url = Utils.appendToUrl(url, params);
            deleteWebinarThrottler.throttle();
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void updateWebinarStatus(Map<String, String> params, UpdateWebinarStatus data) {
        if(updateStatusThrottler == null){
            updateStatusThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/webinars/%s/status";
            url = String.format(url, params.get("webinarId"));
            String dataStr = gson.toJson(data);
            updateStatusThrottler.throttle();
            String response = ApiClient.getApiClient().putRequest(url, params, dataStr, null, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

}
