package ru.vrtourney.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vrtourney.model.TournamentNotification;

public interface TournamentNotificationRepository extends JpaRepository<TournamentNotification, Long> {
    @EntityGraph(attributePaths = "tournament")
    Optional<TournamentNotification> findFirstByOrderByCreatedAtDescIdDesc();
}
