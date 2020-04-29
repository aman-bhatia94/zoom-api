package utils;

public class StatusCodes {

    //chat channel
    public final static int USER_CHANNELS_RETURNED = 200;
    public final static int CHANNEL_CREATED = 201;
    public final static int CHANNEL_RETURNED = 200;
    public final static int CHANNEL_UPDATED = 204;
    public final static int CHANNEL_DELETED = 204;
    public final static int LIST_OF_CHANNEL_MEMBERS_RETURNED = 200;
    public final static int MEMBERS_INVITED_TO_THE_CHANNEL = 201;
    public final static int SUCCESSFULLY_JOINED_THE_CHANNEL = 201;
    public final static int LEFT_CHANNEL_SUCCESSFULLY = 201;
    public final static int MEMBER_REMOVED = 204;

    //chat messages
    public final static int LIST_OF_CHAT_MESSAGES_RETURNED = 200;
    public final static int MESSAGE_SENT = 201;
    public final static int MESSAGE_UPDATED_SUCCESSFULLY = 204;
    public final static int MESSAGE_DELETED = 204;

    //meeting
    public final static int LIST_OF_MEETING_OBJECTS_RETURNED = 200;
    public final static int MEETING_CREATED = 201;
    public final static int MEETING_OBJECT_RETURNED = 200;
    public final static int MEETING_UPDATED = 204;

    //recording
    public final static int LIST_OF_RECORDING_OBJECTS_RETURNED = 200;
    public final static int RECORDING_OBJECT_RETURNED = 200;
    public final static int MEETING_RECORDING_DELETED = 204;

    //reports
    public final static int ACTIVE_OR_INACTIVE_HOSTS_REPORT_RETURNED = 200;

    //user
    public final static int USER_OBJECT_RETURNED = 200;
    public final static int USER_UPDATED = 204;
    public final static int USER_LIST_RETURNED = 200;
    public final static int USER_CREATED = 201;
    public final static int USER_DELETED = 204;

    //webinar
    public final static int LIST_OF_WEBINAR_OBJECTS_RETURNED = 200;
    public final static int WEBINAR_CREATED = 201;
    public final static int SUCCESS = 200;
    public final static int WEBINAR_UPDATED = 204;
    public final static int WEBINAR_STATUS_UPDATED = 204;
}
