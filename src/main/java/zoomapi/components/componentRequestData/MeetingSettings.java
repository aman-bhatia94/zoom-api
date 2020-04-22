package zoomapi.components.componentRequestData;

public class MeetingSettings {
    Boolean host_video;
    Boolean participant_video;
    Boolean cn_meeting;
    Boolean in_meeting;
    Boolean join_before_host;
    Boolean mute_upon_entry;
    Boolean watermark;
    Boolean use_pmi;
    Integer approval_type;
    Integer registration_type;
    String audio;
    String auto_recording;
    Boolean enforce_login;
    String enforce_login_domains;
    String alternative_hosts;
    String[] global_dial_in_countries;
    Boolean registrants_email_notification;

    public MeetingSettings() {
        host_video = null;
        participant_video = null;
        cn_meeting = null;
        in_meeting = null;
        join_before_host = null;
        mute_upon_entry = null;
        watermark = null;
        use_pmi = null;
        approval_type = null;
        registration_type = null;
        audio = null;
        auto_recording = null;
        enforce_login = null;
        enforce_login_domains = null;
        alternative_hosts = null;
        global_dial_in_countries = null;
        registrants_email_notification = null;
    }

    public void setHost_video(Boolean host_video) {
        this.host_video = host_video;
    }

    public void setParticipant_video(Boolean participant_video) {
        this.participant_video = participant_video;
    }

    public void setCn_meeting(Boolean cn_meeting) {
        this.cn_meeting = cn_meeting;
    }

    public void setIn_meeting(Boolean in_meeting) {
        this.in_meeting = in_meeting;
    }

    public void setJoin_before_host(Boolean join_before_host) {
        this.join_before_host = join_before_host;
    }

    public void setMute_upon_entry(Boolean mute_upon_entry) {
        this.mute_upon_entry = mute_upon_entry;
    }

    public void setWatermark(Boolean watermark) {
        this.watermark = watermark;
    }

    public void setUse_pmi(Boolean use_pmi) {
        this.use_pmi = use_pmi;
    }

    public void setApproval_type(Integer approval_type) {
        this.approval_type = approval_type;
    }

    public void setRegistration_type(Integer registration_type) {
        this.registration_type = registration_type;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public void setAuto_recording(String auto_recording) {
        this.auto_recording = auto_recording;
    }

    public void setEnforce_login(Boolean enforce_login) {
        this.enforce_login = enforce_login;
    }

    public void setEnforce_login_domains(String enforce_login_domains) {
        this.enforce_login_domains = enforce_login_domains;
    }

    public void setAlternative_hosts(String alternative_hosts) {
        this.alternative_hosts = alternative_hosts;
    }

    public void setGlobal_dial_in_countries(String[] global_dial_in_countries) {
        this.global_dial_in_countries = global_dial_in_countries;
    }

    public void setRegistrants_email_notification(Boolean registrants_email_notification) {
        this.registrants_email_notification = registrants_email_notification;
    }
}
