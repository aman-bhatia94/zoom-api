package zoomapi.components.componentResponseData.ChannelResponseData;

import zoomapi.components.componentResponseData.ChannelData;

public class GetChannelResponse extends ChannelData {
    @Override
    public String toString() {
        return "GetChannelResponse{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", type=" + getType() +
                '}';
    }
}
