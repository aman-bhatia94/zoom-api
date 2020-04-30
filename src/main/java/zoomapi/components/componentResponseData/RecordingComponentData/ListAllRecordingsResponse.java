package zoomapi.components.componentResponseData.RecordingComponentData;

import zoomapi.components.componentResponseData.Meeting;
import zoomapi.components.componentResponseData.Response;

import java.util.ArrayList;

public class ListAllRecordingsResponse {

    String from;
    String to;
    Integer page_count;
    Integer page_size;
    Integer total_records;
    Integer next_page_token;
    ArrayList<Meeting> meetings;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Integer getPage_count() {
        return page_count;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public Integer getTotal_records() {
        return total_records;
    }

    public Integer getNext_page_token() {
        return next_page_token;
    }

    public ArrayList<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public String toString() {
        return "ListAllRecordingsResponseData{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", page_count=" + page_count +
                ", page_size=" + page_size +
                ", total_records=" + total_records +
                ", next_page_token=" + next_page_token +
                ", meetings=" + meetings +
                '}';
    }
}
