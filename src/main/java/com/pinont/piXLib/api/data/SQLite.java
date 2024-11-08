package com.pinont.piXLib.api.data;

import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.UUID;

public class SQLite {

    private final String name;

    private Connection connection = getConnection();;

    public SQLite(String name) {
        this.name = name;
    }

    private Connection getConnection() {
        try {
            return connection = DriverManager.getConnection("jdbc:sqlite:%s.db".formatted(this.name));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public void createTable(String tableName, String columnName, String columnLabel) {
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS " + tableName + "(" + columnName + "," + columnLabel + ")");
    }

    @SneakyThrows
    public void insert(String tableName, String name, String values) {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO " + tableName + "(name, values) VALUES('" + name + "', " + values + ")");
    }

    @SneakyThrows
    public void delete(String tableName, String key, String value) {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM " + tableName + " WHERE " + key + " = " + value);
    }

    @SneakyThrows
    public Double getDouble(String columnName, String columnLabel) {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery("SELECT * FROM " + name + " WHERE key = " + columnName).getDouble(columnLabel);
    }
}
