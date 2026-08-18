package com.joj.model;

public class Pays {
    private int id;
    private String nom;
    private String continent;

    public Pays() {}

    public Pays(int id, String nom, String continent) {
        this.id = id;
        this.nom = nom;
        this.continent = continent;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getContinent() { return continent; }
    public void setContinent(String continent) { this.continent = continent; }
}