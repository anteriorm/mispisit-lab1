package ru.vrtourney.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vrtourney.model.Tournament;
import ru.vrtourney.model.TournamentNotification;
import ru.vrtourney.repository.TournamentNotificationRepository;
import ru.vrtourney.repository.TournamentRepository;

@Service
@Transactional(readOnly = true)
public class TournamentService {
    private final TournamentRepository repository;

    private final TournamentNotificationRepository notifications;

    public TournamentService(TournamentRepository repository, TournamentNotificationRepository notifications) {
        this.repository = repository;
        this.notifications = notifications;
    }

    public List<Tournament> findAll() {
        return repository.findAll(Sort.by("id"));
    }

    public Optional<Tournament> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Tournament save(Tournament tournament) {
        if (tournament.getId() != null) {
            throw new IllegalArgumentException("Создание турнира не принимает готовый идентификатор");
        }
        if (tournament.getTitle() == null || tournament.getTitle().isBlank()) {
            throw new IllegalArgumentException("Название турнира не может быть пустым");
        }
        tournament.setTitle(tournament.getTitle().trim());
        Tournament saved = repository.save(tournament);
        notifications.save(new TournamentNotification(saved));
        return saved;
    }
}
