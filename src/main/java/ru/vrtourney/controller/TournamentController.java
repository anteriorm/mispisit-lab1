package ru.vrtourney.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String create(@ModelAttribute Tournament tournament, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() || tournament.getId() != null || tournament.getTitle() == null || tournament.getTitle().isBlank()
                || tournament.getTitle().trim().length() > 80 || tournament.getDate() == null
                || !java.util.Arrays.asList("Beat Saber", "Eleven", "Creed", "Pistol Whip").contains(tournament.getDiscipline())
                || !java.util.Arrays.asList("1x1", "2x2", "Группы + плей-офф").contains(tournament.getFormat())) {
            model.addAttribute("error", "Укажите название до 80 символов, дисциплину, формат и корректную дату.");
            return "tournaments/form";
        }
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