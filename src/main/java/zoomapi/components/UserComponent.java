package zoomapi.components;

import utils.ApiClient;
import utils.Throttled;
import utils.Utils;
import zoomapi.components.componentRequestData.CreateUserRequest;
import zoomapi.components.componentRequestData.UpdateUserRequest;
import zoomapi.components.componentResponseData.GetUserResponse;

import java.io.IOException;
import java.util.Map;

public class UserComponent extends BaseComponent {

    Throttled getUserThrottler = null;
    Throttled createUserThrottler = null;
    Throttled updateUserThrottler = null;
    Throttled listUserThrottler = null;
    Throttled deleteUserThrottler = null;

    public UserComponent(String baseUri, String token) {
        super(baseUri, token);
    }

    public GetUserResponse getUser(Map<String, String> params) {
        GetUserResponse responseData = null;
        if (getUserThrottler == null) {
            getUserThrottler = new Throttled();
        }
        try {
            if (!params.containsKey("userId")) {
                throw new Exception("userId was not found");
            }
            String url = ApiClient.getApiClient().getBaseUri() + "/users/%s";
            url = String.format(url, params.get("userId"));
            getUserThrottler.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map<String, String> responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = gson.fromJson(response, GetUserResponse.class);
//                System.out.println("Response: " + response);
            }
//            if (responseMap.containsKey("code") && responseMap.get("code") != )
//            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public Map<String, String> createUser(Map<String, String> params, CreateUserRequest data) throws IOException, InterruptedException {
        Map<String, String> responseMap = null;
        if (createUserThrottler == null) {
            createUserThrottler = new Throttled();
        }
        String url = ApiClient.getApiClient().getBaseUri() + "/users";
        String dataString = gson.toJson(data);
        createUserThrottler.throttle();
        String response = ApiClient.getApiClient().postRequest(url, params, dataString, null, null);
        System.out.println("Response: " + response);
        responseMap = gson.fromJson(response, Map.class);
        return responseMap;
    }

    public Map<String, String> updateUser(Map<String, String> params, UpdateUserRequest data) throws IOException, InterruptedException {
        Map<String, String> responseMap = null;
        if (updateUserThrottler == null) {
            updateUserThrottler = new Throttled();
        }
        String url = ApiClient.getApiClient().getBaseUri() + "/users/%s";
        url = String.format(url, params.get("userId"));
        String dataString = gson.toJson(data);
        updateUserThrottler.throttle();
        String response = ApiClient.getApiClient().patchRequest(url, params, dataString, null, null);
        System.out.println("Response: " + response);
        responseMap = gson.fromJson(response, Map.class);
        return responseMap;
    }

    public Map<String, String> listUsers(Map<String, String> params) {
        Map<String, String> responseMap = null;
        if (listUserThrottler == null) {
            listUserThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/users";
            if (!params.containsKey("page_size"))
                params.put("page_size", "10");
            if (!params.containsKey("page_number"))
                params.put("page_number", "1");
            if (!params.containsKey("status"))
                params.put("status", "active");
            url = Utils.appendToUrl(url, params);
            listUserThrottler.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public Map<String, String> deleteUser(Map<String, String> params) {
        Map<String, String> responseMap = null;
        if (deleteUserThrottler == null) {
            deleteUserThrottler = new Throttled();
        }
        try {
            if (!params.containsKey("userId")) {
                throw new Exception("userId was not found");
            }
            String url = ApiClient.getApiClient().getBaseUri() + "/users/%s";
            url = String.format(url, params.get("userId"));
            if (!params.containsKey("action"))
                params.put("action", "disassociate");
            deleteUserThrottler.throttle();
            String response = ApiClient.getApiClient().deleteRequest(url, params, null, null, null);
            responseMap = gson.fromJson(response, Map.class);
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }
}
