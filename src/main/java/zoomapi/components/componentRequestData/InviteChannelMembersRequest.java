package zoomapi.components.componentRequestData;

import java.util.List;

public class InviteChannelMembersRequest {
    List<Member> members;

    public InviteChannelMembersRequest() {
        members = null;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
