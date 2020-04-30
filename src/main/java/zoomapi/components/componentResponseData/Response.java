package zoomapi.components.componentResponseData;

public class Response {

    Integer page_size;
    Integer page_number;
    String next_page_token;
    Integer total_records;
    Integer page_count;

    public Integer getPage_size() {
        return page_size;
    }

    public String getNext_page_token() {
        return next_page_token;
    }

    public Integer getTotal_records() {
        return total_records;
    }

    public Integer getPage_count() {
        return page_count;
    }

    public Integer getPage_number() {
        return page_number;
    }
}
