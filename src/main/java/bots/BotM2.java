package bots;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.ini4j.Ini;
import xyz.dmanchon.ngrok.client.NgrokTunnel;
import java.io.File;
import java.io.IOException;

public class BotM2 {

    public static String clientId;
    public static String clientSecret;
    public static int port;
    public static String browserPath;
    public static String redirect_url;
    public static NgrokTunnel tunnel;
    public static void parseBotIniFile(String filepath){

        try {
            Ini ini = new Ini(new File(filepath));
            clientId = ini.get("OAuth","client_id");
            clientSecret = ini.get("OAuth","client_secret");
            port = Integer.parseInt(ini.get("OAuth","port"));
            browserPath = ini.get("OAuth","browser_path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void connect(){
        try {
            tunnel = new NgrokTunnel(port);
            redirect_url = tunnel.url();

        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect(){
        try {
            tunnel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static void display() {
        System.out.println("Client ID: "+ clientId);
        System.out.println("Client Secret: "+ clientSecret);
        System.out.println("Port: "+port);
        System.out.println("Browser Path: "+browserPath);
        System.out.println("Redirect_URL: "+redirect_url);
    }

    public static void main(String[] args) {


        BotM2.parseBotIniFile("src/main/java/bots/bot.ini");
        BotM2.connect(); //creating ngrock tunnel
        BotM2.display();

    }
}
