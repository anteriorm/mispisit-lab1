package ru.vrtourney.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tournament_notifications")
public class TournamentNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tournament_id", nullable = false, unique = true)
    private Tournament tournament;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(nullable = false, length = 16)
    private String status;

    protected TournamentNotification() {
    }

    public TournamentNotification(Tournament tournament) {
        this.tournament = tournament;
        this.createdAt = Instant.now();
        this.status = "NEW";
    }

    public Tournament getTournament() {
        return tournament;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }
}
