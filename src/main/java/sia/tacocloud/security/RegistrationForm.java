package sia.tacocloud.security;

import lombok.Data;
import sia.tacocloud.entity.User.User;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser() {
        return new User(
                this.username, this.password,
                this.fullname, this.street, this.city, this.state, this.zip, this.phone);
    }

}
