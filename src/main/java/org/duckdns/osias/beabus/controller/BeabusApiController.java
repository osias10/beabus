package org.duckdns.osias.beabus.controller;

import org.duckdns.osias.beabus.entity.User;
import org.duckdns.osias.beabus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1")
public class BeabusApiController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ResponseBody
    public String signIn(User user, Model model) {
        System.out.println("user: " + user.getUserId() + user.getUserPassword());
        User userVO = userService.signinUser(user.getUserId(), user.getUserPassword());
        if (userVO == null) {
            model.addAttribute("loginMessage", "아이디 혹은 비밀번호가 일치하지 않습니다.");
            return "fail";
        } else {
            model.addAttribute("userName: ", userVO.getUserName());
            return "success";
        }
    }
}
