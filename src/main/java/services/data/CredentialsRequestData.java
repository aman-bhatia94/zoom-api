package services.data;

import models.Credentials;

public class CredentialsRequestData {

    final String tableName = "credentials";
    Credentials queryValues;
    Credentials newValues;

    public CredentialsRequestData(Credentials queryValues, Credentials newValues) {
        this.queryValues = queryValues;
        this.newValues = newValues;
    }
}
