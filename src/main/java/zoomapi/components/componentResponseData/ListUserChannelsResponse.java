package zoomapi.components.componentResponseData;

import java.util.List;

public class ListUserChannelsResponse extends Response {

    List<ChannelData> channels;

    public List<ChannelData> getChannels() {
        return channels;
    }
}
