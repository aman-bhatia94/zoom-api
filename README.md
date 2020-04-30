# zoom-api

NOTE: Add the http://ngrok.io/  url to whiteurl on zoom before testing the bot 
NOTE: If you are using intellij and get errors on import statements, invalidate caches and restart your intellij environment

1. Extract your zip file
2. Go into the extracted folder 
3. Go into src/main/java/bots to find bot.ini.example. Create a bot.ini file and put your credentials in the bot.ini file. Make sure you look at bot.ini.example for reference because browser. 
4. Start the tunnel using “ngrok start —none” - double hyphen before none
5. Open a new terminal tab, go to the root folder(where pom.xml is present)
6. Build the project using the following
    1. mvn clean install
    2. Run it using mvn exec:java
