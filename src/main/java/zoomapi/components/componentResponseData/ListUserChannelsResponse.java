package zoomapi.components.componentResponseData;

import java.util.List;

public class ListUserChannelsResponse {
    Integer total_records;
    Integer page_size;
    String next_page_token;
    List<ChannelData> channels;

    public Integer getTotal_records() {
        return total_records;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public String getNext_page_token() {
        return next_page_token;
    }

    public List<ChannelData> getChannels() {
        return channels;
    }
}
