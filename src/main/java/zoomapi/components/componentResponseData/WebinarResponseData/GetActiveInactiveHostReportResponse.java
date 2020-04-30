package zoomapi.components.componentResponseData.WebinarResponseData;

import zoomapi.components.componentResponseData.Users;

import java.util.ArrayList;

public class GetActiveInactiveHostReportResponse {

    String from;
    String to;
    Integer page_size;
    Integer total_records;
    Integer page_count;
    Integer page_number;
    Integer total_meetings;
    Integer total_participants;
    Integer total_meeting_minutes;
    ArrayList<Users> users;


    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public Integer getTotal_records() {
        return total_records;
    }

    public Integer getPage_count() {
        return page_count;
    }

    public Integer getPage_number() {
        return page_number;
    }

    public Integer getTotal_meetings() {
        return total_meetings;
    }

    public Integer getTotal_participants() {
        return total_participants;
    }

    public Integer getTotal_meeting_minutes() {
        return total_meeting_minutes;
    }

    public ArrayList<Users> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "GetActiveInactiveHostReportResponse{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", page_size=" + page_size +
                ", total_records=" + total_records +
                ", page_count=" + page_count +
                ", page_number=" + page_number +
                ", total_meetings=" + total_meetings +
                ", total_participants=" + total_participants +
                ", total_meeting_minutes=" + total_meeting_minutes +
                ", users=" + users +
                '}';
    }


}
