package zoomapi.components.componentResponseData.WebinarResponseData;

import zoomapi.components.componentResponseData.Webinar;

import java.util.ArrayList;

public class ListWebinarsResponseData {

    Integer page_count;
    Integer page_number;
    Integer page_size;
    Integer total_records;

    ArrayList<Webinar> webinars;

    public Integer getPage_count() {
        return page_count;
    }

    public Integer getPage_number() {
        return page_number;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public Integer getTotal_records() {
        return total_records;
    }

    @Override
    public String toString() {
        return "ListWebinarsResponseData{" +
                "page_count=" + page_count +
                ", page_number=" + page_number +
                ", page_size=" + page_size +
                ", total_records=" + total_records +
                ", webinars=" + webinars +
                '}';
    }

    public ArrayList<Webinar> getWebinars() {
        return webinars;
    }
}
