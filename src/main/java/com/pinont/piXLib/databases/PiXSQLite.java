package com.pinont.piXLib.databases;

import javax.annotation.Nonnull;
import java.sql.*;

public class PiXSQLite {
    private Connection connection;

    public void getConnection(@Nonnull String database) {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + database);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void disconnect() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(@Nonnull String createTableSQL) {
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getString(@Nonnull String query) {
        try (Statement stmt = this.connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getString(1);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}