package utils;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;

public class OAuthTokenFetcher {

    static ServerSocket server;
    static String accessToken;

    public static String getOAuthToken(String client_id, String client_secret, String port, String redirect_url, String browser_path) {

        try {
            server = new ServerSocket(Integer.parseInt(port));
            OAuthClientRequest request = OAuthClientRequest
                    .authorizationLocation("https://zoom.us/oauth/authorize")
                    .setResponseType("code")
                    .setRedirectURI(redirect_url)
                    .setClientId(client_id)
                    .buildQueryMessage();

            //Now we get the generated URL back from Zoom's authorization server with the authorization code
            System.out.println("The generated URL is: \t" + request.getLocationUri());
            try {
                //System.out.println(browser_path);
                String[] browserParams = {browser_path, request.getLocationUri()};
                Runtime.getRuntime().exec(browserParams);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Now we need to get the response after we send the generated url to request authorization from zoom server
            //We establish a server on the same port, and a client that listens to it, the client will thus
            //receive a response from the server which contains the auth code
            String code = httpReceiver(port);
            System.out.println(code);
            //Now that we have received our code, we try and exchange it for access token
            OAuthClientRequest clientRequest = OAuthClientRequest
                    .tokenLocation("https://zoom.us/oauth/token")
                    .setGrantType(GrantType.AUTHORIZATION_CODE)
                    .setRedirectURI(redirect_url)
                    .setCode(code)
                    .buildQueryMessage();

            String encodeForHeader = Base64.getEncoder().encodeToString((client_id + ":" + client_secret).getBytes());
            clientRequest.setHeader("Authorization", "Basic " + encodeForHeader);

            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            OAuthResourceResponse resourceResponse = oAuthClient.resource(clientRequest, OAuth.HttpMethod.POST, OAuthResourceResponse.class);
            JSONObject jsonResponse = new JSONObject(resourceResponse.getBody());
            accessToken = jsonResponse.get("access_token").toString();
            System.out.println("access_token" + accessToken);

        } catch (OAuthSystemException | IOException | OAuthProblemException e) {
            e.printStackTrace();
        }
        return accessToken;

    }

    public static String httpReceiver(String port) {
        String returnedString = null;
        String code = null;
        try {
            Socket client = server.accept();
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            returnedString = input.readLine();
            assert returnedString != null;
            String[] tokens = returnedString.split("code=");
            String[] tempTokens2 = tokens[1].split(" ");
            code = tempTokens2[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }
}
