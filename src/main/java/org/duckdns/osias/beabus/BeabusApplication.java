package org.duckdns.osias.beabus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BeabusApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeabusApplication.class, args);
    }

}
