package services.data;

import models.Messages;

public class MessagesRequestData {

    public String tableName = "messages";
    public Messages queryValues;
    public Messages newValues;

    public MessagesRequestData(Messages queryValues, Messages newValues) {
        this.queryValues = queryValues;
        this.newValues = newValues;
    }
}
