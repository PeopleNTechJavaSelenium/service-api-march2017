package resources.databases;

/**
 * Created by mrahman on 5/5/17.
 */
public class AdminProfile {

    public String email;
    public String password;

    public AdminProfile(){}

    public AdminProfile(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
