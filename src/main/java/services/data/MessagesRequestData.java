package services.data;

import models.Messages;

public class MessagesRequestData {

    Messages queryValues;
    Messages newValues;
    final String tableName = "messages";

    public MessagesRequestData(Messages queryValues, Messages newValues) {
        this.queryValues = queryValues;
        this.newValues = newValues;
    }
}
