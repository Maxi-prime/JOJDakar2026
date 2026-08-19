package com.joj.dao;

import com.joj.model.Athlete;
import com.joj.model.Pays;
import com.joj.model.Discipline;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AthleteDAO {
    private Connection connection;
    private PaysDAO paysDAO;
    private DisciplineDAO disciplineDAO;

    public AthleteDAO() {
        this.connection = DatabaseConnection.getConnection();
        this.paysDAO = new PaysDAO();
        this.disciplineDAO = new DisciplineDAO();
    }

    public void ajouter(Athlete athlete) {
        String sql = "INSERT INTO athlete (nom, prenom, sexe, date_naissance, pays_id, discipline_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, athlete.getNom());
            ps.setString(2, athlete.getPrenom());
            ps.setString(3, athlete.getSexe());
            ps.setDate(4, athlete.getDateNaissance());
            ps.setInt(5, athlete.getPays().getId());
            ps.setInt(6, athlete.getDiscipline().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifier(Athlete athlete) {
        String sql = "UPDATE athlete SET nom = ?, prenom = ?, sexe = ?, date_naissance = ?, pays_id = ?, discipline_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, athlete.getNom());
            ps.setString(2, athlete.getPrenom());
            ps.setString(3, athlete.getSexe());
            ps.setDate(4, athlete.getDateNaissance());
            ps.setInt(5, athlete.getPays().getId());
            ps.setInt(6, athlete.getDiscipline().getId());
            ps.setInt(7, athlete.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimer(int id) {
        String sqlCheck = "SELECT COUNT(*) FROM resultat WHERE athlete_id = ?";
        try {
            PreparedStatement psCheck = connection.prepareStatement(sqlCheck);
            psCheck.setInt(1, id);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Impossible de supprimer cet athlete car il a des resultats associes !");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "DELETE FROM athlete WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Athlete rechercherParId(int id) {
        String sql = "SELECT * FROM athlete WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Pays pays = paysDAO.rechercherParId(rs.getInt("pays_id"));
                Discipline discipline = disciplineDAO.rechercherParId(rs.getInt("discipline_id"));
                return new Athlete(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("sexe"),
                        rs.getDate("date_naissance"),
                        pays,
                        discipline
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Athlete> listerTous() {
        List<Athlete> athletes = new ArrayList<>();
        String sql = "SELECT * FROM athlete";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pays pays = paysDAO.rechercherParId(rs.getInt("pays_id"));
                Discipline discipline = disciplineDAO.rechercherParId(rs.getInt("discipline_id"));
                athletes.add(new Athlete(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("sexe"),
                        rs.getDate("date_naissance"),
                        pays,
                        discipline
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return athletes;
    }

    public List<Athlete> rechercherParPays(int paysId) {
        List<Athlete> athletes = new ArrayList<>();
        String sql = "SELECT * FROM athlete WHERE pays_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, paysId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pays pays = paysDAO.rechercherParId(rs.getInt("pays_id"));
                Discipline discipline = disciplineDAO.rechercherParId(rs.getInt("discipline_id"));
                athletes.add(new Athlete(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("sexe"),
                        rs.getDate("date_naissance"),
                        pays,
                        discipline
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return athletes;
    }
}