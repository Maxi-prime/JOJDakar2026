package com.joj.service;

import com.joj.dao.UtilisateurDAO;
import com.joj.model.Utilisateur;
import java.util.List;

public class UtilisateurService {
    private UtilisateurDAO utilisateurDAO;

    public UtilisateurService() {
        this.utilisateurDAO = new UtilisateurDAO();
    }

    public void ajouterUtilisateur(String nomComplet, String login, String motDePasse, String role) {
        Utilisateur utilisateur = new Utilisateur(0, nomComplet, login, motDePasse, role);
        utilisateurDAO.ajouter(utilisateur);
    }

    public void modifierUtilisateur(int id, String nomComplet, String login, String motDePasse, String role) {
        Utilisateur utilisateur = new Utilisateur(id, nomComplet, login, motDePasse, role);
        utilisateurDAO.modifier(utilisateur);
    }

    public void supprimerUtilisateur(int id) {
        utilisateurDAO.supprimer(id);
    }

    public Utilisateur rechercherUtilisateurParId(int id) {
        return utilisateurDAO.rechercherParId(id);
    }

    public Utilisateur rechercherUtilisateurParLogin(String login) {
        return utilisateurDAO.rechercherParLogin(login);
    }

    public List<Utilisateur> listerUtilisateurs() {
        return utilisateurDAO.listerTous();
    }
}