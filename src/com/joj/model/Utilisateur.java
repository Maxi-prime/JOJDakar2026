package com.joj.model;

public class Utilisateur {
    private int id;
    private String nomComplet;
    private String login;
    private String motDePasse;
    private String role;

    public Utilisateur() {}

    public Utilisateur(int id, String nomComplet, String login, String motDePasse, String role) {
        this.id = id;
        this.nomComplet = nomComplet;
        this.login = login;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNomComplet() { return nomComplet; }
    public void setNomComplet(String nomComplet) { this.nomComplet = nomComplet; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}