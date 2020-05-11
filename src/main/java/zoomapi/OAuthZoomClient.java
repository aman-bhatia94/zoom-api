package zoomapi;

import utils.OAuthTokenFetcher;

import java.util.HashMap;

public class OAuthZoomClient extends ZoomClient {

    private final HashMap<String, String> config;
    int timeout;
    String dataType;

    public OAuthZoomClient(String client_id, String client_secret, String port, String redirect_url, String browser_path) {
        super(client_id, client_secret);
        this.config = new HashMap<>();
        this.config.put("client_id", client_id);
        this.config.put("client_secret", client_secret);
        this.config.put("port", port);
        this.config.put("redirect_url", redirect_url);
        this.config.put("browser_path", browser_path);
        String oAuthToken = OAuthTokenFetcher.getOAuthToken(client_id, client_secret, port, redirect_url, browser_path);
        this.config.put("token", oAuthToken);
        this.dataType = "json";
        this.timeout = 15;
    }

    public String getAccessToken() {
        if (config != null && config.containsKey("token")) {
            return config.get("token");
        }
        return null;
    }

    @Override
    //Setting the refresh token
    public void setRefreshToken() {
        String newToken = OAuthTokenFetcher.getOAuthToken(this.config.get("client_id"),
                this.config.get("client_secret"), this.config.get("port"),
                this.config.get("redirect_url"), this.config.get("browser_path"));
        this.config.put("token", newToken);
    }

    //Fetch the redirect URl
    public String getRedirectUrl() {
        return this.config.get("redirect_url");
    }

    //Setter for redirectUrl
    public void setRedirectUrl(String value) {
        this.config.put("redirect_url", value);
        setRefreshToken();
    }
}
