package utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

//This class is in a package utils since the class was present in python version of utils.py
//Ask a question for this design decision
public class ApiClient {

    private static ApiClient apiClient;
    private final String token;
    private String baseUri;
    private int timeout;

    private ApiClient(String baseUri, String token) {
        this.baseUri = baseUri;
        this.timeout = 15;
        this.token = token;
    }

    public static void init(String baseUri, String token) {
        apiClient = new ApiClient(baseUri, token);
    }

    public static ApiClient getApiClient() {
        return apiClient;
    }

    //get timeout
    public int getTimeout() {
        return timeout;
    }

    //set timeout
    public void setTimeout(int value) {
        this.timeout = value;
    }

    //get base_uri
    public String getBaseUri() {
        return this.baseUri;
    }

    //setting the base uri
    public void setBaseUri(String value) {
        if (value != null) {
            if (value.endsWith("/")) {
                value = value.substring(0, value.length() - 1);
            }
        }
        this.baseUri = value;
    }

    // Helper function for GET requests
    // :param endpoint: The endpoint
    // :param params: The URL parameters
    // :param headers: request headers
    // :return: The :class:``requests.Response`` object for this request
    public String getRequest(String endPoint, Map<String, String> params, Map<String, String> headers) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        headers = getHeader(headers, false);
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(endPoint));

        return getResponse(headers, client, builder);
    }

    // Helper function for POST requests
    // :param endpoint: The endpoint
    // :param params: The URL parameters
    // :param data: The data (either as a dict or dumped JSON string) to include with the POST
    // :param headers: request headers
    // :param cookies: request cookies
    // :param cookies: request cookies
    // :return: The :class:``requests.Response`` object for this request
    public String postRequest(String endPoint, Map<String, String> params, String data,
                              Map<String, String> headers, Map<String, String> cookies) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        headers = getHeader(headers, true);
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(endPoint))
                .POST(HttpRequest.BodyPublishers.ofString(data));
        return getResponse(headers, client, builder);
    }

    // Helper function for PATCH requests
    // :param endpoint: The endpoint
    // :param params: The URL parameters
    // :param data: The data (either as a dict or dumped JSON string) to include with the PATCH
    // :param headers: request headers
    // :param cookies: request cookies
    // :return: The :class:``requests.Response`` object for this request
    public String patchRequest(String endPoint, Map<String, String> params, String data,
                               Map<String, String> headers, Map<String, String> cookies) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        headers = getHeader(headers, true);
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(endPoint))
                .method("PATCH", HttpRequest.BodyPublishers.ofString(data));
        return getResponse(headers, client, builder);
    }

    // Helper function for DELETE requests
    // :param endpoint: The endpoint
    // :param params: The URL
    // :param data: The data (either as a dict or dumped JSON string) to include with the DELETE
    // :param headers: request headers
    // :param cookies: request cookies
    // :return: The :class:``requests.Response`` object for this request
    public String deleteRequest(String endPoint, Map<String, String> params, String data,
                                Map<String, String> headers, Map<String, String> cookies) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        headers = getHeader(headers, true);
        HttpRequest.Builder builder = HttpRequest.newBuilder().DELETE()
                .uri(URI.create(endPoint));
        return getResponse(headers, client, builder);
    }

    // Helper function for PUT requests
    // :param endpoint: The endpoint
    // :param params: The URL parameters
    // :param data: The data (either as a dict or dumped JSON string) to include with the PUT
    // :param headers: request headers
    // :param cookies: request cookies
    // :return: The :class:``requests.Response`` object for this request
    public String putRequest(String endPoint, Map<String, String> params, String data,
                             Map<String, String> headers, Map<String, String> cookies) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        headers = getHeader(headers, true);
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(data))
                .uri(URI.create(endPoint));
        return getResponse(headers, client, builder);
    }


    //private helper methods
    private String getResponse(Map<String, String> headers, HttpClient client, HttpRequest.Builder builder) throws IOException, InterruptedException {
        for (String key : headers.keySet()) {
            builder.setHeader(key, headers.get(key));
        }
        HttpRequest request = builder.build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private Map<String, String> getHeader(Map<String, String> headers, boolean addDefaultContentType) {
        if (headers == null) {
            headers = new HashMap<>();
            headers.put("Authorization", String.format("Bearer %s", token));
        }
        if (addDefaultContentType) {
            headers.put("Content-type", "application/json");
        }
        return headers;
    }
}
