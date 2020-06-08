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

    public void setDate(String date) {
        this.date = date;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "ListUserChatMessagesResponse{" +
                "date='" + date + '\'' +
                ", messages=" + messages +
                '}';
    }
}
