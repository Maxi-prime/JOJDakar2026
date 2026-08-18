package com.joj.model;

public class Resultat {
    private int id;
    private Athlete athlete;
    private Competition competition;
    private String score;
    private int rang;

    public Resultat() {}

    public Resultat(int id, Athlete athlete, Competition competition, String score, int rang) {
        this.id = id;
        this.athlete = athlete;
        this.competition = competition;
        this.score = score;
        this.rang = rang;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Athlete getAthlete() { return athlete; }
    public void setAthlete(Athlete athlete) { this.athlete = athlete; }
    public Competition getCompetition() { return competition; }
    public void setCompetition(Competition competition) { this.competition = competition; }
    public String getScore() { return score; }
    public void setScore(String score) { this.score = score; }
    public int getRang() { return rang; }
    public void setRang(int rang) { this.rang = rang; }
}