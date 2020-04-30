package zoomapi.components.componentResponseData.ReportComponentResponseData;

import zoomapi.components.componentResponseData.Meeting;

import java.util.ArrayList;

public class GetMeetingReportResponse {

    String from;
    String to;
    Integer page_size;
    String next_page_token;
    Integer total_records;
    Integer page_count;
    Integer page_number;

    //Improve later for modification, a few objects remain
    ArrayList<Meeting> meetings;


    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public String getNext_page_token() {
        return next_page_token;
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

    public ArrayList<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public String toString() {
        return "GetMeetingReportResponse{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", page_size=" + page_size +
                ", next_page_token='" + next_page_token + '\'' +
                ", total_records=" + total_records +
                ", page_count=" + page_count +
                ", page_number=" + page_number +
                ", meetings=" + meetings +
                '}';
    }
}
