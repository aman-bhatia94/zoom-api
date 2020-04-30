package zoomapi.components.componentResponseData;

public class RecordingFiles {

    String id;
    String meeting_id;
    String recording_start;
    String recording_end;
    String file_type;
    Double file_size;
    String play_url;
    String download_url;
    String status;
    String deleted_time;
    String recording_type;

    public String getId() {
        return id;
    }

    public String getMeeting_id() {
        return meeting_id;
    }

    public String getRecording_start() {
        return recording_start;
    }

    public String getRecording_end() {
        return recording_end;
    }

    public String getFile_type() {
        return file_type;
    }

    public Double getFile_size() {
        return file_size;
    }

    public String getPlay_url() {
        return play_url;
    }

    public String getDownload_url() {
        return download_url;
    }

    public String getStatus() {
        return status;
    }

    public String getDeleted_time() {
        return deleted_time;
    }

    public String getRecording_type() {
        return recording_type;
    }

    @Override
    public String toString() {
        return "RecordingFiles{" +
                "id='" + id + '\'' +
                ", meeting_id='" + meeting_id + '\'' +
                ", recording_start='" + recording_start + '\'' +
                ", recording_end='" + recording_end + '\'' +
                ", file_type='" + file_type + '\'' +
                ", file_size=" + file_size +
                ", play_url='" + play_url + '\'' +
                ", download_url='" + download_url + '\'' +
                ", status='" + status + '\'' +
                ", deleted_time='" + deleted_time + '\'' +
                ", recording_type='" + recording_type + '\'' +
                '}';
    }
}
