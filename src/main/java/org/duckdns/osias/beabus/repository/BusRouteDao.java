package org.duckdns.osias.beabus.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

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
}
