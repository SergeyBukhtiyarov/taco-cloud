package sia.tacocloud.security;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacocloud.entity.User;

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

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username, passwordEncoder.encode(password),
                fullname, street, city, state, zip, phone);
    }
@Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        return  http
                .authorizeRequests()
                .antMatchers("/design", "/orders").access("hasRole('serg')")
                .antMatchers("/","/**").access("permitAll()")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authentificate")
                .usernameParameter("user")
                .passwordParameter("password")
                .and()
                .build();
}
}
