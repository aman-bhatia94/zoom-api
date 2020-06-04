package services.data;

public class DBResponseData {
    int error;
    String errorMessage;
    String responseData;

    public DBResponseData() {
    }

    public DBResponseData(int error, String errorMessage, String responseData) {
        this.error = error;
        this.errorMessage = errorMessage;
        this.responseData = responseData;
    }

    @Override
    public String toString() {
        return "DBResponseData{" +
                "error=" + error +
                ", errorMessage='" + errorMessage + '\'' +
                ", responseData=" + responseData +
                '}';
    }
}
