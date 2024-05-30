package com.hoperise.patient;

import com.hoperise.patient.model.Patient;
import com.hoperise.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@EnableDiscoveryClient
public class PatientApplication implements CommandLineRunner {

	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}

	@Bean
	public EventInterceptor customInterceptor() { return new EventInterceptor(); }

	@Override
	public void run(String... arg) {
		patientRepository.deleteAll();

		List<Patient> patients = new ArrayList<>();
		patients.add(new Patient(1234567890123L, "John", "Doe", LocalDate.of(1990, 1, 1), "New York", "123 Main St", 5551234567L, "Male", null));
		patients.add(new Patient(2234567890123L, "Jane", "Doe", LocalDate.of(1985, 5, 15), "Los Angeles", "456 Elm St", 5557654321L, "Female", null));
		patients.add(new Patient(3234567890123L, "Michael", "Smith", LocalDate.of(1978, 3, 22), "Chicago", "789 Oak St", 5559876543L, "Male", null));
		patients.add(new Patient(4234567890123L, "Emily", "Jones", LocalDate.of(1995, 7, 30), "Houston", "101 Pine St", 5556543210L, "Female", null));
		patients.add(new Patient(5234567890123L, "David", "Brown", LocalDate.of(1982, 11, 11), "Phoenix", "202 Cedar St", 5553210987L, "Male", null));
		patients.add(new Patient(6234567890123L, "Sarah", "Davis", LocalDate.of(1990, 6, 6), "Philadelphia", "303 Birch St", 5554321098L, "Female", null));
		patients.add(new Patient(7234567890123L, "James", "Miller", LocalDate.of(1988, 12, 12), "San Antonio", "404 Maple St", 5555432109L, "Male", null));
		patients.add(new Patient(8234567890123L, "Laura", "Wilson", LocalDate.of(1992, 8, 8), "San Diego", "505 Spruce St", 5556543219L, "Female", null));
		patients.add(new Patient(9234567890123L, "Robert", "Moore", LocalDate.of(1986, 2, 2), "Dallas", "606 Willow St", 5557654328L, "Male", null));
		patients.add(new Patient(1034567890123L, "Linda", "Taylor", LocalDate.of(1980, 4, 4), "San Jose", "707 Palm St", 5558765432L, "Female", null));

		patientRepository.saveAll(patients);
	}

}