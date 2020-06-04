package services.data;

import models.Channels;

public class ChannelsRequestData {

    final String tableName = "channels";
    Channels queryValues;
    Channels newValues;

    public ChannelsRequestData(Channels queryValues, Channels newValues) {
        this.queryValues = queryValues;
        this.newValues = newValues;
    }
}
