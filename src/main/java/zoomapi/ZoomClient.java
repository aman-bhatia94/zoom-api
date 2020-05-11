package zoomapi;

import java.util.HashMap;

public class ZoomClient {
    public static final String API_BASE_URIS = "https://api.zoom.us/v2";
    private final int timeout;
    private final String dataType;
    private final String BASE_URI;
    private final HashMap<String, String> config;

    public ZoomClient(String apiKey, String apiSecret) {
        this.config = new HashMap<>();
        this.BASE_URI = API_BASE_URIS;
        this.timeout = 15;
        this.dataType = "json";
        this.config.put("api_key", apiKey);
        this.config.put("api_secret", apiSecret);
        this.config.put("data_type", this.dataType);
    }


    public void setRefreshToken() {
        //TODO
    }

    //get api key
    public String getApiKey() {
        return this.config.get("api_key");
    }

    //set api key
    public void setAPiKey(String value) {
        this.config.put("api_key", value);
        setRefreshToken();
    }

    //get api secret
    public String getApiSecret() {
        return this.config.get("api_secret");
    }

    //set api secret
    public void setAPiSecret(String value) {
        this.config.put("api_secret", value);
        setRefreshToken();
    }
}
