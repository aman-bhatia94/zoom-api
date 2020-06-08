package zoomapi.components;

import com.google.gson.Gson;
import utils.ApiClient;
import utils.DatabaseConnection;
import utils.Throttled;
import utils.Utils;

import java.net.URISyntaxException;
import java.util.Map;

public class BaseComponent {
    static final Gson GSON = new Gson();
    static final Throttled THROTTLED = new Throttled();

    public BaseComponent(String baseUri, String token, String clientId) {
        ApiClient.init(baseUri, token);
        DatabaseConnection.init();
    }

    public static String getUrl(String baseUrl, String apiUrl, Map<String, String> params, String[] urlParams,
                                boolean appendToUrl) throws URISyntaxException {
        String url = baseUrl + apiUrl;
        if (urlParams != null && urlParams.length > 0) {
            for (String param : urlParams) {
                url = String.format(url, params.get(param));
            }
        }
        if (appendToUrl) {
            url = Utils.appendToUrl(url, params);
        }
        return url;
    }
}
