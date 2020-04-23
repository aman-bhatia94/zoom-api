package zoomapi;

import java.util.HashMap;

/*
TODO change the type in these
API_BASE_URIS = "https://api.zoom.us/v2"

COMPONENT_CLASSES = {
    "user": components.user.UserComponentV2,
    "meeting": components.meeting.MeetingComponentV2,
    "report": components.report.ReportComponentV2,
    "webinar": components.webinar.WebinarComponentV2,
    "recording": components.recording.RecordingComponentV2,
}
 */
public class ZoomClient {
    //TODO super(ZoomClient, self).__init__(base_uri=self.BASE_URI, timeout=timeout)
    public static final String API_BASE_URIS = "https://api.zoom.us/v2";
    public static final HashMap<String, String> COMPONENT_CLASSES = new HashMap<String, String>() {
        {
            put("user", "components.user.UserComponentV2");
            put("meeting", "components.meeting.MeetingComponentV2");
            put("report", "components.report.ReportComponentV2");
            put("webinar", "components.webinar.WebinarComponentV2");
            put("recording", "components.recording.RecordingComponentV2");
        }
    };

    private int timeout;
    private String dataType;
    private String BASE_URI;
    private HashMap<String, String> components;
    private HashMap<String, String> config;

    public ZoomClient(String apiKey, String apiSecret) {
//        super(API_BASE_URIS);
        this.config = new HashMap<>();
        this.BASE_URI = API_BASE_URIS;
        this.timeout = 15;
        this.dataType = "json";
        this.components = (HashMap<String, String>) COMPONENT_CLASSES.clone();
        this.config.put("api_key", apiKey);
        this.config.put("api_secret", apiSecret);
        this.config.put("data_type", this.dataType);
    }


    public void setRefreshToken() {

        //TODO
    }

    //get api key
    public String getApiKey() {
        return this.config.get("api_key");
    }

    //set api key
    public void setAPiKey(String value) {
        this.config.put("api_key", value);
        setRefreshToken();
    }

    //get api secret
    public String getApiSecret() {
        return this.config.get("api_secret");
    }

    //set api secret
    public void setAPiSecret(String value) {
        this.config.put("api_secret", value);
        setRefreshToken();
    }

    //get report
    public String getReport() {
        return this.components.get("report");
    }

    //get user
    public String getUser() {
        return this.components.get("user");
    }

    //get meeting
    public String getWebinar() {
        return this.components.get("webinar");
    }

    //get recording
    public String getRecording() {
        return this.components.get("recording");
    }
}
