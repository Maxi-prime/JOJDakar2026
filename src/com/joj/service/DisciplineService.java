package com.joj.service;

import com.joj.dao.DisciplineDAO;
import com.joj.model.Discipline;
import java.util.List;

public class DisciplineService {
    private DisciplineDAO disciplineDAO;

    public DisciplineService() {
        this.disciplineDAO = new DisciplineDAO();
    }

    public void ajouterDiscipline(String nom, String description) {
        Discipline discipline = new Discipline(0, nom, description);
        disciplineDAO.ajouter(discipline);
    }

    public void modifierDiscipline(int id, String nom, String description) {
        Discipline discipline = new Discipline(id, nom, description);
        disciplineDAO.modifier(discipline);
    }

    public void supprimerDiscipline(int id) {
        disciplineDAO.supprimer(id);
    }

    public Discipline rechercherDisciplineParId(int id) {
        return disciplineDAO.rechercherParId(id);
    }

    public List<Discipline> listerDisciplines() {
        return disciplineDAO.listerTous();
    }
}