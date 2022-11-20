package org.duckdns.osias.beabus.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Bus {
    String busNum;
    String busCode;
    int busId;
    int stationId;
    boolean liftFlag;
    int getOnPeople;
    int getOffPeople;
    Calendar updatedTime;

    public Bus(String busNum, String busCode, int stationId) {
        this.busNum = busNum;
        this.busCode = busCode;
        this.stationId = stationId;
        this.updatedTime = Calendar.getInstance();
    }

    public void getOn(boolean liftFlag) {
        this.getOnPeople += 1;
        if (liftFlag) {
            this.liftFlag = true;
        }
    }

    public void getOff() {
        this.getOffPeople += 1;
    }

    public void insertLocation(int stationId) {
        if (this.stationId != stationId) {
            getOnPeople = 0;
            getOffPeople = 0;
            liftFlag = false;
        }
        this.stationId = stationId;
        this.updatedTime = Calendar.getInstance();
    }

    public String getBusCode() {
        return busCode;
    }
    public String getBusNum() {
        return busNum;
    }
    public int getBusStation() {return stationId; }
}
