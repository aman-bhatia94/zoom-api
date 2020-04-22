package zoomapi.components.componentRequestData;

public class CreateWebinarRequest {
    String topic;
    Integer type;
    String start_time;
    Integer duration;
    String timezone;
    String password;
    String agenda;
    WebinarRecurrence recurrence;
    WebinarSettings settings;

    public CreateWebinarRequest() {
        topic = null;
        type = null;
        start_time = null;
        duration = null;
        timezone = null;
        password = null;
        agenda = null;
        recurrence = null;
        settings = null;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public void setRecurrence(WebinarRecurrence recurrence) {
        this.recurrence = recurrence;
    }

    public void setSettings(WebinarSettings settings) {
        this.settings = settings;
    }
}
