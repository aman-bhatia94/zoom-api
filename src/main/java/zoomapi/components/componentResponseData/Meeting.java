package zoomapi.components.componentResponseData;

import java.util.ArrayList;

public class Meeting {

    String uuid;
    String id;
    String account_id;
    String host_id;
    String topic;
    Integer type;
    String start_time;
    Integer duration;
    String timezone;
    String created_at;
    String join_url;
    String agenda;
    String total_size;
    String recording_count;


    ArrayList<RecordingFiles> recording_files;

    public Integer getType() {
        return type;
    }

    public String getUuid() {
        return uuid;
    }

    public String getId() {
        return id;
    }

    public String getAccount_id() {
        return account_id;
    }


    public String getHost_id() {
        return host_id;
    }

    public String getTopic() {
        return topic;
    }

    public String getStart_time() {
        return start_time;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getTotal_size() {
        return total_size;
    }

    public String getRecording_count() {
        return recording_count;
    }

    public ArrayList<RecordingFiles> getRecording_files() {
        return recording_files;
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

    @Override
    public String toString() {
        return "Meeting{" +
                "uuid='" + uuid + '\'' +
                ", id='" + id + '\'' +
                ", account_id='" + account_id + '\'' +
                ", host_id='" + host_id + '\'' +
                ", topic='" + topic + '\'' +
                ", start_time='" + start_time + '\'' +
                ", duration=" + duration +
                ", total_size='" + total_size + '\'' +
                ", recording_count='" + recording_count + '\'' +
                ", recording_files=" + recording_files +
                '}';
    }
}
