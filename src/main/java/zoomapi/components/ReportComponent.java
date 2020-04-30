package zoomapi.components;

import utils.ApiClient;
import utils.Throttled;
import utils.Utils;
import zoomapi.components.componentResponseData.ChatMessagesResponseData.SendChatMessageResponse;
import zoomapi.components.componentResponseData.ReportComponentResponseData.GetMeetingReportResponse;
import zoomapi.components.componentResponseData.WebinarResponseData.GetActiveInactiveHostReportResponse;

import java.util.Map;

public class ReportComponent extends BaseComponent {

    Throttled getMeetingReportsThrottler = null;
    Throttled getInactiveHostReportsThrottler = null;

    public ReportComponent(String baseUri, String token) {
        super(baseUri, token);
    }

    public GetMeetingReportResponse getMeetingReports(Map<String, String> params) {
        Map responseMap = null;
        GetMeetingReportResponse responseData = new GetMeetingReportResponse();
        if (getMeetingReportsThrottler == null) {
            getMeetingReportsThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/report/users/%s/meetings";
            url = String.format(url, params.get("userId"));
            if (!params.containsKey("page_size"))
                params.put("page_size", "10");
            if (!params.containsKey("type"))
                params.put("type", "past");
            getMeetingReportsThrottler.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = gson.fromJson(response, GetMeetingReportResponse.class);
//                System.out.println("Response: " + response);
            }
            //System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

    public GetActiveInactiveHostReportResponse getActiveInactiveHostReports(Map<String, String> params) {
        Map responseMap = null;
        GetActiveInactiveHostReportResponse responseData = new GetActiveInactiveHostReportResponse();
        if (getInactiveHostReportsThrottler == null) {
            getInactiveHostReportsThrottler = new Throttled();
        }
        try {
            String url = ApiClient.getApiClient().getBaseUri() + "/report/users";
            if (!params.containsKey("page_size"))
                params.put("page_size", "10");
            if (!params.containsKey("page_number"))
                params.put("page_number", "1");
            url = Utils.appendToUrl(url, params);
            getInactiveHostReportsThrottler.throttle();
            String response = ApiClient.getApiClient().getRequest(url, params, null);
            responseMap = gson.fromJson(response, Map.class);
            if (responseMap.containsKey("code")) {
                throw new Exception(Utils.getErrorMessageFromResponse(responseMap));
            } else {
                responseData = gson.fromJson(response, GetActiveInactiveHostReportResponse.class);
//                System.out.println("Response: " + response);
            }
            System.out.println("Response: " + response);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return responseData;
    }

}
