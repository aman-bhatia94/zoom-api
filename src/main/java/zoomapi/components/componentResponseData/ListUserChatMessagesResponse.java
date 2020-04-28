package zoomapi.components.componentResponseData;

import java.util.List;

public class ListUserChatMessagesResponse extends Response {
    String date;
    List<Message> messages;

    public String getDate() {
        return date;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
