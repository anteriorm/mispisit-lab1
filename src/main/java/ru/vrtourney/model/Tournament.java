package ru.vrtourney.model;

public class Tournament {
    private Long id;
    private String title;
    private String discipline;
    private String format;
    private String date;

    public Tournament() {}

    public Tournament(Long id, String title, String discipline, String format, String date) {
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

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}