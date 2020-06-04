package services.data;

import models.ChannelMembership;

public class ChannelMembershipRequestData {

    ChannelMembership queryValues;
    ChannelMembership newValues;
    final String tableName = "channelmembership";

    public ChannelMembershipRequestData(ChannelMembership queryValues, ChannelMembership newValues) {
        this.queryValues = queryValues;
        this.newValues = newValues;
    }
}
