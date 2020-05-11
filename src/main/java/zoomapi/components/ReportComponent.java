package zoomapi.components;

import utils.ApiClient;
import utils.Utils;
import zoomapi.components.componentResponseData.ReportComponentResponseData.GetMeetingReportResponse;
import zoomapi.components.componentResponseData.WebinarResponseData.GetActiveInactiveHostReportResponse;

import java.util.Map;

public class ReportComponent extends BaseComponent {

    public ReportComponent(String baseUri, String token) {
        super(baseUri, token);
    }

    public GetMeetingReportResponse getMeetingReports(Map<String, String> params) {
        GetMeetingReportResponse responseData = new GetMeetingReportResponse();
        try {
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/report/users/%s/meetings", params,
                    new String[]{"userId"}, false);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, GetMeetingReportResponse.class);
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public GetActiveInactiveHostReportResponse getActiveInactiveHostReports(Map<String, String> params) {
        GetActiveInactiveHostReportResponse responseData = new GetActiveInactiveHostReportResponse();
        try {
            String url = getUrl(ApiClient.getApiClient().getBaseUri(),
                    "/report/users", params, null, true);
            THROTTLED.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            Map responseMap = GSON.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = GSON.fromJson(response, GetActiveInactiveHostReportResponse.class);
            }
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

}
