package org.duckdns.osias.beabus.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    public String message;
    public int code;
    public String data;
}
