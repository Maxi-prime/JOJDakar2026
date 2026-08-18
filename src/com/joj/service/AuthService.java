package com.joj.service;

import com.joj.dao.UtilisateurDAO;
import com.joj.model.Utilisateur;

public class AuthService {
    private UtilisateurDAO utilisateurDAO;
    private Utilisateur utilisateurConnecte;

    public AuthService() {
        this.utilisateurDAO = new UtilisateurDAO();
    }

    public boolean seConnecter(String login, String motDePasse) {
        boolean authentifie = utilisateurDAO.verifierAuthentification(login, motDePasse);
        if (authentifie) {
            utilisateurConnecte = utilisateurDAO.rechercherParLogin(login);
        }
        return authentifie;
    }

    public void seDeconnecter() {
        utilisateurConnecte = null;
    }

    public Utilisateur getUtilisateurConnecte() {
        return utilisateurConnecte;
    }

    public boolean estAdmin() {
        if (utilisateurConnecte == null) {
            return false;
        }
        return "admin".equals(utilisateurConnecte.getRole());
    }

    public boolean estConnecte() {
        return utilisateurConnecte != null;
    }
}