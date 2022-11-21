package org.duckdns.osias.beabus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class BusRoute {
    @Id
    private Long id;
    private String stationName;

}
