# zoom-api

1. Extract your zip file
2. Go into the extracted folder
3. Put your credentials in the bot.ini file
4. Start the tunnel using “ngrok start —none” - double hyphen before none
5. Open a new terminal tab, go to the root folder(where pom.xml is present)
6. Build the project using the following
    1. mvn clean install
    2. Run it using mvn exec:java
