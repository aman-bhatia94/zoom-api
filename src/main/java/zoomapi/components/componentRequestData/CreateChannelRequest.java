package zoomapi.components.componentRequestData;

import java.util.List;

public class CreateChannelRequest {
    String name;
    Integer type;
    List<Member> members;

    public CreateChannelRequest() {
        name = null;
        type = null;
        members = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
