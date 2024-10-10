package com.pinont.piXLib.api.scoreboard;

import com.pinont.piXLib.PiXPlugin;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.*;

import javax.annotation.Nonnull;
import java.util.Objects;

public class Board {

    private final Scoreboard scoreboard;
    private final String name;
    private final Criteria criteria;
    private final String DisplayName;
    private DisplaySlot displaySlot;
    private RenderType renderType;
    private Player player;
    private Player[] players;
    private final Objective objective;
    private Score score;

    public Board(String name, Criteria criteria, String DisplayName) {
        this.name = name;
        this.criteria = criteria;
        this.DisplayName = DisplayName;
        Plugin main = PiXPlugin.getPlugin();
        this.scoreboard = Objects.requireNonNull(main.getServer().getScoreboardManager()).getMainScoreboard();
        objective = scoreboard.registerNewObjective(name, criteria, DisplayName);
        Team boardTeam = scoreboard.registerNewTeam(name);
        boardTeam.addEntry(DisplayName + " ");
        boardTeam.setSuffix("");
        boardTeam.setPrefix("");
        getScore(DisplayName + " ").setScore(0);
    }

    public Board(@Nonnull String name) {
        this.scoreboard = Objects.requireNonNull(PiXPlugin.getPlugin().getServer().getScoreboardManager()).getMainScoreboard().getObjective(name).getScoreboard();
        this.name = name;
        this.criteria = Criteria.create(scoreboard.getObjective(name).getCriteria());
        this.DisplayName = scoreboard.getObjective(name).getDisplayName();
        this.objective = scoreboard.getObjective(name);
    }

    public Board setDisplaySlot(DisplaySlot displaySlot) {
        this.displaySlot = displaySlot;
        objective.setDisplaySlot(displaySlot);
        return this;
    }

    public Board setRenderType(RenderType renderType) {
        this.renderType = renderType;
        objective.setRenderType(renderType);
        return this;
    }

    public Board setPlayer(Player player) {
        this.player = player;
        this.score = objective.getScore(player.getName());
        return this;
    }

    public Board setScorePlayer(Player player) {
        this.player = player;
        player.setScoreboard(scoreboard);
        return this;
    }

    public Board setScorePlayers(Player[] players) {
        this.players = players;
        for (Player player : players) {
            player.setScoreboard(scoreboard);
        }
        return this;
    }

    public void setScore(int score) {
        this.score.setScore(score);
    }

    public void setScore(double score) {
        if (((int) score) < score) {
            this.score.setScore((int) score + 1);
            return;
        }
        this.score.setScore((int) score);
    }

    public Board getScore(String s) {
        this.score = objective.getScore(s);
        return this;
    }

    public DisplaySlot getDisplaySlot() {
        return displaySlot;
    }

    public RenderType getRenderType() {
        return renderType;
    }

    public Player getPlayer() {
        return player;
    }

    public Player[] getPlayers() {
        return players;
    }

    public String getName() {
        return name;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }
}
