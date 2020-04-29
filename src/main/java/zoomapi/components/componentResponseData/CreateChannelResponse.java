package zoomapi.components.componentResponseData;

public class CreateChannelResponse extends ChannelData {
    String jid;

    public String getJid() {
        return jid;
    }

    @Override
    public String toString() {
        return "CreateChannelResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
