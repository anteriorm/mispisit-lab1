package ru.vrtourney.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "VR-Tourney");
        model.addAttribute("description",
                "Система управления турнирной сеткой VR-спорта");
        model.addAttribute("version", "0.0.3");
        model.addAttribute("status", "Разработка продолжается");
        return "index";
    }

    @GetMapping("/access-denied")
    public String accessDenied(Model model) {
        model.addAttribute("reason",
                "Создавать турниры и управлять ими может только организатор. " +
                        "Войдите с учётной записью организатора, чтобы продолжить.");
        return "error/access-denied";
    }
}