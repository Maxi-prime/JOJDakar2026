package com.joj.model;

import java.sql.Date;

public class Athlete {
    private int id;
    private String nom;
    private String prenom;
    private String sexe;
    private Date dateNaissance;
    private Pays pays;
    private Discipline discipline;

    public Athlete() {}

    public Athlete(int id, String nom, String prenom, String sexe, Date dateNaissance, Pays pays, Discipline discipline) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.pays = pays;
        this.discipline = discipline;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }
    public Date getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }
    public Pays getPays() { return pays; }
    public void setPays(Pays pays) { this.pays = pays; }
    public Discipline getDiscipline() { return discipline; }
    public void setDiscipline(Discipline discipline) { this.discipline = discipline; }
}