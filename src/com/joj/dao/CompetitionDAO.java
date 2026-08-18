package com.joj.dao;

import com.joj.model.Competition;
import com.joj.model.Discipline;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompetitionDAO {
    private Connection connection;
    private DisciplineDAO disciplineDAO;

    public CompetitionDAO() {
        this.connection = DatabaseConnection.getConnection();
        this.disciplineDAO = new DisciplineDAO();
    }

    public void ajouter(Competition competition) {
        String sql = "INSERT INTO competition (nom, date_competition, lieu, discipline_id) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, competition.getNom());
            ps.setDate(2, competition.getDateCompetition());
            ps.setString(3, competition.getLieu());
            ps.setInt(4, competition.getDiscipline().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifier(Competition competition) {
        String sql = "UPDATE competition SET nom = ?, date_competition = ?, lieu = ?, discipline_id = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, competition.getNom());
            ps.setDate(2, competition.getDateCompetition());
            ps.setString(3, competition.getLieu());
            ps.setInt(4, competition.getDiscipline().getId());
            ps.setInt(5, competition.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimer(int id) {
        String sql = "DELETE FROM competition WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Competition rechercherParId(int id) {
        String sql = "SELECT * FROM competition WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Discipline discipline = disciplineDAO.rechercherParId(rs.getInt("discipline_id"));
                return new Competition(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getDate("date_competition"),
                        rs.getString("lieu"),
                        discipline
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Competition> listerTous() {
        List<Competition> competitions = new ArrayList<>();
        String sql = "SELECT * FROM competition";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Discipline discipline = disciplineDAO.rechercherParId(rs.getInt("discipline_id"));
                competitions.add(new Competition(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getDate("date_competition"),
                        rs.getString("lieu"),
                        discipline
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return competitions;
    }

    public List<Competition> rechercherParDiscipline(int disciplineId) {
        List<Competition> competitions = new ArrayList<>();
        String sql = "SELECT * FROM competition WHERE discipline_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, disciplineId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Discipline discipline = disciplineDAO.rechercherParId(rs.getInt("discipline_id"));
                competitions.add(new Competition(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getDate("date_competition"),
                        rs.getString("lieu"),
                        discipline
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return competitions;
    }
}