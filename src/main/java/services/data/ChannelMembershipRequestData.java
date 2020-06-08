package services.data;

import models.ChannelMembership;

public class ChannelMembershipRequestData {

    public String tableName = "channelmembership";
    public ChannelMembership queryValues;
    public ChannelMembership newValues;

    public ChannelMembershipRequestData(ChannelMembership queryValues, ChannelMembership newValues) {
        this.queryValues = queryValues;
        this.newValues = newValues;
    }
}
