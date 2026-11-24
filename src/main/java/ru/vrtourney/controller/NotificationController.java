package ru.vrtourney.controller;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vrtourney.repository.TournamentNotificationRepository;

@Controller
public class NotificationController {
    private static final DateTimeFormatter DISPLAY_TIME = DateTimeFormatter
            .ofPattern("dd.MM.yyyy HH:mm 'UTC'xxx", Locale.ROOT)
            .withZone(ZoneId.of("Europe/Ulyanovsk"));

    private final TournamentNotificationRepository notifications;

    public NotificationController(TournamentNotificationRepository notifications) {
        this.notifications = notifications;
    }

    @GetMapping("/notifications")
    public String latest(Model model) {
        notifications.findFirstByOrderByCreatedAtDescIdDesc().ifPresent(notification -> {
            model.addAttribute("notification", notification);
            model.addAttribute("notificationTime", DISPLAY_TIME.format(notification.getCreatedAt()));
        });
        return "notifications";
    }
}
