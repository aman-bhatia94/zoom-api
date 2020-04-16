package utils;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAuthzResponse;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

public class OAuthTokenFetcher {


    public static String getOAuthToken(String client_id, String client_secret, String port, String redirect_url, String browser_path) {

        try {
            OAuthClientRequest request = OAuthClientRequest
                    .authorizationLocation("ttps://zoom.us/oauth/authorize")
                    .setClientId(client_id)
                    .setRedirectURI(redirect_url)
                    .buildQueryMessage();

            OAuthAuthzResponse oar = OAuthAuthzResponse.oauthCodeAuthzResponse(request);
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }
        return null;

    }
}
