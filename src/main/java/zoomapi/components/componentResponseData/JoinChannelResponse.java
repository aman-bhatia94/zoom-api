package zoomapi.components.componentResponseData;

public class JoinChannelResponse {
    String id;
    String added_at;

    public String getId() {
        return id;
    }

    public String getAdded_at() {
        return added_at;
    }

    @Override
    public String toString() {
        return "JoinChannelResponse{" +
                "id='" + id + '\'' +
                ", added_at='" + added_at + '\'' +
                '}';
    }
}
