package zoomapi.components;

import com.google.gson.Gson;
import utils.ApiClient;

public class BaseComponent {
    static final Gson gson = new Gson();

    public BaseComponent(String baseUri, String token) {
        ApiClient.init(baseUri, token);
    }

}
