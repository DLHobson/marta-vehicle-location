package com.itsmarta.technology.its.martavehiclelocation.configuration;

import com.itsmarta.technology.its.martavehiclelocation.AppRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="spring.task.execution", ignoreInvalidFields = true)
public class AsynchConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(AsynchConfiguration.class);


	private String threadNamePrefix;
	private Pool pool = new Pool();

	public static class Pool {
		private static final Logger logger = LoggerFactory.getLogger(Pool.class);

		private Integer maxSize = 999;
		private Integer coreSize = 99;
		private Boolean allowCoreThreadTimeout = Boolean.FALSE;
		private Integer queueCapacity = 9999;
		private Integer keepAlive = 99;
		private Integer threadWait = 999;

		@Bean
		public Pool pool() {
			logger.info("Pool instantiated");
			return new Pool();
		}
		
		public Integer getMaxSize() {
			return maxSize;
		}
		public Integer getCoreSize() {
			return coreSize;
		}
		public Boolean getAllowCoreThreadTimeout() {
			return allowCoreThreadTimeout;
		}
		public Integer getQueueCapacity() {
			return queueCapacity;
		}
		public Integer getKeepAlive() {
			return keepAlive;
		}
		public Integer getThreadWait() {
			return threadWait;
		}
		public void setMaxSize(Integer maxSize) {
			this.maxSize = maxSize;
		}
		public void setCoreSize(Integer coreSize) {
			this.coreSize = coreSize;
		}
		public void setAllowCoreThreadTimeout(Boolean allowCoreThreadTimeout) {
			this.allowCoreThreadTimeout = allowCoreThreadTimeout;
		}
		public void setQueueCapacity(Integer queueCapacity) {
			this.queueCapacity = queueCapacity;
		}
		public void setKeepAlive(Integer keepAlive) {
			this.keepAlive = keepAlive;
		}
		public void setThreadWait(Integer threadWait) {
			this.threadWait = threadWait;
		}
	}

	@Bean(name="asynchConfig")
	public AsynchConfiguration asynchConfig() {
		logger.info("AsynchConfiguration instantiated");
		logger.info(this.toString());

		return new AsynchConfiguration();
	}

	public String getThreadNamePrefix() {
		return threadNamePrefix;
	}

	public Pool getPool() {
		return pool;
	}

	public void setThreadNamePrefix(String threadNamePrefix) {
		this.threadNamePrefix = threadNamePrefix;
	}

	public void setPool(Pool pool) {
		this.pool = pool;
	}

	@Override
	public String toString() {
		return "AsynchConfiguration{" +
				"threadNamePrefix='" + threadNamePrefix + '\'' +
				", pool=" + pool +
				'}';
	}
}
