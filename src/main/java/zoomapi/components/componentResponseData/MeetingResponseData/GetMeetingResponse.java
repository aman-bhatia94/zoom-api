package zoomapi.components.componentResponseData.MeetingResponseData;

import zoomapi.components.componentRequestData.MeetingRecurrence;
import zoomapi.components.componentRequestData.MeetingSettings;
import zoomapi.components.componentResponseData.Meeting;
import zoomapi.components.componentResponseData.MeetingOccurrence;
import zoomapi.components.componentResponseData.TrackingField;

public class GetMeetingResponse extends Meeting {
    String status;
    String password;
    String h323_password;
    String encrypted_password;
    String pmi;
    TrackingField tracking_fields;
    MeetingOccurrence occurrences;
    MeetingSettings settings;
    MeetingRecurrence recurrence;

    public String getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public String getH323_password() {
        return h323_password;
    }

    public String getEncrypted_password() {
        return encrypted_password;
    }

    public String getPmi() {
        return pmi;
    }

    public TrackingField getTracking_fields() {
        return tracking_fields;
    }

    public MeetingOccurrence getOccurrences() {
        return occurrences;
    }

    public MeetingSettings getSettings() {
        return settings;
    }

    public MeetingRecurrence getRecurrence() {
        return recurrence;
    }

    @Override
    public String toString() {
        return "GetMeetingResponse{" +
                "password='" + password + '\'' +
                ", pmi='" + pmi + '\'' +
                ", tracking_fields=" + tracking_fields +
                ", occurrences=" + occurrences +
                ", settings=" + settings +
                ", recurrence=" + recurrence +
                '}';
    }
}
