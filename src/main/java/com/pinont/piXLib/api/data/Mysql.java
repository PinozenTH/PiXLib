package com.pinont.piXLib.api.data;

import com.pinont.piXLib.PiXLib;
import com.pinont.piXLib.api.utils.enums.LoggerType;
import com.pinont.piXLib.api.utils.texts.Message;
import lombok.SneakyThrows;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public record Mysql(String host, String database, String username, String password) {

    private static List<String> statements;
    private static PreparedStatement preparedStatement;

    @SneakyThrows
    public Connection getConnection() {
        return DriverManager.getConnection("jdbc:mysql://" + host + "/" + database + "?useSSL=true", username, password);
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    private void setPreparedStatement(PreparedStatement preparedStatement) {
        Mysql.preparedStatement = preparedStatement;
    }

    public Mysql setStatements(List<String> statements) {
        Mysql.statements = statements;
        return this;
    }

    public Mysql addStatement(String statement) {
        statements.add(statement);
        return this;
    }

    public void executeStatements() {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            for (var s : statements) {
                statement.execute(s);
            }
            statement.close();
        } catch (Exception e) {
            new Message(e.getMessage()).setLoggerType(LoggerType.SEVERE).send();
        }
    }

    public void load(long minuteInterval) {
        new BukkitRunnable() {
            @Override
            public void run() {
                getConnection();
            }
        }.runTaskTimer(PiXLib.getPlugin(), 0L, minuteInterval * 60 * 20);
    }

}
