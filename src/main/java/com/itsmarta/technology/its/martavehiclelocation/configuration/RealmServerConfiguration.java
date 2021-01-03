package com.itsmarta.technology.its.martavehiclelocation.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="realm.ftl", ignoreInvalidFields = true)
public class RealmServerConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(RealmServerConfiguration.class);

	//Realm Server Configuration
	private String primaryRealmServer = "** INVALID FIELD - CHECK application.yml **";
	private String userName = "** INVALID FIELD - CHECK application.yml **";
	private String password = "** INVALID FIELD - CHECK application.yml **";
	private String messageApp = "** INVALID FIELD - CHECK application.yml **";
	private String messageEndpoint = "** INVALID FIELD - CHECK application.yml **";
	private String messageFormat = "** INVALID FIELD - CHECK application.yml **";

	private App app = new App();
	
	public class App {
		private String appName = "** INVALID FIELD - CHECK application.yml **";
		private String msgType = "** INVALID FIELD - CHECK application.yml **";
		private String msgSubType = "** INVALID FIELD - CHECK application.yml **";

		public App app() {
			logger.info("App instantiated");
			return new App();
		}
		
		public String getAppName() {
			return appName;
		}
		public String getMsgType() {
			return msgType;
		}
		public String getMsgSubType() {
			return msgSubType;
		}
		public void setAppName(String appName) {
			this.appName = appName;
		}
		public void setMsgType(String msgType) {
			this.msgType = msgType;
		}
		public void setMsgSubType(String msgSubType) {
			this.msgSubType = msgSubType;
		}
	}

	@Bean(name="realmServerConfig")
	public RealmServerConfiguration realmServerConfig() {
		logger.info("RealmServerConfiguration instantiated");
		logger.info(this.toString());

		return new RealmServerConfiguration();
	}

	public String getPrimaryRealmServer() {
		return primaryRealmServer;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getMessageApp() {
		return messageApp;
	}

	public String getMessageEndpoint() {
		return messageEndpoint;
	}

	public String getMessageFormat() {
		return messageFormat;
	}

	public App getApp() {
		return app;
	}

	public void setPrimaryRealmServer(String primaryRealmServer) {
		this.primaryRealmServer = primaryRealmServer;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMessageApp(String messageApp) {
		this.messageApp = messageApp;
	}

	public void setMessageEndpoint(String messageEndpoint) {
		this.messageEndpoint = messageEndpoint;
	}

	public void setMessageFormat(String messageFormat) {
		this.messageFormat = messageFormat;
	}

	public void setApp(App app) {
		this.app = app;
	}

	@Override
	public String toString() {
		return "RealmServerConfiguration{" +
				"primaryRealmServer='" + primaryRealmServer + '\'' +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", messageApp='" + messageApp + '\'' +
				", messageEndpoint='" + messageEndpoint + '\'' +
				", messageFormat='" + messageFormat + '\'' +
				", app=" + app +
				'}';
	}
}
