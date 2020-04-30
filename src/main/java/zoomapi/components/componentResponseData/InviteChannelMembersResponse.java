package zoomapi.components.componentResponseData;

public class InviteChannelMembersResponse {
    String ids;
    String added_at;

    public String getIds() {
        return ids;
    }

    public String getAdded_at() {
        return added_at;
    }

    @Override
    public String toString() {
        return "InviteChannelMembersResponse{" +
                "ids=" + ids +
                ", added_at='" + added_at + '\'' +
                '}';
    }
}
