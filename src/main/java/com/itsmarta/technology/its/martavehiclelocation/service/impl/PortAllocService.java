 package com.itsmarta.technology.its.martavehiclelocation.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.itsmarta.technology.its.martavehiclelocation.configuration.RouterMessageConfiguration;

public class PortAllocService implements com.itsmarta.technology.its.martavehiclelocation.service.PortAllocationService {
	private static final Logger logger = LogManager.getLogger(PortAllocService.class);
	
	@Autowired
	private RouterMessageConfiguration routerMessageConfig;

	private final Map<Integer, Boolean> portMap = new HashMap<Integer, Boolean>();

	/**
	public PortAllocService() {
		for(Integer item:routerMessageConfig.getListener().getPorts()) {
			portMap.put(item, true);
		}
		
		logger.info("List to hashmap" + portMap);
	}
	 **/

	@Override
	public Integer retrieveNextAvailablePort() {
		Integer retValue = null;
		
		// Iterate through the hash map containing the ports
		// The first entry that has a value of true (available port) is returned 
		// and the value is set to false
		for(Entry<Integer, Boolean> entry:portMap.entrySet()) {
			if(entry.getValue()) {
				retValue = entry.getKey();
				
				logger.info("Entry" + entry);
				
				entry.setValue(false);
				break;
			}
				
		}
		
		return retValue;
	}

	@Override
    public void setPortToAvailable(Integer key) {
    	
    	this.portMap.replace(key, true);
    	
    }

}
