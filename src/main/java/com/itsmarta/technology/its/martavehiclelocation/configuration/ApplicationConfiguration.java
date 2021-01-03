package com.itsmarta.technology.its.martavehiclelocation.configuration;

import com.itsmarta.technology.its.martavehiclelocation.service.impl.PortAllocService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.itsmarta.technology.its.martavehiclelocation.dao.RouterMessage;
import com.itsmarta.technology.its.martavehiclelocation.dao.impl.FTLMessage;
import com.itsmarta.technology.its.martavehiclelocation.dao.impl.NMEAMessage;
import com.itsmarta.technology.its.martavehiclelocation.dao.impl.TAIPMessage;
import com.itsmarta.technology.its.martavehiclelocation.service.PortAllocationService;
import com.itsmarta.technology.its.martavehiclelocation.service.PublishLocationService;
import com.itsmarta.technology.its.martavehiclelocation.service.ReceiveRouterMessageService;
import com.itsmarta.technology.its.martavehiclelocation.service.impl.PublishVehicleLocationService;
import com.itsmarta.technology.its.martavehiclelocation.service.impl.RouterMessageService;
import org.springframework.stereotype.Service;

@Configuration
public class ApplicationConfiguration {
    @Bean(name="ftlMsg")
    public FTLMessage getFTLMessage(){
        return new FTLMessage();
    }

    @Bean(name="nmeaMsg")
    public RouterMessage getNMEAMessage() {
        return new NMEAMessage();
    }

    @Bean(name="taipMsg")
    public RouterMessage getTAIPMessage() {
        return new TAIPMessage();
    }
/**
    @Bean(name="routerMessageConfig")
    public RouterMessageConfiguration getRouterMessageConfiguration() {
        return new RouterMessageConfiguration();
    }

    @Bean(name="realmServerConfig")
    public RealmServerConfiguration getRealmServerConfiguration() {
        return new RealmServerConfiguration();
    }

    @Bean(name="asyncConfig")
    public AsynchConfiguration getAsynchConfiguration() {
        return new AsynchConfiguration();
    }
**/
    @Bean(name="portAllocationSVC")
    public PortAllocationService createPortAllocService() {
        return new PortAllocService();
    }

    @Bean(name="routerMessageSVC")
    public ReceiveRouterMessageService createReceiveMessageService() {
        return new RouterMessageService();
    }

    @Bean(name="publishLocationSVC")
    public PublishLocationService createPublishVehicleLocationMessageService() {
        return new PublishVehicleLocationService();
    }

}
