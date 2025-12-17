
package com.game.uno;

import com.game.uno.model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
/**
 *
 * @author erika
 */
public class ConexionPostgreSQL {
    
	private static final String BD_URL = "jdbc:mysql://localhost:3306/uno";
    private static final String USER = "root";
    private static final String PASS = "";
    
    /**
     * Abre una conexión con la base de datos PostgreSQL.
     */
    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(BD_URL, USER, PASS);
    }
    
    /**
     * Guarda los datos de un jugador en la tabla Uno.
     */
    public void savePlayer(String name, int score, String date) {
        String sql = "INSERT INTO Uno(name, score, date) VALUES (?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, score);
            ps.setString(3, date);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "✅ Puntuación guardada correctamente en la BD");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al guardar datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Devuelve todos los jugadores registrados en la base de datos.
     */
    public List<Player> viewPlayers() {
        List<Player> lista = new ArrayList<>();
        String sql = "SELECT id_player, name, score, date FROM Uno ORDER BY score DESC LIMIT 3";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Player d = new Player();
                d.setId(rs.getInt("id_player"));
                d.setName(rs.getString("name"));
                d.setScore(rs.getInt("score"));
                d.setDate(rs.getString("date"));
                lista.add(d);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "❌ Error al consultar datos: " + e.getMessage());
        }

        return lista;
    }
}
