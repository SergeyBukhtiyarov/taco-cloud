package sia.tacocloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.security.RegistrationForm;
import sia.tacocloud.service.WebUserService;


@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
WebUserService webUserService;

//    @Autowired
//    PasswordEncoder passwordEncoder;
    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        System.out.println(form);
        System.out.println(form.toUser());
       webUserService.saveUser(form.toUser());
        return "redirect:/login";
    }

}