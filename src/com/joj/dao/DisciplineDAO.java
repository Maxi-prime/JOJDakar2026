package com.joj.dao;

import com.joj.model.Discipline;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplineDAO {
    private Connection connection;

    public DisciplineDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void ajouter(Discipline discipline) {
        String sql = "INSERT INTO discipline (nom, description) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, discipline.getNom());
            ps.setString(2, discipline.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifier(Discipline discipline) {
        String sql = "UPDATE discipline SET nom = ?, description = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, discipline.getNom());
            ps.setString(2, discipline.getDescription());
            ps.setInt(3, discipline.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimer(int id) {
        String sql = "DELETE FROM discipline WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Discipline rechercherParId(int id) {
        String sql = "SELECT * FROM discipline WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Discipline(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Discipline> listerTous() {
        List<Discipline> disciplines = new ArrayList<>();
        String sql = "SELECT * FROM discipline";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                disciplines.add(new Discipline(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplines;
    }
}