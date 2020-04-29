package zoomapi.components.componentResponseData;

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
                ", total_records=" + total_records +
                '}';
    }
}
