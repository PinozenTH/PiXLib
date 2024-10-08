package com.pinont.piXLib.databases;

import javax.annotation.Nonnull;
import java.sql.*;

public class PiXSQL {

    private Connection connection;

    public void executeQuery(@Nonnull String query) {
        try {
            Statement statement = this.connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet executeQueryWithResult(@Nonnull String query) {
        try {
            Statement statement = this.connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(@Nonnull String table, @Nonnull String columns) {
        this.executeQuery("CREATE TABLE IF NOT EXISTS " + table + " (" + columns + ")");
    }

    public void removeTable(@Nonnull String table) {
        this.executeQuery("DROP TABLE IF EXISTS " + table);
    }

    public int getInt(@Nonnull String table, @Nonnull String column, @Nonnull String where, @Nonnull String value) {
        try {
            ResultSet resultSet = this.executeQueryWithResult("SELECT " + column + " FROM " + table + " WHERE " + where + " = " + value);
            return resultSet.getInt(column);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getString(@Nonnull String table, @Nonnull String column, @Nonnull String where, @Nonnull String value) {
        try {
            ResultSet resultSet = this.executeQueryWithResult("SELECT " + column + " FROM " + table + " WHERE " + where + " = " + value);
            return resultSet.getString(column);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Double getDouble(@Nonnull String table, @Nonnull String column, @Nonnull String where, @Nonnull String value) {
        try {
            ResultSet resultSet = this.executeQueryWithResult("SELECT " + column + " FROM " + table + " WHERE " + where + " = " + value);
            return resultSet.getDouble(column);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Float getFloat(@Nonnull String table, @Nonnull String column, @Nonnull String where, @Nonnull String value) {
        try {
            ResultSet resultSet = this.executeQueryWithResult("SELECT " + column + " FROM " + table + " WHERE " + where + " = " + value);
            return resultSet.getFloat(column);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long getLong(@Nonnull String table, @Nonnull String column, @Nonnull String where, @Nonnull String value) {
        try {
            ResultSet resultSet = this.executeQueryWithResult("SELECT " + column + " FROM " + table + " WHERE " + where + " = " + value);
            return resultSet.getLong(column);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean getBoolean(@Nonnull String table, @Nonnull String column, @Nonnull String where, @Nonnull String value) {
        try {
            ResultSet resultSet = this.executeQueryWithResult("SELECT " + column + " FROM " + table + " WHERE " + where + " = " + value);
            return resultSet.getBoolean(column);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setInt(@Nonnull String table, @Nonnull String column, @Nonnull String where, @Nonnull String value, int newValue) {
        this.executeQuery("UPDATE " + table + " SET " + column + " = " + newValue + " WHERE " + where + " = " + value);
    }

    public void setString(@Nonnull String table, @Nonnull String column, @Nonnull String where, @Nonnull String value, @Nonnull String newValue) {
        this.executeQuery("UPDATE " + table + " SET " + column + " = " + newValue + " WHERE " + where + " = " + value);
    }

    public void setDouble(@Nonnull String table, @Nonnull String column, @Nonnull String where, @Nonnull String value, double newValue) {
        this.executeQuery("UPDATE " + table + " SET " + column + " = " + newValue + " WHERE " + where + " = " + value);
    }

    public void setFloat(@Nonnull String table, @Nonnull String column, @Nonnull String where, @Nonnull String value, float newValue) {
        this.executeQuery("UPDATE " + table + " SET " + column + " = " + newValue + " WHERE " + where + " = " + value);
    }

    public void setLong(@Nonnull String table, @Nonnull String column, @Nonnull String where, @Nonnull String value, long newValue) {
        this.executeQuery("UPDATE " + table + " SET " + column + " = " + newValue + " WHERE " + where + " = " + value);
    }

    public void setBoolean(@Nonnull String table, @Nonnull String column, @Nonnull String where, @Nonnull String value, boolean newValue) {
        this.executeQuery("UPDATE " + table + " SET " + column + " = " + newValue + " WHERE " + where + " = " + value);
    }

    public void getConnection(@Nonnull String host, @Nonnull String database, @Nonnull String username, @Nonnull String password) {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + host + "/" + database, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
