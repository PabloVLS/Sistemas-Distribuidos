package com.mycompany.prjaviaoservlet.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class AviaoDAO {

    private String jdbcURL = "jdbc:postgresql://localhost:5432/aviao";
    private String jdbcUser = "postgres";
    private String jdbcPass = "postgres";

    private static final String INSERT = "INSERT INTO aviao (modelo, fabricante, capacidade) VALUES (?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM aviao";

    public AviaoDAO() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver PostgreSQL carregado!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void salvar(Aviao aviao) {
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass); PreparedStatement stmt = conn.prepareStatement(INSERT)) {
            stmt.setString(1, aviao.getModelo());
            stmt.setString(2, aviao.getFabricante());
            stmt.setInt(3, aviao.getCapacidade());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Aviao> listar() {
        List<Aviao> avioes = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass); PreparedStatement stmt = conn.prepareStatement(SELECT_ALL)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Aviao a = new Aviao();
                a.setId(rs.getInt("id"));
                a.setModelo(rs.getString("modelo"));
                a.setFabricante(rs.getString("fabricante"));
                a.setCapacidade(rs.getInt("capacidade"));
                avioes.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avioes;
    }

    public void atualizar(Aviao aviao) {
        String sql = "UPDATE aviao SET modelo = ?, fabricante = ?, capacidade = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aviao.getModelo());
            stmt.setString(2, aviao.getFabricante());
            stmt.setInt(3, aviao.getCapacidade());
            stmt.setInt(4, aviao.getId());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// Excluir avião
    public void excluir(int id) {
        String sql = "DELETE FROM aviao WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// Buscar avião por id
    public Aviao buscarPorId(int id) {
        Aviao a = null;
        String sql = "SELECT * FROM aviao WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUser, jdbcPass); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                a = new Aviao();
                a.setId(rs.getInt("id"));
                a.setModelo(rs.getString("modelo"));
                a.setFabricante(rs.getString("fabricante"));
                a.setCapacidade(rs.getInt("capacidade"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }
}
