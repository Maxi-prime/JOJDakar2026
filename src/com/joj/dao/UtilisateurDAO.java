package com.joj.dao;

import com.joj.model.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {
    private Connection connection;

    public UtilisateurDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void ajouter(Utilisateur utilisateur) {
        String sql = "INSERT INTO utilisateur (nom_complet, login, mot_de_passe, role) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, utilisateur.getNomComplet());
            ps.setString(2, utilisateur.getLogin());
            ps.setString(3, utilisateur.getMotDePasse());
            ps.setString(4, utilisateur.getRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifier(Utilisateur utilisateur) {
        String sql = "UPDATE utilisateur SET nom_complet = ?, login = ?, mot_de_passe = ?, role = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, utilisateur.getNomComplet());
            ps.setString(2, utilisateur.getLogin());
            ps.setString(3, utilisateur.getMotDePasse());
            ps.setString(4, utilisateur.getRole());
            ps.setInt(5, utilisateur.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimer(int id) {
        String sql = "DELETE FROM utilisateur WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Utilisateur rechercherParId(int id) {
        String sql = "SELECT * FROM utilisateur WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom_complet"),
                        rs.getString("login"),
                        rs.getString("mot_de_passe"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Utilisateur rechercherParLogin(String login) {
        String sql = "SELECT * FROM utilisateur WHERE login = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom_complet"),
                        rs.getString("login"),
                        rs.getString("mot_de_passe"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Utilisateur> listerTous() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateur";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                utilisateurs.add(new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom_complet"),
                        rs.getString("login"),
                        rs.getString("mot_de_passe"),
                        rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    public boolean verifierAuthentification(String login, String motDePasse) {
        String sql = "SELECT * FROM utilisateur WHERE login = ? AND mot_de_passe = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, motDePasse);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}