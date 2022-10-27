package org.duckdns.osias.beabus.entity;

public class Bus {
    String busNum;
    int busId;
    int stationId;
    boolean liftFlag;
    int getOnPeople;
    int getOffPeople;

    public void Bus(String busNum, int busId) {
        this.busNum = busNum;
        this.busId = busId;
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
    }
}
