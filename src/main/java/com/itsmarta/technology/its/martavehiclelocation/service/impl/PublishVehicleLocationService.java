package com.itsmarta.technology.its.martavehiclelocation.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.itsmarta.technology.its.martavehiclelocation.configuration.RealmServerConfiguration;
import com.itsmarta.technology.its.martavehiclelocation.dao.RouterMessage;
import com.itsmarta.technology.its.martavehiclelocation.service.PublishLocationService;
import com.tibco.ftl.FTL;
import com.tibco.ftl.FTLException;
import com.tibco.ftl.Message;
import com.tibco.ftl.Publisher;
import com.tibco.ftl.Realm;
import com.tibco.ftl.TibProperties;

public class PublishVehicleLocationService implements PublishLocationService {
	
    private static final Logger logger = LoggerFactory.getLogger(PublishVehicleLocationService.class);
    
    @Autowired
    private RealmServerConfiguration realmServerConfig;
 
	@Override
	public Integer sendMessage(RouterMessage routerMsg) throws FTLException {
        Publisher 		publisher 	= null;
        Message    		msg         = null;
        Realm           realm       = null;
        TibProperties   props		= null;
        int returnCode = 0;
 
        try {
            logger.info("Creating and initializing a sole realm server process...");
        	logger.info(FTL.getVersionInformation());
           
        	logger.info("Setting properties...");
            props = FTL.createProperties();
            props.set(Realm.PROPERTY_STRING_USERNAME, realmServerConfig.getUserName());
            props.set(Realm.PROPERTY_STRING_USERPASSWORD, realmServerConfig.getPassword());

            logger.info("Connecting to realm server...\n");
            realm = FTL.connectToRealmServer(realmServerConfig.getPrimaryRealmServer(),
            								 realmServerConfig.getMessageApp(),
            								 props);
            logger.info("Done.");

            logger.info("Building FTL message...\n");
            logger.info(routerMsg.toString());
            publisher = realm.createPublisher(realmServerConfig.getMessageEndpoint());
            msg = realm.createMessage(realmServerConfig.getMessageFormat());
            msg = realm.createMessage(null);

            msg.setString("AppName", realmServerConfig.getApp().getAppName());
            msg.setString("MessageType", realmServerConfig.getApp().getMsgType());
            msg.setString("MessageSubType", realmServerConfig.getApp().getMsgSubType());
            msg.setString("Port", Integer.toString(routerMsg.getSourcePort()));
            msg.setString("IPAddress", routerMsg.getSourceIPaddress().toString());
            msg.setString("NMEASentence", routerMsg.getSentence());

            logger.info("Done.");

            logger.info("Sending FTL message...\n");
            publisher.send(msg);
            logger.info("Done.");

        }
        catch(FTLException FTLex) {
        	FTLex.printStackTrace();
        	returnCode = -1;
        }
        catch(Exception ex) {
        	ex.printStackTrace();
           	returnCode = -1;
        }
        finally {
            logger.info("Performing cleanup...\n");
            msg.destroy();
            publisher.close();
            realm.close();
            logger.info("Done.");
        }
		return returnCode;
	}


}
