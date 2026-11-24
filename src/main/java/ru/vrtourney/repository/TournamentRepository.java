package ru.vrtourney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vrtourney.model.Tournament;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
}
