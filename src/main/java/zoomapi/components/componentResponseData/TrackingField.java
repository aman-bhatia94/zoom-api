package zoomapi.components.componentResponseData;

public class TrackingField {
    String field;
    String value;

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TrackingField{" +
                "field='" + field + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

