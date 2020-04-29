package zoomapi.components.componentResponseData;

import java.util.Arrays;

public class InviteChannelMembersResponse {
    String[] ids;
    String added_at;

    public String[] getIds() {
        return ids;
    }

    public String getAdded_at() {
        return added_at;
    }

    @Override
    public String toString() {
        return "InviteChannelMembersResponse{" +
                "ids=" + Arrays.toString(ids) +
                ", added_at='" + added_at + '\'' +
                '}';
    }
}
