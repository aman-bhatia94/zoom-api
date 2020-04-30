package zoomapi.components.componentResponseData.MeetingResponseData;

import zoomapi.components.componentResponseData.Meeting;
import zoomapi.components.componentResponseData.Response;

import java.util.List;

public class ListMeetingsResponse extends Response {

    List<Meeting> meetings;

    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public String toString() {
        return "ListMeetingsResponse{" +
                "meetings=" + meetings +
                '}';
    }
}
