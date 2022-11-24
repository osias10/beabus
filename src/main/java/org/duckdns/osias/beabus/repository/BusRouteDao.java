package org.duckdns.osias.beabus.repository;

import org.duckdns.osias.beabus.entity.BusRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

@Component
public class BusRouteDao {
    @Autowired
    EntityManager eml;

    public List<String> getStationName(String busNum, int id) {
        String s = "SELECT station_name FROM route.`bus_%s` WHERE id = %d";
        String sql = String.format(s,busNum, id);
        System.out.println(sql);
        List<String> stationName = eml.createNativeQuery(sql).getResultList();
        return stationName;
    }
    public List<Object[]> getStationList(String busNum) {
        String s = "SELECT * FROM route.`bus_%s`";
        String sql = String.format(s,busNum);
        List<Object[]> stationName = eml.createNativeQuery(sql).getResultList();
        return stationName;
    }
}
