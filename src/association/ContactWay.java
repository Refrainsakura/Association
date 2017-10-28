package association;

public class ContactWay {
    private String phone_num;
    private String email;

    public ContactWay(String phone_num, String email) {
        this.phone_num = phone_num;
        this.email = email;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
