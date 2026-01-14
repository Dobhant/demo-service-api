package org.example.notificationservice.controller;

import org.example.notificationservice.model.UserEvent;
import org.example.notificationservice.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notify")
public class NotificationController {

    private final EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity<String> sendEmailManually(@RequestBody UserEvent event) {
        try {
            emailService.sendEmailBasedOnEvent(event);
            return ResponseEntity.ok("Email отправлен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при отправке email: " + e.getMessage());
        }
    }
}