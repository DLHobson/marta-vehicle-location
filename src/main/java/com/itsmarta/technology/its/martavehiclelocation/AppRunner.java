package com.itsmarta.technology.its.martavehiclelocation;

import com.itsmarta.technology.its.martavehiclelocation.configuration.AsynchConfiguration;
import com.itsmarta.technology.its.martavehiclelocation.configuration.RealmServerConfiguration;
import com.itsmarta.technology.its.martavehiclelocation.configuration.RouterMessageConfiguration;
import com.itsmarta.technology.its.martavehiclelocation.dao.RouterMessage;
import com.itsmarta.technology.its.martavehiclelocation.service.PortAllocationService;
import com.itsmarta.technology.its.martavehiclelocation.service.PublishLocationService;
import com.itsmarta.technology.its.martavehiclelocation.service.ReceiveRouterMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
public class AppRunner implements CommandLineRunner {
	
    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);
    
    @Autowired
	private final ReceiveRouterMessageService routerMessageSVC;
    
    @Autowired
	private final PublishLocationService publishLocationSVC;

    @Autowired
    private final PortAllocationService portAllocationSVC;

    @Autowired
	RealmServerConfiguration realmServerConfig;

    @Autowired
	RouterMessageConfiguration routerMessageConfig;
    
	@Autowired
	private AsynchConfiguration asynchConfig;

	public AppRunner(ReceiveRouterMessageService routerMsgSvc,
					 PublishLocationService locationSvc,
					 PortAllocationService portAllocSvc) {
		
		    this.routerMessageSVC = routerMsgSvc;
		    this.publishLocationSVC = locationSvc;
		    this.portAllocationSVC = portAllocSvc;
	}

	@Override
	public void run(String... args) throws Exception {
		while(true) {
	    	try {     
	    		logger.info("Executing ReceiveAndPublishMessage Asynchronously");
	    		@SuppressWarnings("unused")
	    		CompletableFuture<Void> cf = CompletableFuture.runAsync(()->{
    			ReceiveAndPublishMessage(portAllocationSVC.retrieveNextAvailablePort().intValue());
    			});
	    		TimeUnit.MILLISECONDS.sleep(asynchConfig.getPool().getThreadWait());
	    	}
	    	catch (Exception ex) {
	    		ex.printStackTrace();
	    	}			
		}
	}

	@Async
	public CompletableFuture<Void> ReceiveAndPublishMessage(int udpPort) {
		try {
			logger.info("Calling: ReceiveRouterMessageService.receiveRouterMessage()");
			CompletableFuture<RouterMessage> completableFuture = routerMessageSVC.receiveRouterMessage(udpPort);
			
			RouterMessage routerMsg = completableFuture.get();
			
			if(routerMsg != null) {
				logger.info(routerMsg.toString());
	    		int rcode = 0;
	    		
	       		logger.info("Calling: PublishLocationService.sendMessage(routerMsg)");
	       		rcode = publishLocationSVC.sendMessage(routerMsg).intValue();
	    		
	    		if(rcode == 0) {
	    			logger.info("ReturnCode (0): Successful completion.");
	    		}
	    		else {
	    			logger.error("ReturnCode (-1): Error encountered during publish");
	    		}
			}
			else {
				logger.error("NMEA Message discarded.");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
