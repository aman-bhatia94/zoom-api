package zoomapi.components.componentResponseData;

public class ChannelData {
    String id;
    String name;
    Integer type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ChannelData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ChannelData)) {
            return false;
        }
        ChannelData channelData = (ChannelData) obj;
        return channelData.getName().equals(this.name) &&
                channelData.getId().equals(this.id) &&
                channelData.getType().equals(this.type);
    }
}
