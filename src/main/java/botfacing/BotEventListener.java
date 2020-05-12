package botfacing;

public interface BotEventListener {
    void onNewMessageEvent(Object[] arg);

    void onNewChannelUserEvent(Object[] arg);

    void onMessageUpdateEvent(Object[] arg);

}
