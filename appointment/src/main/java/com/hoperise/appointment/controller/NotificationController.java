package com.hoperise.appointment.controller;

import com.hoperise.appointment.model.Appointment;
import com.hoperise.appointment.model.Notification;
import com.hoperise.appointment.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private final NotificationRepository notificationRepository;

    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        notificationRepository.deleteById(id);
    }
}
