package org.duckdns.osias.beabus.entity;

import org.duckdns.osias.beabus.repository.BusRouteDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Bus {
    String busNum;
    String busCode;
    int busId;
    int stationId;
    boolean liftFlag;
    int getOnPeople;
    int getOffPeople;
    Date updatedTime;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    @Autowired
    BusRouteDao busRouteDao;
    public Bus(String busNum, String busCode, int stationId) {
        this.busNum = busNum;
        this.busCode = busCode;
        this.stationId = stationId;
        this.updatedTime = Calendar.getInstance().getTime();
    }

    public void insertGetOn(boolean liftFlag) {
        this.getOnPeople += 1;
        if (liftFlag) {
            this.liftFlag = true;
        }
    }

    public void insertGetOff() {
        this.getOffPeople += 1;
    }

    public void insertLocation(int stationId) {
        if (this.stationId != stationId) {
            getOnPeople = 0;
            getOffPeople = 0;
            liftFlag = false;
        }
        this.stationId = stationId;
        this.updatedTime = Calendar.getInstance().getTime();
    }

    public String getBusCode() {
        return busCode;
    }
    public String getBusNum() {
        return busNum;
    }
    public int getBusStation() {return stationId; }
    public String getUpdatedTime() { return sdf.format(updatedTime); }
    public long getUpdatedTimeLong() { return updatedTime.getTime(); }
    public int getGetOnPeople() { return getOnPeople; }
    public int getGetOffPeople() { return getOffPeople; }
    public boolean getGetLift() { return liftFlag; }
    public String getBusStationName() { return busRouteDao.getStationName(busNum, stationId).get(0); }

}
