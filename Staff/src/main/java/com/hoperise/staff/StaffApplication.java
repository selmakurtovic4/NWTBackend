package com.hoperise.staff;

import com.hoperise.staff.exceptions.EventInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StaffApplication {

	@Bean
	public EventInterceptor customInterceptor() {return new EventInterceptor(); }

	public static void main(String[] args) {
		SpringApplication.run(StaffApplication.class, args);
	}

}
