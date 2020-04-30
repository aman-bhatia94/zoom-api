package zoomapi.components;

import utils.ApiClient;
import utils.Throttled;
import utils.Utils;
import zoomapi.components.componentRequestData.CreateWebinarRequest;
import zoomapi.components.componentRequestData.UpdateMeetingRequest;
import zoomapi.components.componentRequestData.UpdateWebinarStatus;
import zoomapi.components.componentResponseData.ChatMessagesResponseData.SendChatMessageResponse;
import zoomapi.components.componentResponseData.WebinarResponseData.ListWebinarsResponseData;

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

    public ListWebinarsResponseData listWebinars(Map<String, String> params) {
        ListWebinarsResponseData responseData = new ListWebinarsResponseData();
        Map responseMap = null;
        if (listWebinarThrottler == null) {
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
            responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = gson.fromJson(response, ListWebinarsResponseData.class);
//                System.out.println("Response: " + response);
            }
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public Map createWebinar(Map<String, String> params, CreateWebinarRequest data) {
        Map<String, String> responseMap = null;
        if (createWebinarThrottler == null) {
            createWebinarThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/users/%s/webinars";
            url = String.format(url, params.get("userId"));
            String dataStr = gson.toJson(data);
            createWebinarThrottler.throttle();
            String response = ApiClient.getApiClient().postRequest(url, params, dataStr, null, null);
            responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public Map<String, String> getWebinar(Map<String, String> params) {
        Map<String, String> responseMap = null;
        if (getWebinarThrottler == null) {
            getWebinarThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/webinars/%s";
            url = String.format(url, params.get("webinarId"));
            url = Utils.appendToUrl(url, params);
            getWebinarThrottler = new Throttled();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public Map<String, String> updateWebinar(Map<String, String> params, UpdateMeetingRequest data) {
        Map<String, String> responseMap = null;
        if (updateWebinarThrottler == null) {
            updateWebinarThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/webinars/%s";
            url = String.format(url, params.get("webinarId"));
            url = Utils.appendToUrl(url, params);
            String dataStr = gson.toJson(data);
            updateWebinarThrottler.throttle();
            String response = ApiClient.getApiClient().patchRequest(url, params, dataStr, null, null);
            responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public Map<String, String> deleteWebinar(Map<String, String> params) {
        Map<String, String> responseMap = null;
        if (deleteWebinarThrottler == null) {
            deleteWebinarThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/webinars/%s";
            url = String.format(url, params.get("webinarId"));
            url = Utils.appendToUrl(url, params);
            deleteWebinarThrottler.throttle();
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public Map<String, String> updateWebinarStatus(Map<String, String> params, UpdateWebinarStatus data) {
        Map<String, String> responseMap = null;
        if (updateStatusThrottler == null) {
            updateStatusThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/webinars/%s/status";
            url = String.format(url, params.get("webinarId"));
            String dataStr = gson.toJson(data);
            updateStatusThrottler.throttle();
            String response = ApiClient.getApiClient().putRequest(url, params, dataStr, null, null);
            responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }
}
