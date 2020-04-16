import utils.OAuthTokenFetcher;

import java.util.HashMap;

public class OAuthZoomClient extends ZoomClient{


    private HashMap<String,String> config;
    private HashMap<String,String> components;
    int timeout;
    String dataType;
    public OAuthZoomClient(String client_id, String client_secret, String port, String redirect_url, String browser_path) {

        super(client_id,client_secret);
        this.config = new HashMap<>();
        this.config.put("client_id",client_id);
        this.config.put("client_secret",client_secret);
        this.config.put("port",port);
        this.config.put("redirect_url",redirect_url);
        this.config.put("browser_path",browser_path);
        String oAuthToken = OAuthTokenFetcher.getOAuthToken(client_id, client_secret, port, redirect_url, browser_path);
        this.config.put("token",oAuthToken);
        this.dataType = "json";
        this.timeout = 15;

        this.components = new HashMap<>();
        /**TODO
         * implement components and create the hashmap here
         */

    }
}
