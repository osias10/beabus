package org.duckdns.osias.beabus.controller;

import org.duckdns.osias.beabus.entity.User;
import org.duckdns.osias.beabus.model.Result;
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
    public Object signIn(User user, Model model) {
        System.out.println("user: " + user.getUserId() + user.getUserPassword());
        User userVO = userService.signinUser(user.getUserId(), user.getUserPassword());
        Result result = new Result();
        if (userVO == null) {
            model.addAttribute("loginMessage", "아이디 혹은 비밀번호가 일치하지 않습니다.");
            result.message = "Unauthorized";
            result.code = 401;
            return result;
        } else {
            model.addAttribute("userName: ", userVO.getUserName());
            result.message = "success";
            result.code = 200;
            return result;
        }
    }
}
