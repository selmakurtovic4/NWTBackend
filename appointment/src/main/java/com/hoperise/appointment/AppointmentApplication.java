package com.hoperise.appointment;

import com.hoperise.appointment.model.Appointment;
import com.hoperise.appointment.model.AppointmentStatus;
import com.hoperise.appointment.model.Notification;
import com.hoperise.appointment.repository.AppointmentRepository;
import com.hoperise.appointment.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

@SpringBootApplication
public class AppointmentApplication implements CommandLineRunner {
	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	NotificationRepository notificationRepository;

	public static void main(String[] args) {
		SpringApplication.run(AppointmentApplication.class, args);
	}

	@Override
	public void run(String... arg) {
		appointmentRepository.deleteAll();
		notificationRepository.deleteAll();

		var creationDate = LocalDateTime.now();
		var createdBy = "Adna";
		var appointments = new ArrayList<Appointment>();
		appointments.add(new Appointment(LocalDate.of(2024, 3, 25),LocalTime.of(9,0), AppointmentStatus.AVAILABLE.toString(), 1L,1L, creationDate,createdBy));
		appointments.add(new Appointment(LocalDate.of(2024, 3, 25),LocalTime.of(9, 30), AppointmentStatus.AVAILABLE.toString(), 1L,1L, creationDate,createdBy));
		appointments.add(new Appointment(LocalDate.of(2024, 3, 26),LocalTime.of(10, 0), AppointmentStatus.AVAILABLE.toString(), 1L,1L, creationDate,createdBy));
		appointments.add(new Appointment(LocalDate.of(2024, 3, 27),LocalTime.of(11, 30), AppointmentStatus.CANCELLED.toString(), 1L,1L, creationDate,createdBy));
		appointments.add(new Appointment(LocalDate.of(2024, 4, 1),LocalTime.of(12, 0), AppointmentStatus.BOOKED.toString(), 1L,1L, creationDate,createdBy));
		appointments.add(new Appointment(LocalDate.of(2024, 4, 1),LocalTime.of(13, 0), AppointmentStatus.AVAILABLE.toString(), 1L,1L, creationDate,createdBy));
		appointments.add(new Appointment(LocalDate.of(2024, 4, 2),LocalTime.of(10, 0), AppointmentStatus.BOOKED.toString(), 1L,1L, creationDate,createdBy));
		appointmentRepository.saveAll(appointments);

		String message;
		var notifications = new ArrayList<Notification>();
		for (Appointment appointment : appointments) {
			if (appointment.getStatus().equals(AppointmentStatus.BOOKED.toString())) {
				message = "The appointment on " + appointment.getDate() + " at " + appointment.getTime() + " is booked.";
			} else if (appointment.getStatus().equals(AppointmentStatus.CANCELLED.toString())) {
				message = "The appointment on " + appointment.getDate() + " at " + appointment.getTime() + " is cancelled.";
			} else {
				message = "The appointment on " + appointment.getDate() + " at " + appointment.getTime() + " is successfully scheduled.";
			}
			notifications.add(new Notification(message, appointment, creationDate, createdBy));
		}
		notificationRepository.saveAll(notifications);
	}

}
