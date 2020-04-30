package zoomapi.components.componentResponseData.MeetingResponseData;

import zoomapi.components.componentRequestData.MeetingRecurrence;
import zoomapi.components.componentRequestData.MeetingSettings;
import zoomapi.components.componentResponseData.Meeting;
import zoomapi.components.componentResponseData.TrackingField;

import java.util.List;

public class CreateMeetingResponse extends Meeting {
    String schedule_for;
    String password;
    List<TrackingField> tracking_fields;
    MeetingRecurrence recurrence;
    MeetingSettings settings;
}
