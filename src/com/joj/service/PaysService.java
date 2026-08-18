package com.joj.service;

import com.joj.dao.PaysDAO;
import com.joj.model.Pays;
import java.util.List;

public class PaysService {
    private PaysDAO paysDAO;

    public PaysService() {
        this.paysDAO = new PaysDAO();
    }

    public void ajouterPays(String nom, String continent) {
        Pays pays = new Pays(0, nom, continent);
        paysDAO.ajouter(pays);
    }

    public void modifierPays(int id, String nom, String continent) {
        Pays pays = new Pays(id, nom, continent);
        paysDAO.modifier(pays);
    }

    public void supprimerPays(int id) {
        paysDAO.supprimer(id);
    }

    public Pays rechercherPaysParId(int id) {
        return paysDAO.rechercherParId(id);
    }

    public List<Pays> listerPays() {
        return paysDAO.listerTous();
    }
}