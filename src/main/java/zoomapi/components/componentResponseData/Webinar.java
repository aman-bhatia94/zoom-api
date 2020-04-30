package zoomapi.components.componentResponseData;

public class Webinar {

    String uuid;
    Integer id;
    String host_id;
    String topic;
    Integer type;
    Integer duration;
    String timezone;
    String created_at;
    String join_url;
    String agenda;
    String start_time;

    public String getUuid() {
        return uuid;
    }

    public Integer getId() {
        return id;
    }

    public String getHost_id() {
        return host_id;
    }

    public String getTopic() {
        return topic;
    }

    public Integer getType() {
        return type;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getJoin_url() {
        return join_url;
    }

    public String getAgenda() {
        return agenda;
    }

    public String getStart_time() {
        return start_time;
    }

    @Override
    public String toString() {
        return "Webinar{" +
                "uuid='" + uuid + '\'' +
                ", id=" + id +
                ", host_id='" + host_id + '\'' +
                ", topic='" + topic + '\'' +
                ", type=" + type +
                ", duration=" + duration +
                ", timezone='" + timezone + '\'' +
                ", created_at='" + created_at + '\'' +
                ", join_url='" + join_url + '\'' +
                ", agenda='" + agenda + '\'' +
                ", start_time='" + start_time + '\'' +
                '}';
    }
}
