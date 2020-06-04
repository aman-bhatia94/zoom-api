# zoom-api

### NOTE: Add the http://ngrok.io  url to whiteurl on zoom before testing the bot, dont add / after .io 
### NOTE: If you are using intellij and get errors on import statements, invalidate caches and restart your intellij environment
### NOTE: We have changed the throttling code. We took this decision because zoom had made their rate limits very strict. So we throttle between each request now, we request that you be patient as our bot might take a second or two to execute what you do. 
### NOTE: If you feel the program is slow, you could try to change SLEEP_TIME in Throttled to make it fast.
### NOTE: Please add the url of the zoom db inside src/main/java/services/DatabaseConnectionService.java inside the connect method. The provided one is default. (we will have to have a separate config file for the server side stuff, which will include this, but for this iteration(milestone 5), we let it stay hardcoded

1. Extract your zip file
2. Go into the extracted folder 
3. Go into src/main/java/bots to find bot.ini.example. Create a bot.ini file and put your credentials in the bot.ini file. Make sure you look at bot.ini.example for reference because browser. 
4. Start the tunnel using “ngrok start —none” - double hyphen before none
5. Open a new terminal tab, go to the root folder(where pom.xml is present)
6. Build the project using the following
    1. mvn clean install
    2. Run it using mvn exec:java
