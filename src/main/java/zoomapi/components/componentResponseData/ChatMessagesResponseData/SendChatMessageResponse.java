package zoomapi.components.componentResponseData.ChatMessagesResponseData;

public class SendChatMessageResponse {

    String id;

    public String getId(){
        return id;
    }

    @Override
    public String toString() {
        return "SendChatMessageResponse{" +
                "id='" + id + '\'' +
                '}';
    }
}
