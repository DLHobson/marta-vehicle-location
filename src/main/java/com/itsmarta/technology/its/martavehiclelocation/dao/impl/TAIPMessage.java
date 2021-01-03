package com.itsmarta.technology.its.martavehiclelocation.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.itsmarta.technology.its.martavehiclelocation.dao.RouterMessage;
import com.itsmarta.technology.its.martavehiclelocation.service.impl.PublishVehicleLocationService;

public class TAIPMessage implements RouterMessage {
	
    private static final Logger logger = LoggerFactory.getLogger(PublishVehicleLocationService.class);
	
	private InetAddress sourceIPaddress = null;
    private int sourcePort = 0;
	private String sentence = null;
	private int messageLength = 0;
	private String responseQualifier = null;
	private String messageTypeIdentifier = null;
	private String gpsTOD = null;
	private String latitude = null;
	private String longitude = null;
	private String altitude = null;
	private String horizontalSpeed = null;
	private String verticleSpeed = null;
	private String heading = null;
	private String numOfSV = null;
	private String svID = null;
	private String IODE = null;
	private String reserved = null;
	private String sourceOfData = null;
	private String ageOfData = null;
	private String vehicleID = null;
	private String checksum = null;
 	
	public TAIPMessage() {
		super();
	}

    public TAIPMessage(InetAddress sourceIPaddress, int sourcePort, String sentence, int messageLength) {
		super();
		this.sourceIPaddress = sourceIPaddress;
		this.sourcePort = sourcePort;
		this.sentence = sentence;
		this.messageLength = messageLength;
	}

    @Override
	public InetAddress getSourceIPaddress() {
		return sourceIPaddress;
	}
	
	@Override
	public void setSourceIPaddress(InetAddress sourceIPaddress) {
		this.sourceIPaddress = sourceIPaddress;
	}
	
	@Override
	public int getSourcePort() {
		return sourcePort;
	}

	@Override
	public void setSourcePort(int sourcePort) {
		this.sourcePort = sourcePort;
	}
	
	@Override
	public String getSentence() {
		return sentence;
	}

	@Override
	public void setSentence(String sentence) {
		this.sentence = sentence;
		
	}
	
	@Override
	public int getMessageLength() {
		return messageLength;
	}

	@Override
	public void setMessageLength(int messageLength) {
		this.messageLength = messageLength;
	}
	
	public String getResponseQualifier() {
		return responseQualifier;
	}

	public String getMessageTypeIdentifier() {
		return messageTypeIdentifier;
	}

	public String getGpsTOD() {
		return gpsTOD;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getAltitude() {
		return altitude;
	}

	public String getHorizontalSpeed() {
		return horizontalSpeed;
	}

	public String getVerticleSpeed() {
		return verticleSpeed;
	}

	public String getHeading() {
		return heading;
	}

	public String getNumOfSV() {
		return numOfSV;
	}

	public String getSvID() {
		return svID;
	}

	public String getIODE() {
		return IODE;
	}

	public String getReserved() {
		return reserved;
	}

	public String getSourceOfData() {
		return sourceOfData;
	}

	public String getAgeOfData() {
		return ageOfData;
	}

	public String getVehicleID() {
		return vehicleID;
	}

	public String getChecksum() {
		return checksum;
	}
	
	public String toString() {
		return "TAIPMessage [sourceIPaddress=" + sourceIPaddress + ", sourcePort=" + sourcePort + ", sentence="
				+ sentence + ", messageLength=" + messageLength + "]";
	}

	@Override
	public void ParseMessageString(String sentence) {
		try {
			logger.info("Parsing sentence: " + sentence + "...");
			this.responseQualifier = new String(sentence.substring(1,2));
			this.messageTypeIdentifier = new String(sentence.substring(2,4));
			this.gpsTOD = new String(sentence.substring(4,12));
			this.latitude = new String(sentence.substring(12,22));
			logger.info("Fields:" 
					+ "responseQualifier: " + this.responseQualifier + " sentence part: " + sentence.substring(1,2)
					+ "messageTypeIdentifier: " + this.messageTypeIdentifier + " sentence part: " + sentence.substring(2,4)
					+ "gpsTOD: " + this.gpsTOD + " sentence part: " + sentence.substring(4,12)
					+ "latitude: " + this.latitude + " sentence part: " + sentence.substring(12,22));
			this.longitude = new String(sentence.substring(22,33));
			this.altitude = new String(sentence.substring(33,42));
			this.horizontalSpeed = new String(sentence.substring(42,46));
			this.verticleSpeed = new String(sentence.substring(46,51));
			logger.info("Fields:" 
					+ "longitude: " + this.longitude + " sentence part: " + sentence.substring(22,33)
					+ "altitude: " + this.altitude + " sentence part: " + sentence.substring(33,42)
					+ "horizontalSpeed: " + this.horizontalSpeed + " sentence part: " + sentence.substring(42,46)
					+ "verticleSpeed: " + this.verticleSpeed + " sentence part: " + sentence.substring(46,51));
			this.heading = new String(sentence.substring(51,55));
			this.numOfSV = new String(sentence.substring(55,57));
			this.svID = new String(sentence.substring(57,59));
			this.IODE = new String(sentence.substring(59,61));
			logger.info("Fields:" 
					+ "heading: " + this.heading + " sentence part: " + sentence.substring(51,55)
					+ "numOfSV: " + this.numOfSV + " sentence part: " + sentence.substring(55,57)
					+ "svID: " + this.svID + " sentence part: " + sentence.substring(57,59)
					+ "IODE: " + this.IODE + " sentence part: " + sentence.substring(59,61));
			this.reserved = new String(sentence.substring(61,115));
			this.sourceOfData = new String(sentence.substring(115,116));
			this.ageOfData = new String(sentence.substring(116,117));
			this.vehicleID = new String(sentence.substring(117,125));
			logger.info("Fields:" 
					+ "reserved: " + this.reserved + " sentence part: " + sentence.substring(61,115)
					+ "sourceOfData: " + this.sourceOfData + " sentence part: " + sentence.substring(115,116)
					+ "ageOfData: " + this.ageOfData + " sentence part: " + sentence.substring(116,117)
					+ "vehicleID: " + this.vehicleID + " sentence part: " + sentence.substring(117,125));
			this.checksum =  new String(sentence.substring(125,126));
			logger.info("Fields:" 
					+ "checksum: " + this.checksum + " sentence part: " + sentence.substring(125,126));
			logger.info("complete");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

	}
	
}
