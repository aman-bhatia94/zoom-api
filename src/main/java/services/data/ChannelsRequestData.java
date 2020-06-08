package services.data;

import models.Channels;

public class ChannelsRequestData {

    public String tableName = "channels";
    public Channels queryValues;
    public Channels newValues;

    public ChannelsRequestData(Channels queryValues, Channels newValues) {
        this.queryValues = queryValues;
        this.newValues = newValues;
    }
}
