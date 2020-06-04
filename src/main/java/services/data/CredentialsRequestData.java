package services.data;

import models.Credentials;

public class CredentialsRequestData {

    Credentials queryValues;
    Credentials newValues;
    final String tableName = "Credentials";

    public CredentialsRequestData(Credentials queryValues, Credentials newValues) {
        this.queryValues = queryValues;
        this.newValues = newValues;
    }
}
