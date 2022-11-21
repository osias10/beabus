package org.duckdns.osias.beabus.controller;

import org.duckdns.osias.beabus.entity.Bus;
import org.duckdns.osias.beabus.entity.User;
import org.duckdns.osias.beabus.model.ResultJson;
import org.duckdns.osias.beabus.service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1")
public class BeabusApiController {
    BeabusApiBusController beabusApiBusController;
    public List<Bus> busList = beabusApiBusController.busList;
    @Autowired
    UserService userService;
    ResultJson resultJson;

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ResponseBody
    public Object signIn(User user) {
        System.out.println("user: " + user.getUserId() + user.getUserPassword());
        User userVO = userService.signinUser(user.getUserId(), user.getUserPassword());

        if (userVO == null) {
            return resultJson.getResult("Unauthorized", 401);
        } else {
            return resultJson.getResult("success", 200);
        }
    }
    @ResponseBody
    @PostMapping("/signup")
    public Object signUp(User user) {
        boolean result = userService.joinUser(user);
        System.out.println(result);
        if (result == true ){
            return resultJson.getResult("success", 200);
        } else {
            return resultJson.getResult("fail", 409);
        }
    }
    @ResponseBody
    @PostMapping("/idCheck")
    public Object idCheck(User user) throws Exception{
        int idResult = userService.idCheck(user.getUserId());
        if (idResult == 0) {
            return resultJson.getResult("success", 200);
        } else {
            return resultJson.getResult("duplicate", 400);
        }
    }

    @RequestMapping(value="checkbuscode",method = RequestMethod.POST)
    @ResponseBody
    public Object getBusState(@RequestParam Map<String, String> requestData) {
        String busCode = (String) requestData.get("busCode");

        Bus busCheck = busList.stream().filter(b -> b.getBusCode().equals(busCode)).findAny().orElse(null);
        if (busCheck == null) {
            return resultJson.getResult("no Bus", 400);
        } else {
            return resultJson.getResult("success", 200);
        }
    }

    @RequestMapping(value="getOn",method = RequestMethod.POST)
    @ResponseBody
    public Object getOn(@RequestParam Map<String, String> requestData) {
        String busCode = (String) requestData.get("busCode");
        Bus busCheck = busList.stream().filter(b -> b.getBusCode().equals(busCode)).findAny().orElse(null);
        if (busCheck == null) {
            return resultJson.getResult("no Bus", 400);
        } else {
            busCheck.insertGetOn(true);
            return resultJson.getResult("success", 200);
        }
    }

    @RequestMapping(value="getOff",method = RequestMethod.POST)
    @ResponseBody
    public Object getOff(@RequestParam Map<String, String> requestData) {
        String busCode = (String) requestData.get("busCode");
        Bus busCheck = busList.stream().filter(b -> b.getBusCode().equals(busCode)).findAny().orElse(null);
        if (busCheck == null) {
            return resultJson.getResult("no Bus", 400);
        } else {
            busCheck.insertGetOff();
            return resultJson.getResult("success", 200);
        }
    }
}
