package com.itsmarta.technology.its.martavehiclelocation;

import com.itsmarta.technology.its.martavehiclelocation.configuration.AsynchConfiguration;
import com.itsmarta.technology.its.martavehiclelocation.configuration.RealmServerConfiguration;
import com.itsmarta.technology.its.martavehiclelocation.configuration.RouterMessageConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableConfigurationProperties({AsynchConfiguration.class,
								RealmServerConfiguration.class,
								RouterMessageConfiguration.class})
@EnableAsync
public class MartaVehicleLocationApplication {
    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

	@Autowired
	private AsynchConfiguration asynchConfig;

	public static void main(String[] args) {
//		SpringApplication.run(MartaVehicleLocationApplication.class, args).close();
		ApplicationContext appContext = SpringApplication.run(MartaVehicleLocationApplication.class, args);

		for (String name : appContext.getBeanDefinitionNames()) {
			logger.info(name);
		}
	}

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(asynchConfig.getPool().getCoreSize());
		executor.setMaxPoolSize(asynchConfig.getPool().getMaxSize());
		executor.setQueueCapacity(asynchConfig.getPool().getQueueCapacity());
		executor.setKeepAliveSeconds(asynchConfig.getPool().getKeepAlive());
		executor.setAllowCoreThreadTimeOut(asynchConfig.getPool().getAllowCoreThreadTimeout());
		executor.setThreadNamePrefix(asynchConfig.getThreadNamePrefix());
		executor.initialize();
		return executor;
	}
}
