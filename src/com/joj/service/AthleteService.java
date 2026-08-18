package com.joj.service;

import com.joj.dao.AthleteDAO;
import com.joj.model.Athlete;
import com.joj.model.Pays;
import com.joj.model.Discipline;
import java.sql.Date;
import java.util.List;

public class AthleteService {
    private AthleteDAO athleteDAO;
    private PaysService paysService;
    private DisciplineService disciplineService;

    public AthleteService() {
        this.athleteDAO = new AthleteDAO();
        this.paysService = new PaysService();
        this.disciplineService = new DisciplineService();
    }

    public void ajouterAthlete(String nom, String prenom, String sexe, Date dateNaissance, int paysId, int disciplineId) {
        Pays pays = paysService.rechercherPaysParId(paysId);
        Discipline discipline = disciplineService.rechercherDisciplineParId(disciplineId);
        Athlete athlete = new Athlete(0, nom, prenom, sexe, dateNaissance, pays, discipline);
        athleteDAO.ajouter(athlete);
    }

    public void modifierAthlete(int id, String nom, String prenom, String sexe, Date dateNaissance, int paysId, int disciplineId) {
        Pays pays = paysService.rechercherPaysParId(paysId);
        Discipline discipline = disciplineService.rechercherDisciplineParId(disciplineId);
        Athlete athlete = new Athlete(id, nom, prenom, sexe, dateNaissance, pays, discipline);
        athleteDAO.modifier(athlete);
    }

    public void supprimerAthlete(int id) {
        athleteDAO.supprimer(id);
    }

    public Athlete rechercherAthleteParId(int id) {
        return athleteDAO.rechercherParId(id);
    }

    public List<Athlete> listerAthletes() {
        return athleteDAO.listerTous();
    }

    public List<Athlete> rechercherAthletesParPays(int paysId) {
        return athleteDAO.rechercherParPays(paysId);
    }
}