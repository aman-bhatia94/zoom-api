package zoomapi.components.componentRequestData;

public class MeetingRecurrence {
    Integer type;
    Integer repeat_interval;
    String weekly_days;
    Integer monthly_day;
    Integer monthly_week;
    Integer monthly_week_day;
    Integer end_times;
    String end_date_time;

    public MeetingRecurrence() {
        type = null;
        repeat_interval = null;
        weekly_days = null;
        monthly_day = null;
        monthly_week = null;
        monthly_week_day = null;
        end_times = null;
        end_date_time = null;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setRepeat_interval(Integer repeat_interval) {
        this.repeat_interval = repeat_interval;
    }

    public void setWeekly_days(String weekly_days) {
        this.weekly_days = weekly_days;
    }

    public void setMonthly_day(Integer monthly_day) {
        this.monthly_day = monthly_day;
    }

    public void setMonthly_week(Integer monthly_week) {
        this.monthly_week = monthly_week;
    }

    public void setMonthly_week_day(Integer monthly_week_day) {
        this.monthly_week_day = monthly_week_day;
    }

    public void setEnd_times(Integer end_times) {
        this.end_times = end_times;
    }

    public void setEnd_date_time(String end_date_time) {
        this.end_date_time = end_date_time;
    }
}
