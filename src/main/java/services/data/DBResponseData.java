package services.data;

public class DBResponseData {
    int error;
    String errorMessage;
    String responseData;


    public DBResponseData(int error, String errorMessage, String responseData) {
        this.error = error;
        this.errorMessage = errorMessage;
        this.responseData = responseData;
    }

    public int getError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getResponseData() {
        return responseData;
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
