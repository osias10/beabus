package org.duckdns.osias.beabus.controller;

import org.duckdns.osias.beabus.entity.User;
import org.duckdns.osias.beabus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BeabusController {

    @Autowired
    UserService userService;

    @GetMapping("/signup")
    public String signUpForm(){
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signUp(User user) {
        userService.joinUser(user);
        return "user/success";
    }
}
