package utils;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAuthzResponse;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OAuthTokenFetcher {


    public static String getOAuthToken(String client_id, String client_secret, String port, String redirect_url, String browser_path) {

        try {
            OAuthClientRequest request = OAuthClientRequest
                    .authorizationLocation("https://zoom.us/oauth/authorize")
                    .setResponseType("code")
                    .setRedirectURI(redirect_url)
                    .setClientId(client_id)
                    .buildQueryMessage();

            //Now we get the generated URL back from Zoom's authorization server with the authorization code
            System.out.println("The authorization code is: \t"+request.getLocationUri());

            //Now we open this generated url in the specified browser
            /*if(Desktop.isDesktopSupported()){
                try {
                    URI uri = new URI(request.getLocationUri());
                    Desktop.getDesktop().browse(uri);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                //For cross platform
                Runtime runtime = Runtime.getRuntime();
                try {
                    runtime.exec(browser_path+request.getLocationUri());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
            try{
                System.out.println(browser_path);
                String[] browserParams = {browser_path,request.getLocationUri()};
                Runtime.getRuntime().exec(browserParams);
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }
        return null;

    }
}
