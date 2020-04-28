package zoomapi.components.componentResponseData;

public class Response {

    Integer page_size;
    String next_page_token;
    Integer total_records;

    public Integer getPage_size() {
        return page_size;
    }

    public String getNext_page_token() {
        return next_page_token;
    }

    public Integer getTotal_records() {
        return total_records;
    }
}
