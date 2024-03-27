package com.mohit.asyncFileupload.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
	
	@Bean(name = "taskExecutor")
	public Executor taskExecutor() {
	
		ThreadPoolTaskExecutor taskExecutor= new ThreadPoolTaskExecutor();
	    taskExecutor.setCorePoolSize(2);
	    taskExecutor.setMaxPoolSize(2);
	    taskExecutor.setQueueCapacity(100);
	    taskExecutor.setThreadNamePrefix("Mohit-Thread--");
	    taskExecutor.initialize();
		return taskExecutor;
	}

}
