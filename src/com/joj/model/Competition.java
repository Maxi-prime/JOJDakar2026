package com.joj.model;

import java.sql.Date;

public class Competition {
    private int id;
    private String nom;
    private Date dateCompetition;
    private String lieu;
    private Discipline discipline;

    public Competition() {}

    public Competition(int id, String nom, Date dateCompetition, String lieu, Discipline discipline) {
        this.id = id;
        this.nom = nom;
        this.dateCompetition = dateCompetition;
        this.lieu = lieu;
        this.discipline = discipline;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Date getDateCompetition() { return dateCompetition; }
    public void setDateCompetition(Date dateCompetition) { this.dateCompetition = dateCompetition; }
    public String getLieu() { return lieu; }
    public void setLieu(String lieu) { this.lieu = lieu; }
    public Discipline getDiscipline() { return discipline; }
    public void setDiscipline(Discipline discipline) { this.discipline = discipline; }
}