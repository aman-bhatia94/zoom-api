package zoomapi.components.componentResponseData;

public class MeetingOccurrence {
    String occurrence_id;
    String start_time;
    Integer duration;
    String status;

    public String getOccurrence_id() {
        return occurrence_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "MeetingOccurrence{" +
                "occurrence_id='" + occurrence_id + '\'' +
                ", start_time='" + start_time + '\'' +
                ", duration=" + duration +
                ", status='" + status + '\'' +
                '}';
    }
}
