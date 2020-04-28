package zoomapi.components.componentResponseData;

import java.util.List;

public class ListUserChannelsResponse {
    Integer total_records;
    Integer page_size;
    String next_page_token;
    List<ChannelData> channels;
}
