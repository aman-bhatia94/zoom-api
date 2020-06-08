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

    public Integer getId() {
        return id;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
