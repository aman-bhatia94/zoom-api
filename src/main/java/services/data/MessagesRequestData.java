package services.data;

import models.Messages;

public class MessagesRequestData {

    final String tableName = "messages";
    Messages queryValues;
    Messages newValues;

    public MessagesRequestData(Messages queryValues, Messages newValues) {
        this.queryValues = queryValues;
        this.newValues = newValues;
    }
}
