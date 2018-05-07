package com.hueizhe.controller;

import com.hueizhe.domain.User;
import com.hueizhe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignInController {

    @Autowired
    private UserService userService;

    @RequestMapping("to-sign-in")
    public String toSignIn() {
        return "sign-in";
    }

    @RequestMapping("signIn")
    public String signIn(User user) {
        userService.signIn(user);
        return "redirect:login";
    }
}
