package botfacing;

public class BotHelper {

    private String baseUrl;
    private String accessToken;
    private String clientId;
    public String clientSecret;
    public String port;
    public String browserPath;
    public String redirect_url;

    public BotHelper(String baseUrl, String accessToken, String clientId, String clientSecret, String port, String browserPath, String redirect_url) {
        this.baseUrl = baseUrl;
        this.accessToken = accessToken;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.port = port;
        this.browserPath = browserPath;
        this.redirect_url = redirect_url;
    }

    public String sendChatMessages(String channelName, String message){


        return null;
    }
}
