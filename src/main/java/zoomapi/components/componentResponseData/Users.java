package zoomapi.components.componentResponseData;

public class Users {

    String id;
    String email;
    String user_name;
    Integer type;
    String dept;
    Integer meetings;
    Integer participants;
    Integer meeting_minutes;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUser_name() {
        return user_name;
    }

    public Integer getType() {
        return type;
    }

    public String getDept() {
        return dept;
    }

    public Integer getMeetings() {
        return meetings;
    }

    public Integer getParticipants() {
        return participants;
    }

    public Integer getMeeting_minutes() {
        return meeting_minutes;
    }


    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", user_name='" + user_name + '\'' +
                ", type=" + type +
                ", dept='" + dept + '\'' +
                ", meetings=" + meetings +
                ", participants=" + participants +
                ", meeting_minutes=" + meeting_minutes +
                '}';
    }
}
