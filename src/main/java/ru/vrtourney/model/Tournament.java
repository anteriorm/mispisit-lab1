package ru.vrtourney.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 80)
    private String title;
    @Column(nullable = false, length = 80)
    private String discipline;
    @Column(nullable = false, length = 80)
    private String format;
    @Column(name = "event_date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    public Tournament() {}

    public Tournament(Long id, String title, String discipline, String format, LocalDate date) {
        this.id = id;
        this.title = title;
        this.discipline = discipline;
        this.format = format;
        this.date = date;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDiscipline() { return discipline; }
    public void setDiscipline(String discipline) { this.discipline = discipline; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}