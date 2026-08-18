package com.joj.service;

import com.joj.dao.CompetitionDAO;
import com.joj.model.Competition;
import com.joj.model.Discipline;
import java.sql.Date;
import java.util.List;

public class CompetitionService {
    private CompetitionDAO competitionDAO;
    private DisciplineService disciplineService;

    public CompetitionService() {
        this.competitionDAO = new CompetitionDAO();
        this.disciplineService = new DisciplineService();
    }

    public void ajouterCompetition(String nom, Date dateCompetition, String lieu, int disciplineId) {
        Discipline discipline = disciplineService.rechercherDisciplineParId(disciplineId);
        Competition competition = new Competition(0, nom, dateCompetition, lieu, discipline);
        competitionDAO.ajouter(competition);
    }

    public void modifierCompetition(int id, String nom, Date dateCompetition, String lieu, int disciplineId) {
        Discipline discipline = disciplineService.rechercherDisciplineParId(disciplineId);
        Competition competition = new Competition(id, nom, dateCompetition, lieu, discipline);
        competitionDAO.modifier(competition);
    }

    public void supprimerCompetition(int id) {
        competitionDAO.supprimer(id);
    }

    public Competition rechercherCompetitionParId(int id) {
        return competitionDAO.rechercherParId(id);
    }

    public List<Competition> listerCompetitions() {
        return competitionDAO.listerTous();
    }

    public List<Competition> rechercherCompetitionsParDiscipline(int disciplineId) {
        return competitionDAO.rechercherParDiscipline(disciplineId);
    }
}