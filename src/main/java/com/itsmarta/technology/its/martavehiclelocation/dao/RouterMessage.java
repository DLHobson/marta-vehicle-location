package com.itsmarta.technology.its.martavehiclelocation.dao;

import java.net.InetAddress;

public interface RouterMessage {
	
	InetAddress getSourceIPaddress();
	void setSourceIPaddress(InetAddress sourceIPaddress);
	int getSourcePort();
	void setSourcePort(int sourcePort);
	String getSentence();
	void setSentence(String sentence);
	int getMessageLength();
	void setMessageLength(int messageLength);
	String toString();
	void ParseMessageString(String sentence);
	
}
