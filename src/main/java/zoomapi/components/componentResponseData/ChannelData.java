package zoomapi.components.componentResponseData;

public class ChannelData {
    String id;
    String name;
    Integer type;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ChannelData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
