package org.duckdns.osias.beabus.repository;

import org.duckdns.osias.beabus.entity.BusDB;
import org.duckdns.osias.beabus.entity.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusRouteRepository extends JpaRepository<BusRoute,Integer> {
//    @Query(value = "SELECT station_name FROM route.`bus_?1` WHERE id =?2", nativeQuery = true)
//    BusRoute findByStationId (String busNum, int id);

    //@Query(value = "SELECT station_name FROM route.`bus_700-2` WHERE id =:id", nativeQuery = true)
    //BusRoute findByStationId (@Param("busNum") String busNum, @Param("id")int id);
}
