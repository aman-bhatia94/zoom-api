package services.data;

import models.Credentials;

public class CredentialsRequestData {

    public String tableName = "credentials";
    public Credentials queryValues;
    public Credentials newValues;

    public CredentialsRequestData(Credentials queryValues, Credentials newValues) {
        this.queryValues = queryValues;
        this.newValues = newValues;
    }
}
