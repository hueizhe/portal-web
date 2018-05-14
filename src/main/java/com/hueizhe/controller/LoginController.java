package com.hueizhe.controller;

import com.hueizhe.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final static Logger logger =LoggerFactory.getLogger(LoginController.class);


    @RequestMapping("login")
    public String login(@RequestParam(value = "error", required = false) String error, User user) {

        String password = new BCryptPasswordEncoder().encode("tony");
        logger.debug("password===" + password);

        return "login";
    }
}
