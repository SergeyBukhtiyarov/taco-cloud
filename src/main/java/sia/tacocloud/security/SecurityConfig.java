//package sia.tacocloud.security;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import sia.tacocloud.service.WebUserService;
//
//@Configuration
//public class SecurityConfig {
//    @Autowired
//    WebUserService webUserService;
//    @Autowired
//    BCryptPasswordEncoder passwordEncoder;
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .authorizeRequests()
//                .antMatchers("/design", "/orders").hasRole("USER")
//                .antMatchers("/", "/**").access("permitAll()")
//                .and()
//                .formLogin()
//                .loginPage("/login")
////                .loginProcessingUrl("/authentificate")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .and()
//                .build();
//    }
//
////    @Bean
////    public UserDetailsService userDetailsService (UserRepository userRepo) {
////        return username -> {
////            User user = userRepo.findByUsername(username);
////            if (user!=null) return user;
////            throw new UsernameNotFoundException( "User '" + username + "' not found");
////        };
////    }
//
//
//    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(webUserService)
//                .passwordEncoder(passwordEncoder);
//
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) ->
//                web.ignoring()
//                        .antMatchers("/scripts/**")
//                        .antMatchers("/styles/**")
//                        .antMatchers("/images/**")
//                        .antMatchers("/fonts/**");
//
//
//    }
//}
