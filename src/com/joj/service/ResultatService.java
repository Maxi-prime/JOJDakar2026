package com.joj.service;

import com.joj.dao.ResultatDAO;
import com.joj.model.Resultat;
import com.joj.model.Athlete;
import com.joj.model.Competition;
import java.util.List;

public class ResultatService {
    private ResultatDAO resultatDAO;
    private AthleteService athleteService;
    private CompetitionService competitionService;

    public ResultatService() {
        this.resultatDAO = new ResultatDAO();
        this.athleteService = new AthleteService();
        this.competitionService = new CompetitionService();
    }

    public void enregistrerResultat(int athleteId, int competitionId, String score, int rang) {
        Athlete athlete = athleteService.rechercherAthleteParId(athleteId);
        Competition competition = competitionService.rechercherCompetitionParId(competitionId);
        Resultat resultat = new Resultat(0, athlete, competition, score, rang);
        resultatDAO.ajouter(resultat);
    }

    public void modifierResultat(int id, int athleteId, int competitionId, String score, int rang) {
        Athlete athlete = athleteService.rechercherAthleteParId(athleteId);
        Competition competition = competitionService.rechercherCompetitionParId(competitionId);
        Resultat resultat = new Resultat(id, athlete, competition, score, rang);
        resultatDAO.modifier(resultat);
    }

    public void supprimerResultat(int id) {
        resultatDAO.supprimer(id);
    }

    public Resultat rechercherResultatParId(int id) {
        return resultatDAO.rechercherParId(id);
    }

    public List<Resultat> listerResultats() {
        return resultatDAO.listerTous();
    }

    public List<Resultat> classementCompetition(int competitionId) {
        return resultatDAO.classementCompetition(competitionId);
    }
}