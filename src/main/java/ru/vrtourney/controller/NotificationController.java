package ru.vrtourney.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vrtourney.repository.TournamentNotificationRepository;

@Controller
public class NotificationController {
    private final TournamentNotificationRepository notifications;

    public NotificationController(TournamentNotificationRepository notifications) {
        this.notifications = notifications;
    }

    @GetMapping("/notifications")
    public String latest(Model model) {
        model.addAttribute("notification", notifications.findFirstByOrderByCreatedAtDescIdDesc().orElse(null));
        return "notifications";
    }
}
