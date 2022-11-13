package org.duckdns.osias.beabus.repository;

import org.duckdns.osias.beabus.entity.BusDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BusDBRepository extends JpaRepository<BusDB,Long> {
    //@Query(value = "SELECT * FROM beabus.bus WHERE bus_code =?0", nativeQuery = true)

    @Query(value = "SELECT * FROM beabus.bus WHERE bus_code =?1", nativeQuery = true)
    BusDB findByBusCode(String busCode);
}
