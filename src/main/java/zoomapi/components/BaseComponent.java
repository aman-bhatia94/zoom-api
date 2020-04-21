package zoomapi.components;

import utils.ApiClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseComponent extends ApiClient {

    int timeout;
    String token;
    public BaseComponent(String baseUri, String token) {
        super(baseUri, token);
        this.timeout = 15;
        this.token = token;

    }

    public String postRequest(String endPoint, Map<String, String> params, String data,
                              Map<String, String> headers, Map<String, String> cookies) throws IOException, InterruptedException{


        HashMap<String,String> param = (HashMap<String, String>) params;
        if (headers == null) {
            headers.put("Authorization", String.format("Bearer %s", token));
        }
        return super.postRequest(endPoint, params, data, headers, cookies);

    }
}
