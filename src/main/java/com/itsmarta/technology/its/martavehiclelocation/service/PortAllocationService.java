package com.itsmarta.technology.its.martavehiclelocation.service;


public interface PortAllocationService {
	
    Integer retrieveNextAvailablePort();
    void setPortToAvailable(Integer key);

}
