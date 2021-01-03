package com.itsmarta.technology.its.martavehiclelocation.service;

import java.util.concurrent.CompletableFuture;

import com.itsmarta.technology.its.martavehiclelocation.dao.RouterMessage;

public interface ReceiveRouterMessageService {
	
	CompletableFuture<RouterMessage> receiveRouterMessage(int udpPort) throws Exception;
	void sendRouterMessage(RouterMessage message) throws Exception;

}
