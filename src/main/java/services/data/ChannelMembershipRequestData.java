package services.data;

import models.ChannelMembership;

public class ChannelMembershipRequestData {

    final String tableName = "channelmembership";
    ChannelMembership queryValues;
    ChannelMembership newValues;

    public ChannelMembershipRequestData(ChannelMembership queryValues, ChannelMembership newValues) {
        this.queryValues = queryValues;
        this.newValues = newValues;
    }
}
