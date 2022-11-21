package org.duckdns.osias.beabus.controller;

import org.duckdns.osias.beabus.entity.Bus;
import org.duckdns.osias.beabus.entity.BusDB;
import org.duckdns.osias.beabus.entity.BusRoute;
import org.duckdns.osias.beabus.model.ResultJson;
import org.duckdns.osias.beabus.repository.BusDBRepository;
import org.duckdns.osias.beabus.repository.BusRouteDao;
import org.duckdns.osias.beabus.repository.BusRouteRepository;
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

@Controller
@RequestMapping("/api/v1/bus")
public class BeabusApiBusController {
    ResultJson resultJson;
    @Autowired
    BusDBRepository busDBRepository;
    BusRouteRepository busRouteRepository;
    static public List<Bus> busList = new ArrayList<>();
    @Autowired
    BusRouteDao busRouteDao;

    @RequestMapping(value = "/setbuslocation", method = RequestMethod.POST)
    @ResponseBody
    public Object setBusLocation(@RequestParam Map<String, Object> requestData ) {
        String busCode = (String) requestData.get("busCode");
        String busLocation = (String) requestData.get("busLocation");
        BusDB busNum = busDBRepository.findByBusCode(busCode);
        //System.out.println(busNum.getBusNum());
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
            return resultJson.getResult("no Bus", 400);
        } else {
            JSONObject result = new JSONObject();
            result.put("busName", busNum);
            JSONArray jArray = new JSONArray();
            for (int i =0; i<busCheck.size(); i++) {
                List<String> stationName = busRouteDao.getStationName(busCheck.get(i).getBusNum(), busCheck.get(i).getBusStation());
                JSONObject obj = new JSONObject();
                obj.put("busCode",busCheck.get(i).getBusCode());
                obj.put("busStationId",busCheck.get(i).getBusStation());
                obj.put("busStationName",stationName.get(0));
                jArray.add(obj);
            }
            result.put("busList",jArray);
            return resultJson.getResult("success", 200,result);
        }
    }

    @RequestMapping(value = "/getbusalllocation", method = RequestMethod.POST)
    @ResponseBody
    public Object getBusAllLocation(@RequestParam Map<String, String> requestData) {

        JSONObject result = new JSONObject();
        result.put("busName", "AllBus");
        JSONArray jArray = new JSONArray();
        for (int i =0; i<busList.size(); i++) {
            List<String> stationName = busRouteDao.getStationName(busList.get(i).getBusNum(), busList.get(i).getBusStation());
            JSONObject obj = new JSONObject();
            obj.put("busCode",busList.get(i).getBusCode());
            obj.put("busStationId",busList.get(i).getBusStation());
            obj.put("busStationName",stationName.get(0));
            obj.put("busNum", busList.get(i).getBusNum());
            obj.put("updated", busList.get(i).getUpdatedTime());
            jArray.add(obj);
        }
        result.put("busList",jArray);
        return resultJson.getResult("success", 200,result);
    }

    @RequestMapping(value="getbusstate",method = RequestMethod.GET)
    @ResponseBody
    public Object getBusState(String busCode) {
        Bus busCheck = busList.stream().filter(b -> b.getBusCode().equals(busCode)).findAny().orElse(null);
        if (busCheck == null) {
            return resultJson.getResult("no Bus", 400);
        } else {
            JSONObject result = new JSONObject();
            result.put("busNum",busCheck.getBusNum());
            result.put("busCode", busCheck.getBusCode());
            result.put("getOn", busCheck.getGetOnPeople());
            result.put("getOff", busCheck.getGetOffPeople());
            result.put("lift", busCheck.getGetLift());

            return resultJson.getResult("success", 200,result);
        }
    }


}
