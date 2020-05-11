package zoomapi.components;

import utils.ApiClient;
import utils.Utils;
import zoomapi.components.componentRequestData.CreateWebinarRequest;
import zoomapi.components.componentRequestData.UpdateMeetingRequest;
import zoomapi.components.componentRequestData.UpdateWebinarStatus;
import zoomapi.components.componentResponseData.WebinarResponseData.ListWebinarsResponseData;

import java.util.Map;

public class WebinarComponent extends BaseComponent {
    public WebinarComponent(String baseUri, String token) {
        super(baseUri, token);
    }

    public ListWebinarsResponseData listWebinars(Map<String, String> params) {
        ListWebinarsResponseData responseData = new ListWebinarsResponseData();
        try {
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/users/%s/webinars", params,
                    new String[]{"userId"}, true);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map<String, String> responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, ListWebinarsResponseData.class);
            }
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public Map createWebinar(Map<String, String> params, CreateWebinarRequest data) {
        Map<String, String> responseMap = null;
        try {
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/users/%s/webinars", params,
                    new String[]{"userId"}, false);
            String dataStr = GSON.toJson(data);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().postRequest(url, params, dataStr, null, null);
            responseMap = GSON.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public Map<String, String> getWebinar(Map<String, String> params) {
        Map<String, String> responseMap = null;
        try {
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/webinars/%s", params,
                    new String[]{"webinarId"}, true);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            responseMap = GSON.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public Map<String, String> updateWebinar(Map<String, String> params, UpdateMeetingRequest data) {
        Map<String, String> responseMap = null;
        try {
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/webinars/%s", params,
                    new String[]{"webinarId"}, true);
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

    public Map<String, String> deleteWebinar(Map<String, String> params) {
        Map<String, String> responseMap = null;
        try {
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/webinars/%s", params,
                    new String[]{"webinarId"}, true);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            responseMap = GSON.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public Map<String, String> updateWebinarStatus(Map<String, String> params, UpdateWebinarStatus data) {
        Map<String, String> responseMap = null;
        try {
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/webinars/%s/status", params,
                    new String[]{"webinarId"}, false);
            String dataStr = GSON.toJson(data);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().putRequest(url, params, dataStr, null, null);
            responseMap = GSON.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }
}
