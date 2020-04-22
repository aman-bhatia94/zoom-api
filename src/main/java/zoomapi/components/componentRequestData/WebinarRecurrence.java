package zoomapi.components.componentRequestData;

public class WebinarRecurrence {
    Integer type;
    Integer repeat_interval;
    String end_date_time;

    public WebinarRecurrence() {
        type = 1;
        repeat_interval = 1;
        end_date_time = null;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setRepeat_interval(Integer repeat_interval) {
        this.repeat_interval = repeat_interval;
    }

    public void setEnd_date_time(String end_date_time) {
        this.end_date_time = end_date_time;
    }
}
