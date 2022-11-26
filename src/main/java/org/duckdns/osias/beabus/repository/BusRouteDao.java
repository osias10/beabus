package org.duckdns.osias.beabus.repository;

import org.duckdns.osias.beabus.entity.BusRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
        System.out.println("getStationName:\t" + sql);
        List<String> stationName = eml.createNativeQuery(sql).getResultList();
        return stationName;
    }
    public List<Object[]> getStationList(String busNum) {
        String s = "SELECT id,station_name FROM route.`bus_%s`";
        String sql = String.format(s,busNum);
        List<Object[]> stationName = eml.createNativeQuery(sql).getResultList();
        return stationName;
    }
    public List<Integer> getBookList(String busNum, int id) {
        String s = "SELECT book FROM route.`bus_%s` WHERE id=%d";
        String sql = String.format(s,busNum,id);
        System.out.println("getBookList:\t" + sql);
        List<Integer> books = eml.createNativeQuery(sql).getResultList();
        return books;
    }
    @Transactional
    public void clearBookList(String busNum, int id) {
        String s = "update route.`bus_%s` set book=0 , book_lift=0 where id='%d'";
        String sql = String.format(s,busNum,id);
        eml.createNativeQuery(sql).executeUpdate();;
    }
    @Transactional
    public void addBookList(String busNum, int id, int lift) {
        String s = "UPDATE route.`bus_%s` SET book= book + 1 , book_lift = book_lift + %d WHERE id=%d";
        String sql = String.format(s,busNum,lift,id);
        System.out.println("addBookList:\t" + sql);
        eml.createNativeQuery(sql).executeUpdate();

    }
}
