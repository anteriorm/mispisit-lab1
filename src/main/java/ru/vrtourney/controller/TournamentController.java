package ru.vrtourney.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vrtourney.model.Tournament;
import ru.vrtourney.service.TournamentService;

@Controller
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService service;

    public TournamentController(TournamentService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("tournaments", service.findAll());
        return "tournaments/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("tournament", new Tournament());
        return "tournaments/form";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute Tournament tournament) {
        service.save(tournament);
        return "redirect:/tournaments";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        return service.findById(id)
                .map(t -> {
                    model.addAttribute("tournament", t);
                    return "tournaments/view";
                })
                .orElse("redirect:/tournaments");
    }
    
    @GetMapping("/admin-panel")
    public String adminPanel() {
        return "redirect:/access-denied";
    }
}