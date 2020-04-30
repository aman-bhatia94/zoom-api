package zoomapi.components.componentResponseData.ChannelResponseData;

import zoomapi.components.componentResponseData.Member;
import zoomapi.components.componentResponseData.Response;

import java.util.List;

public class ListChannelMemberResponse extends Response {
    List<Member> members;

    public List<Member> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "ListChannelMemberResponse{" +
                "members=" + members +
                ", total_records=" + getTotal_records() +
                '}';
    }
}
