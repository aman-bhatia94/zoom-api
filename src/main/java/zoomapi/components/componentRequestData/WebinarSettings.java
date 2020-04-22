package zoomapi.components.componentRequestData;

public class WebinarSettings {
    Boolean host_video;
    Boolean panelists_video;
    Boolean practice_session;
    Boolean hd_video;
    Integer approval_type;
    Integer registration_type;
    String audio;
    String auto_recording;
    Boolean enforce_login;
    Boolean close_registration;
    Boolean show_share_button;
    Boolean allow_multiple_devices;
    Boolean registrants_email_notification;

    public WebinarSettings() {
        host_video = null;
        panelists_video = null;
        practice_session = null;
        hd_video = null;
        approval_type = null;
        registration_type = null;
        audio = null;
        auto_recording = null;
        enforce_login = null;
        close_registration = null;
        show_share_button = null;
        allow_multiple_devices = null;
        registrants_email_notification = null;
    }

    public void setHost_video(Boolean host_video) {
        this.host_video = host_video;
    }

    public void setPanelists_video(Boolean panelists_video) {
        this.panelists_video = panelists_video;
    }

    public void setPractice_session(Boolean practice_session) {
        this.practice_session = practice_session;
    }

    public void setHd_video(Boolean hd_video) {
        this.hd_video = hd_video;
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

    public void setClose_registration(Boolean close_registration) {
        this.close_registration = close_registration;
    }

    public void setShow_share_button(Boolean show_share_button) {
        this.show_share_button = show_share_button;
    }

    public void setAllow_multiple_devices(Boolean allow_multiple_devices) {
        this.allow_multiple_devices = allow_multiple_devices;
    }

    public void setRegistrants_email_notification(Boolean registrants_email_notification) {
        this.registrants_email_notification = registrants_email_notification;
    }
}
