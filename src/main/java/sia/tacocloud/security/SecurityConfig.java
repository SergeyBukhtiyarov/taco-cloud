package sia.tacocloud.security;


import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sia.tacocloud.User;
import sia.tacocloud.data.UserRepository;

public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService (UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user!=null) return user;
            throw new UsernameNotFoundException( "User '" + username + "' not found");
        };
    }
}
