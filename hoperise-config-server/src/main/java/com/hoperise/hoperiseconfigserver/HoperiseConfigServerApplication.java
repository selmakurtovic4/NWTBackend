package com.hoperise.hoperiseconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class HoperiseConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HoperiseConfigServerApplication.class, args);
	}

}
