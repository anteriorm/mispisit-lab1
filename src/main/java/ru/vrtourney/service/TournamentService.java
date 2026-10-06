package ru.vrtourney.service;

import org.springframework.stereotype.Service;
import ru.vrtourney.model.Tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TournamentService {

    private final List<Tournament> tournaments = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public TournamentService() {
        // Демо-данные, чтобы список не был пустым при старте
        save(new Tournament(null, "VR Weekend Cup",
                "Beat Saber", "1x1", "2026-11-15"));
        save(new Tournament(null, "Eleven Table Tennis Open",
                "Eleven", "2x2", "2026-11-22"));
        save(new Tournament(null, "Creed Championship",
                "Creed", "Группы + плей-офф", "2026-12-01"));
    }

    public List<Tournament> findAll() {
        return tournaments;
    }

    public Optional<Tournament> findById(Long id) {
        return tournaments.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    public Tournament save(Tournament tournament) {
        if (tournament.getId() == null) {
            tournament.setId(counter.getAndIncrement());
        }
        tournaments.add(tournament);
        return tournament;
    }
}