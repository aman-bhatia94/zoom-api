package zoomapi.components.componentResponseData.ChannelResponseData;

import zoomapi.components.componentResponseData.Message;
import zoomapi.components.componentResponseData.Response;

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

    @Override
    public String toString() {
        return "ListUserChatMessagesResponse{" +
                "date='" + date + '\'' +
                ", messages=" + messages +
                '}';
    }
}
