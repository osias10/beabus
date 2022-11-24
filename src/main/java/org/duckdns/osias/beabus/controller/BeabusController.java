package org.duckdns.osias.beabus.controller;

import org.duckdns.osias.beabus.entity.User;
import org.duckdns.osias.beabus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @GetMapping("/signin")
    public String singInForm(){
        return "user/signin";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signIn(User user, Model model) {
        System.out.println("user: "+ user.getUserId() + user.getUserPassword());
        User userVO = userService.signinUser(user.getUserId(), user.getUserPassword());
        if (userVO == null){
            model.addAttribute("loginMessage", "아이디 혹은 비밀번호가 일치하지 않습니다.");
            return "user/signin";
        } else {
            model.addAttribute("userName: ",userVO.getUserName());
            return "user/success";
        }
        //return "user/success";
    }

    @ResponseBody
    @GetMapping("idCheck")
    public int idCheck(User user) throws Exception{
        int result = userService.idCheck(user.getUserId());
        System.out.println("result: " + result);
        return result;
    }

    @GetMapping("/getbusstate")
    public String getBusState() {
        return "bus/getbusstate";
    }
}
