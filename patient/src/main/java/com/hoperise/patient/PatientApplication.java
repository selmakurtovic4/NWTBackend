package com.hoperise.patient;

import com.hoperise.patient.model.Patient;
import com.hoperise.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.List;


@SpringBootApplication
@EnableDiscoveryClient
public class PatientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}

	@Override
	public void run(String... arg) {
	}
}