package services.data;

import models.Channels;

public class ChannelsRequestData {

    Channels queryValues;
    Channels newValues;

    final String tableName = "channels";

    public ChannelsRequestData(Channels queryValues, Channels newValues) {
        this.queryValues = queryValues;
        this.newValues = newValues;
    }
}
