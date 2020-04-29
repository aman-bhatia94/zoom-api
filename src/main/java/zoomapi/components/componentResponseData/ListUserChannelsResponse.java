package zoomapi.components.componentResponseData;

import java.util.List;

public class ListUserChannelsResponse extends Response {

    List<ChannelData> channels;

    public List<ChannelData> getChannels() {
        return channels;
    }

    @Override
    public String toString() {
        return "ListUserChannelsResponse{" +
                "channels=" + channels +
                ", total_records=" + total_records +
                '}';
    }
}
