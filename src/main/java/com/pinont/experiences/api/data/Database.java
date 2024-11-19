package com.pinont.experiences.api.data;

import org.bukkit.entity.Player;

import java.sql.*;

public class Database {
    public static Connection connection;
    public Connection getConnection() throws SQLException {
        String host = "127.0.0.1:3306";
        String database = "ArmoredSMP";
        String username = "adminArmored";
        String password = "password";
        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database + "?useSSL=true", username, password);
        Database.connection = con;
        return con;
    }

    public void initializeDatabase() throws SQLException {
       Statement statement = getConnection().createStatement();

       statement.execute("CREATE TABLE IF NOT EXISTS ban (uuid varchar(36), username varchar(16), reason varchar(128), duration timestamp)");
       statement.close();
    }

    public static void Unban(Player player) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("DELETE FROM ban WHERE uuid = ?");
        ps.setString(1, String.valueOf(player.getUniqueId()));
        ps.executeUpdate();
        ps.close();
    }

    public static void Ban(Player player, String reason, int durationHR) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT IGNORE INTO ban(uuid, username, reason, duration) VALUES (?,?,?, NOW() + INTERVAL ? HOUR)");
        ps.setString(1, player.getUniqueId().toString());
        ps.setString(2, player.getName());
        ps.setString(3, reason);
        ps.setInt(4, durationHR);
        ps.executeUpdate();
        ps.close();
    }
}
