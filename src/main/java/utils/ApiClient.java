package utils;

import com.google.gson.Gson;

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

    String baseUri;
    int timeout;
    //TODO check this please
    String token;

    //todo check if its needed
    private static final Gson gson = new Gson();

    public ApiClient(String baseUri, String token) {
        this.baseUri = baseUri;
        this.timeout = 15;
        this.token = token;

        //TODO kwargs
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

    // Get the URL for the given endpoint
    // param endpoint: The endpoint
    // return: The full URL for the endpoint
    String urlFor(String endPoint) {
        if (!endPoint.startsWith("/"))
            endPoint = String.format("/%s", endPoint);
        if (endPoint.endsWith("/"))
            endPoint = endPoint.substring(0, -1);
        return this.baseUri + endPoint;
    }

    // Helper function for GET requests
    // :param endpoint: The endpoint
    // :param params: The URL parameters
    // :param headers: request headers
    // :return: The :class:``requests.Response`` object for this request
    public String getRequest(String endPoint, Map<String, String> params, Map<String, String> headers) throws IOException, InterruptedException {
        if (headers == null) {
            headers = new HashMap<>();
            headers.put("Authorization", String.format("Bearer %s", token));
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endPoint))
                .header(Utils.trimByOne(String.valueOf(headers.keySet())), Utils.trimByOne(String.valueOf(headers.values())))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    // Helper function for POST requests
    // :param endpoint: The endpoint
    // :param params: The URL parameters
    // :param data: The data (either as a dict or dumped JSON string) to include with the POST
    // :param headers: request headers
    // :param cookies: request cookies
    // :param cookies: request cookies
    // :return: The :class:``requests.Response`` object for this request
    public String postRequest(String endPoint, Map<String, String> param, String data,
                              Map<String, String> headers, Map<String, String> cookies) throws IOException, InterruptedException {

        data = getStringFromData(data);
        if (headers == null) {
            headers.put("Authorization", String.format("Bearer %s", token));
        }
        headers.put("Content-type", "application/json");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endPoint))
                .header(Utils.trimByOne(String.valueOf(headers.keySet())), Utils.trimByOne(String.valueOf(headers.values())))
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
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

        data = getStringFromData(data);
        if (headers == null) {
            headers.put("Authorization", String.format("Bearer %s", token));
        }
        headers.put("Content-type", "application/json");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endPoint))
                .method("PATCH", HttpRequest.BodyPublishers.ofString(data))
                .header(Utils.trimByOne(String.valueOf(headers.keySet())), Utils.trimByOne(String.valueOf(headers.values())))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
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
        data = getStringFromData(data);
        if (headers == null) {
            headers.put("Authorization", String.format("Bearer %s", token));
        }
        headers.put("Content-type", "application/json");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .header(Utils.trimByOne(String.valueOf(headers.keySet())), Utils.trimByOne(String.valueOf(headers.values())))
                .DELETE()
                .uri(URI.create(endPoint))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
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
        data = getStringFromData(data);
        if (headers == null) {
            headers.put("Authorization", String.format("Bearer %s", token));
        }
        headers.put("Content-type", "application/json");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .header(Utils.trimByOne(String.valueOf(headers.keySet())), Utils.trimByOne(String.valueOf(headers.values())))
                .PUT(HttpRequest.BodyPublishers.ofString(data))
                .uri(URI.create(endPoint))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }


    //TODO endpoint,requests

    public static boolean isJSONValid(String jsonInString) {
        try {
            gson.fromJson(jsonInString, Object.class);
            return true;
        } catch (com.google.gson.JsonSyntaxException ex) {
            return false;
        }
    }

    private String getStringFromData(String data) {
        if (isJSONValid(data)) {
            gson.fromJson(data, Object.class);
            data = gson.toString();
        }
        return data;
    }
}
