package com.joj.dao;

import com.joj.model.Resultat;
import com.joj.model.Athlete;
import com.joj.model.Competition;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultatDAO {
    private Connection connection;
    private AthleteDAO athleteDAO;
    private CompetitionDAO competitionDAO;

    public ResultatDAO() {
        this.connection = DatabaseConnection.getConnection();
        this.athleteDAO = new AthleteDAO();
        this.competitionDAO = new CompetitionDAO();
    }

    public void ajouter(Resultat resultat) {
        String sql = "INSERT INTO resultat (athlete_id, competition_id, score, rang) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, resultat.getAthlete().getId());
            ps.setInt(2, resultat.getCompetition().getId());
            ps.setString(3, resultat.getScore());
            ps.setInt(4, resultat.getRang());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifier(Resultat resultat) {
        String sql = "UPDATE resultat SET athlete_id = ?, competition_id = ?, score = ?, rang = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, resultat.getAthlete().getId());
            ps.setInt(2, resultat.getCompetition().getId());
            ps.setString(3, resultat.getScore());
            ps.setInt(4, resultat.getRang());
            ps.setInt(5, resultat.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimer(int id) {
        String sql = "DELETE FROM resultat WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Resultat rechercherParId(int id) {
        String sql = "SELECT * FROM resultat WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Athlete athlete = athleteDAO.rechercherParId(rs.getInt("athlete_id"));
                Competition competition = competitionDAO.rechercherParId(rs.getInt("competition_id"));
                return new Resultat(
                        rs.getInt("id"),
                        athlete,
                        competition,
                        rs.getString("score"),
                        rs.getInt("rang")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Resultat> listerTous() {
        List<Resultat> resultats = new ArrayList<>();
        String sql = "SELECT * FROM resultat";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Athlete athlete = athleteDAO.rechercherParId(rs.getInt("athlete_id"));
                Competition competition = competitionDAO.rechercherParId(rs.getInt("competition_id"));
                resultats.add(new Resultat(
                        rs.getInt("id"),
                        athlete,
                        competition,
                        rs.getString("score"),
                        rs.getInt("rang")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultats;
    }

    public List<Resultat> classementCompetition(int competitionId) {
        List<Resultat> resultats = new ArrayList<>();
        String sql = "SELECT * FROM resultat WHERE competition_id = ? ORDER BY rang ASC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, competitionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Athlete athlete = athleteDAO.rechercherParId(rs.getInt("athlete_id"));
                Competition competition = competitionDAO.rechercherParId(rs.getInt("competition_id"));
                resultats.add(new Resultat(
                        rs.getInt("id"),
                        athlete,
                        competition,
                        rs.getString("score"),
                        rs.getInt("rang")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultats;
    }
}