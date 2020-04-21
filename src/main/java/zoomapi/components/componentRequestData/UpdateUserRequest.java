package zoomapi.components.componentRequestData;

public class UpdateUserRequest {
    String first_name;
    String last_name;
    Integer type;
    Integer pmi;
    String timezone;
    String dept;
    String vanity_name;
    String host_key;
    String cms_user_id;
    String job_title;
    String company;
    String location;
    String phone_number;
    String phone_country;

    public UpdateUserRequest() {
        first_name = null;
        last_name = null;
        type = null;
        pmi = null;
        timezone = null;
        dept = null;
        vanity_name = null;
        host_key = null;
        cms_user_id = null;
        job_title = null;
        company = null;
        location = null;
        phone_number = null;
        phone_country = null;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setPmi(Integer pmi) {
        this.pmi = pmi;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setVanity_name(String vanity_name) {
        this.vanity_name = vanity_name;
    }

    public void setHost_key(String host_key) {
        this.host_key = host_key;
    }

    public void setCms_user_id(String cms_user_id) {
        this.cms_user_id = cms_user_id;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setPhone_country(String phone_country) {
        this.phone_country = phone_country;
    }
}
