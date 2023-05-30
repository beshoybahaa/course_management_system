public class login_getter {
private String email;
private String passsword;
private int id;

    public login_getter(String email, String passsword) {
        this.email = email;
        this.passsword = passsword;
    }

    public login_getter(String email, String passsword, int id) {
        this.email = email;
        this.passsword = passsword;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasssword() {
        return passsword;
    }

    public int getId() {
        return id;
    }
}
