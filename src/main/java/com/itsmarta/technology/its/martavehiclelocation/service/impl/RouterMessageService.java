package com.itsmarta.technology.its.martavehiclelocation.service.impl;

import com.itsmarta.technology.its.martavehiclelocation.configuration.RouterMessageConfiguration;
import com.itsmarta.technology.its.martavehiclelocation.dao.RouterMessage;
import com.itsmarta.technology.its.martavehiclelocation.service.ReceiveRouterMessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.CompletableFuture;

public class RouterMessageService implements ReceiveRouterMessageService {
	private static final Logger logger = LogManager.getLogger(RouterMessageService.class);
	
	@Autowired
	private RouterMessageConfiguration routerMessageConfig;
	
//    @Value("${realm.udp.port}") private String udpPort;

    @Autowired
    private RouterMessage taipMsg;
    
    @Autowired
    private RouterMessage nmeaMsg;

    @Override
    @Async
	public CompletableFuture<RouterMessage> receiveRouterMessage(int udpPort) throws Exception {
		RouterMessage message = null;
		DatagramSocket serverSocket = null;

		try
	    { 
			
			serverSocket = new DatagramSocket(Integer.valueOf(udpPort)); 
	  
			byte[] receiveData = new byte[routerMessageConfig.getMessage().getSize().intValue()];
			byte[] sendData  = new byte[routerMessageConfig.getMessage().getSize().intValue()];
	  
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 

			logger.info("Listening for datagram packet on port: " + udpPort);
			
			serverSocket.receive(receivePacket); 

			logger.info("Message received.");

			String sentence = new String(receivePacket.getData()).trim(); 
			InetAddress IPAddress = receivePacket.getAddress(); 			  
			int port = receivePacket.getPort(); 
			int msgLen = receivePacket.getLength();
			
			char firstChar = sentence.charAt(0);
			
			// Determine the type of message format received, TAIP or NMEA
			
			if(firstChar == '>') {
				logger.info("TAIP Message received");				
				
				message = taipMsg;
				
				message.setSourceIPaddress(IPAddress);
				message.setSourcePort(Integer.valueOf(port));
				message.setSentence(sentence.trim());
				message.setMessageLength(Integer.valueOf(msgLen));
//				message.ParseMessageString(sentence.trim());			

				logger.info("Router Message: " + message.toString());				
	  
				String capitalizedSentence = message.getSentence().toUpperCase(); 

				sendData = capitalizedSentence.getBytes(); 
	  
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, 
															   message.getSourceIPaddress(),
															   message.getSourcePort()); 
	  
				serverSocket.send(sendPacket); 

			}
			else if(firstChar == '$') {
				logger.info("NMEA Message received.  Will log and disregard.");				

				logger.error("*** Invalid message format received.  NMEA Message: " + sentence + "***");
			}	
	    }
		catch(SocketException sockEx) {
	    	logger.error("UDP Port " + udpPort + " is occupied.");
		}
	    catch (Exception ex) {
	    	ex.printStackTrace();
	    }
		finally {
			serverSocket.close();
		}
		return CompletableFuture.completedFuture(message);
	}

	@Override
	public void sendRouterMessage(RouterMessage message) throws Exception {
		// TODO Auto-generated method stub

	}

}
