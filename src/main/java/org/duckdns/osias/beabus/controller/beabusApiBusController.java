package org.duckdns.osias.beabus.controller;

import org.duckdns.osias.beabus.entity.Bus;
import org.duckdns.osias.beabus.entity.BusDB;
import org.duckdns.osias.beabus.model.ResultJson;
import org.duckdns.osias.beabus.repository.BusDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/bus")
public class beabusApiBusController {
    ResultJson resultJson;
    @Autowired
    BusDBRepository busDBRepository;
    List<Bus> busList = new ArrayList<>();


    @RequestMapping(value = "/setbuslocation", method = RequestMethod.POST)
    @ResponseBody
    public Object setBusLocation(@RequestParam Map<String, Object> requestData ) {
        String busCode = (String) requestData.get("busCode");
        String busLocation = (String) requestData.get("busLocation");
        BusDB busNum = busDBRepository.findByBusCode("test");
        System.out.println(busNum.getBusNum());
        if (busCode == null || busLocation == null || busNum == null) {
            return resultJson.getResult("fail", 409);
        } else {
            Object busCheck = busList.stream().filter(b -> b.getBusCode().equals(busCode));
            System.out.println(busCheck);
            if (busCheck == null){
                busList.add(new Bus(busNum.getBusNum(), busCode, Integer.parseInt(busLocation) ));
                return resultJson.getResult("success", 200);
            } else {
                return resultJson.getResult("adsf", 200);
            }
        }
    }

}
