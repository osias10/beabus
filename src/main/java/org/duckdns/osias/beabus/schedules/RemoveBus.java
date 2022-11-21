package org.duckdns.osias.beabus.schedules;

import org.duckdns.osias.beabus.controller.BeabusApiBusController;
import org.duckdns.osias.beabus.entity.Bus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RemoveBus {
    BeabusApiBusController beabusApiBusController;
    long RemoveTime =  300000;
    public List<Bus> busList = beabusApiBusController.busList;

    @Scheduled(fixedDelayString = "100")
    public void busCheck() throws Exception{
        long now = Calendar.getInstance().getTime().getTime();
        busList.stream().filter(b -> now - b.getUpdatedTimeLong() > RemoveTime)
                .collect(Collectors.toList())
                .forEach(li -> {busList.remove(li);});
    }

}
