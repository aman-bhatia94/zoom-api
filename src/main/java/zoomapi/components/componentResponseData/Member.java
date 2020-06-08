package zoomapi.components.componentResponseData;

public class Member {

    String id;
    String email;
    String first_name;
    String last_name;
    String role;
    String channelId;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Member)) {
            return false;
        }
        Member member = (Member) obj;
        return member.getId().equals(member.id) &&
                member.getEmail().equals(member.email) &&
                member.getFirst_name().equals(member.first_name) &&
                member.getLast_name().equals(member.last_name) &&
                member.getRole().equals(member.role) &&
                member.getChannelId().equals(member.channelId);
    }
}
