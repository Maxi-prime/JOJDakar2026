package com.joj.dao;

import com.joj.model.Pays;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaysDAO {
    private Connection connection;

    public PaysDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public void ajouter(Pays pays) {
        String sql = "INSERT INTO pays (nom, continent) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pays.getNom());
            ps.setString(2, pays.getContinent());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifier(Pays pays) {
        String sql = "UPDATE pays SET nom = ?, continent = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pays.getNom());
            ps.setString(2, pays.getContinent());
            ps.setInt(3, pays.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimer(int id) {
        String sqlCheck = "SELECT COUNT(*) FROM athlete WHERE pays_id = ?";
        try {
            PreparedStatement psCheck = connection.prepareStatement(sqlCheck);
            psCheck.setInt(1, id);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Impossible de supprimer ce pays car il a des athletes associes !");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "DELETE FROM pays WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pays rechercherParId(int id) {
        String sql = "SELECT * FROM pays WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Pays(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("continent")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pays> listerTous() {
        List<Pays> pays = new ArrayList<>();
        String sql = "SELECT * FROM pays";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pays.add(new Pays(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("continent")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pays;
    }
}