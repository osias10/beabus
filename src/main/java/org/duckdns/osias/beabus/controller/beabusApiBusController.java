package org.duckdns.osias.beabus.controller;

import org.duckdns.osias.beabus.entity.Bus;
import org.duckdns.osias.beabus.model.ResultJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/v1/bus")
public class beabusApiBusController {
    ResultJson resultJson;

    List<Bus> busList = new ArrayList<>();

/*
    @RequestMapping(value = "/setbuslocation", method = RequestMethod.POST)
    @ResponseBody
    public Object setBusLocation() {

    }
*/
}
