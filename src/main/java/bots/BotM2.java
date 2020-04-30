package bots;

import botfacing.BotHelper;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.ini4j.Ini;
import utils.Utils;
import xyz.dmanchon.ngrok.client.NgrokTunnel;
import zoomapi.OAuthZoomClient;
import zoomapi.components.componentResponseData.Message;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnirestException e) {
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
        System.out.println("---SEND MESSAGES---");
        System.out.println("Enter the channel name where you want to send the message");
        String channelName = input.nextLine();
        System.out.println("Enter the message");
        String messageToSend = input.nextLine();
        helper.sendMessages(channelName, messageToSend);

        System.out.println("---HISTORY---");
        System.out.println("Enter the channel name: ");
        channelName = input.nextLine();
        System.out.println("Enter start date in format yyyy-MM-dd: ");
        String fromDate = input.nextLine();
        System.out.println("Enter end date in format yyyy-MM-dd: ");
        String endDate = input.nextLine();
        List<Message> result = helper.history(channelName, Utils.stringToLocaleDate(fromDate), Utils.stringToLocaleDate(endDate));
        System.out.println("History: " + result);

        System.out.println("---SEARCH---");
        System.out.println("Enter the channel name: ");
        channelName = input.nextLine();
        System.out.println("Enter start date in format yyyy-MM-dd: ");
        fromDate = input.nextLine();
        System.out.println("Enter end date in format yyyy-MM-dd: ");
        endDate = input.nextLine();
        System.out.println("Enter message to be searched: ");
        String searchText = input.nextLine();
        result = helper.search(channelName, Utils.stringToLocaleDate(fromDate), Utils.stringToLocaleDate(endDate),
                message -> (message.getMessage().toLowerCase().contains(searchText.toLowerCase())));
        System.out.println("Search: " + result);
    }
}
