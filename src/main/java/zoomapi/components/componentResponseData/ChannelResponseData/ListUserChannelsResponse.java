package zoomapi.components.componentResponseData.ChannelResponseData;

import zoomapi.components.componentResponseData.ChannelData;
import zoomapi.components.componentResponseData.Response;

import java.util.List;

public class ListUserChannelsResponse extends Response {

    List<ChannelData> channels;

    public List<ChannelData> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelData> channels) {
        this.channels = channels;
    }

    @Override
    public String toString() {
        return "ListUserChannelsResponse{" +
                "channels=" + channels +
                ", total_records=" + getTotal_records() +
                '}';
    }
}
