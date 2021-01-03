package com.itsmarta.technology.its.martavehiclelocation.dao.impl;

import java.net.InetAddress;

import org.springframework.stereotype.Component;

import com.itsmarta.technology.its.martavehiclelocation.dao.RouterMessage;

public class NMEAMessage implements RouterMessage {
	private InetAddress sourceIPaddress = null;
    private int sourcePort = 0;
	private String sentence = null;
	private int messageLength = 0;
	private String sentenceType = null;
	private String dataStatus = null;
	private String latitude = null;
	private String latitudeDirection = null;
	private String longitude = null;
	private String longitudeDirection = null;
	private String placeholder1 = null;
	private String placeholder2 = null;
	private String speedInKnots = null;
	private String courseOverGround = null;
	private String variation = null;
	private String directionOfVariation = null;
	private String checksum = null;

	
	public NMEAMessage() {
		super();
	}
	
    public NMEAMessage(InetAddress sourceIPaddress, int sourcePort, String sentence, int messageLength) {
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

	public String getSentenceType() {
		return sentenceType;
	}

	public String getDataStatus() {
		return dataStatus;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLatitudeDirection() {
		return latitudeDirection;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getLongitudeDirection() {
		return longitudeDirection;
	}

	public String getPlaceholder1() {
		return placeholder1;
	}

	public String getPlaceholder2() {
		return placeholder2;
	}

	public String getSpeedInKnots() {
		return speedInKnots;
	}

	public String getCourseOverGround() {
		return courseOverGround;
	}

	public String getVariation() {
		return variation;
	}

	public String getDirectionOfVariation() {
		return directionOfVariation;
	}

	public String getChecksum() {
		return checksum;
	}
	
	public String toString() {
		return "NMEAMessage [sourceIPaddress=" + sourceIPaddress + ", sourcePort=" + sourcePort + ", sentence="
				+ sentence + ", messageLength=" + messageLength + "]";
	}

	@Override
	public void ParseMessageString(String sentence) {
		String[] messageParts = sentence.split(",");
		
		if(messageParts[0] != null) this.sentenceType = messageParts[0];
		if(messageParts[1] != null) this.dataStatus = messageParts[1];
		if(messageParts[2] != null) this.latitude = messageParts[2];
		if(messageParts[3] != null) this.latitudeDirection = messageParts[3];
		if(messageParts[4] != null) this.longitude = messageParts[4];
		if(messageParts[5] != null) this.longitudeDirection = messageParts[5];
		if(messageParts[6] != null) this.placeholder1 = messageParts[6];
		if(messageParts[7] != null) this.placeholder2 = messageParts[7];
		if(messageParts[8] != null) this.speedInKnots = messageParts[8];
		if(messageParts[9] != null) this.courseOverGround = messageParts[9];
		if(messageParts[10] != null) this.variation = messageParts[10];
		if(messageParts[11] != null) this.directionOfVariation = messageParts[11];
		if(messageParts[12] != null) this.checksum = messageParts[12];
		
	}
}
