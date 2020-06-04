package models;

public class ChannelMembership {

    Integer id;
    String channel_id;
    String member_id;
    String first_name;
    String last_name;
    String email;
    String role;

    public ChannelMembership(Integer id, String channel_id, String member_id, String first_name, String last_name, String email, String role) {
        this.id = id;
        this.channel_id = channel_id;
        this.member_id = member_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.role = role;
    }

    public ChannelMembership() {
    }
}
