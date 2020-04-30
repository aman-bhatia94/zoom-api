package zoomapi.components.componentResponseData.ChannelResponseData;

import zoomapi.components.componentResponseData.ChannelData;

public class CreateChannelResponse extends ChannelData {
    String jid;

    public String getJid() {
        return jid;
    }

    @Override
    public String toString() {
        return "CreateChannelResponse{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", type=" + getType() +
                '}';
    }
}
