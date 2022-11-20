package org.duckdns.osias.beabus.controller;

import org.duckdns.osias.beabus.entity.Bus;
import org.duckdns.osias.beabus.entity.BusDB;
import org.duckdns.osias.beabus.model.ResultJson;
import org.duckdns.osias.beabus.repository.BusDBRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            Bus busCheck = busList.stream().filter(b -> b.getBusCode().equals(busCode)).findAny().orElse(null);
            //System.out.println(busCheck);
            if (busCheck == null){
                busList.add(new Bus(busNum.getBusNum(), busCode, Integer.parseInt(busLocation) ));
                return resultJson.getResult("success", 200);
            } else {
                busCheck.insertLocation(Integer.parseInt(busLocation));
                return resultJson.getResult("update", 200);
            }
        }
    }

    @RequestMapping(value = "/getbuslocation", method = RequestMethod.POST)
    @ResponseBody
    public Object getBusLocation(@RequestParam Map<String, String> requestData) {
        String busNum = (String) requestData.get("busNum");
        System.out.println(busNum);
        List<Bus> busCheck = busList.stream().filter(b -> b.getBusNum().equals(busNum))
                .sorted(Comparator.comparing(Bus::getBusStation))
                .collect(Collectors.toList());

        if (busCheck.isEmpty()){
            return resultJson.getResult("no Bus", 200);
        } else {
            JSONObject result = new JSONObject();
            result.put("busName", busNum);
            JSONArray jArray = new JSONArray();
            for (int i =0; i<busCheck.size(); i++) {
                JSONObject obj = new JSONObject();
                obj.put("busCode",busCheck.get(i).getBusCode());
                obj.put("busStation",busCheck.get(i).getBusStation());
                jArray.add(obj);
            }
            result.put("busList",jArray);
            return resultJson.getResult("success", 200,result);
        }
    }
}
