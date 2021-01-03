package com.itsmarta.technology.its.martavehiclelocation.dao.impl;

import org.springframework.stereotype.Component;

import com.itsmarta.technology.its.martavehiclelocation.dao.QueueMessage;

public class FTLMessage implements QueueMessage{
	
    private String appName = null;
    private String msgType = null;
    private String msgSubtype = null;
    private String msgTimestamp = null;
    private String msgSource = null;
    private String businessKeyName = null;
    private String businessKeyValue = null;
    private String payload = null;
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgSubtype() {
		return msgSubtype;
	}
	public void setMsgSubtype(String msgSubtype) {
		this.msgSubtype = msgSubtype;
	}
	public String getMsgTimestamp() {
		return msgTimestamp;
	}
	public void setMsgTimestamp(String msgTimestamp) {
		this.msgTimestamp = msgTimestamp;
	}
	public String getMsgSource() {
		return msgSource;
	}
	public void setMsgSource(String msgSource) {
		this.msgSource = msgSource;
	}
	public String getBusinessKeyName() {
		return businessKeyName;
	}
	public void setBusinessKeyName(String businessKeyName) {
		this.businessKeyName = businessKeyName;
	}
	public String getBusinessKeyValue() {
		return businessKeyValue;
	}
	public void setBusinessKeyValue(String businessKeyValue) {
		this.businessKeyValue = businessKeyValue;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "FTLMessage [appName=" + appName + ", msgType=" + msgType + ", msgSubtype=" + msgSubtype
				+ ", msgTimestamp=" + msgTimestamp + ", msgSource=" + msgSource + ", businessKeyName=" + businessKeyName
				+ ", businessKeyValue=" + businessKeyValue + ", payload=" + payload + "]";
	}
    
}
