package zoomapi.components;

import utils.ApiClient;
import utils.Utils;
import zoomapi.components.componentRequestData.CreateUserRequest;
import zoomapi.components.componentRequestData.UpdateUserRequest;
import zoomapi.components.componentResponseData.GetUserResponse;
import zoomapi.components.componentResponseData.UserResponseData.CreateUserResponse;
import zoomapi.components.componentResponseData.UserResponseData.ListUsersResponse;

import java.util.Map;

public class UserComponent extends BaseComponent {

    public UserComponent(String baseUri, String token, String clientId) throws Exception {
        super(baseUri, token, clientId);
    }

    public GetUserResponse getUser(Map<String, String> params) {
        GetUserResponse responseData = null;
        try {
            Utils.requireKeys(params, new String[]{"userId"}, false);
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/users/%s", params,
                    new String[]{"userId"}, false);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map<String, String> responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, GetUserResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public CreateUserResponse createUser(Map<String, String> params, CreateUserRequest data) {
        CreateUserResponse responseData = new CreateUserResponse();
        try {
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/users", params, null, false);
            String dataString = GSON.toJson(data);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().postRequest(url, params, dataString, null, null);
            System.out.println("Response: " + response);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, CreateUserResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public Map<String, String> updateUser(Map<String, String> params, UpdateUserRequest data) {
        Map<String, String> responseMap = null;
        try {
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/users/%s", params,
                    new String[]{"userId"}, false);
            String dataString = GSON.toJson(data);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().patchRequest(url, params, dataString, null, null);
            System.out.println("Response: " + response);
            responseMap = GSON.fromJson(response, Map.class);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseMap;
    }

    public ListUsersResponse listUsers(Map<String, String> params) {
        ListUsersResponse responseData = new ListUsersResponse();
        try {
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/users", params, null, true);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, ListUsersResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public Map<String, String> deleteUser(Map<String, String> params) {
        Map<String, String> responseMap = null;
        try {
            if (!params.containsKey("userId")) {
                throw new Exception("userId was not found");
            }
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/users/%s", params,
                    new String[]{"userId"}, false);
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
