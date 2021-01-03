package com.itsmarta.technology.its.martavehiclelocation.dao;

public interface QueueMessage {
	
	String getAppName();
	void setAppName(String appName);
	String getMsgType();
	void setMsgType(String msgType);
	String getMsgSubtype();
	void setMsgSubtype(String msgSubtype);
	String getMsgTimestamp();
	void setMsgTimestamp(String msgTimestamp);
	String getMsgSource();
	void setMsgSource(String msgSource);
	String getBusinessKeyName();
	void setBusinessKeyName(String businessKeyName);
	String getBusinessKeyValue();
	void setBusinessKeyValue(String businessKeyValue);
	String getPayload();
	void setPayload(String payload);
	
	String toString();


}
