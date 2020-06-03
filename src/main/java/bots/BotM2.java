package bots;

import botfacing.BotHelper;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.ini4j.Ini;
import xyz.dmanchon.ngrok.client.NgrokTunnel;
import zoomapi.OAuthZoomClient;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BotM2 {

    public static String clientId;
    public static String clientSecret;
    public static String port;
    public static String browserPath;
    public static String redirect_url;
    public static NgrokTunnel tunnel;

    public static void parseBotIniFile(String filepath) {

        try {
            Ini ini = new Ini(new File(filepath));
            clientId = ini.get("OAuth", "client_id");
            clientSecret = ini.get("OAuth", "client_secret");
            port = ini.get("OAuth", "port");
            browserPath = ini.get("OAuth", "browser_path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void connect() {
        try {
            int portInteger = Integer.parseInt(port);
            tunnel = new NgrokTunnel(portInteger);
            redirect_url = tunnel.url();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            tunnel.close();
        } catch (IOException | UnirestException e) {
            e.printStackTrace();
        }
    }

    public static void display() {
        System.out.println("Client ID: " + clientId);
        System.out.println("Client Secret: " + clientSecret);
        System.out.println("Port: " + port);
        System.out.println("Browser Path: " + browserPath);
        System.out.println("Redirect_URL: " + redirect_url);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        BotM2.parseBotIniFile("src/main/java/bots/bot.ini");
        BotM2.connect(); //creating ngrock tunnel
        BotM2.display();
        OAuthZoomClient client = new OAuthZoomClient(clientId, clientSecret, port, redirect_url, browserPath);


        final String baseURL = "https://api.zoom.us/v2";
        final String accessToken = client.getAccessToken();

        BotHelper helper = new BotHelper(baseURL, accessToken, clientId, clientSecret, port, redirect_url, browserPath);

        System.out.println("Enter the channel name for new message event: ");
        String channelName = input.nextLine();
        System.out.println("---STARTING THE NEW MESSAGE EVENT IN THE BACKGROUND---");
        helper.registerNewMessageEvent(channelName);
        System.out.println("---STARTING THE NEW MEMBER EVENT IN THE BACKGROUND---");
        helper.registerNewMemberAddedEvent();
        System.out.println("Enter the channel name for update message event: ");
        channelName = input.nextLine();
        System.out.println("---STARTING THE UPDATE MESSAGE EVENT IN THE BACKGROUND---");
        helper.registerUpdateMessageEvent(channelName);
    }
}
