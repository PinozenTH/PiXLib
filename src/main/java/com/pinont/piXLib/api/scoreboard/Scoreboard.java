package com.pinont.piXLib.api.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Scoreboard {

    private String name;
    private Criteria criteria;
    private String DisplayName;
    private DisplaySlot displaySlot;
    private RenderType renderType;
    private Player player;
    private Player[] players;
    private Objective objective;
    private Score score;

    private final ScoreboardManager manager = Bukkit.getScoreboardManager();

    public Scoreboard objectives(Objective objective) {
        this.objective = objective;
        return this;
    }

    public Scoreboard setCriteria(Criteria criteria) {
        this.criteria = criteria;
        return this;
    }

    public Scoreboard setDisplayName(String DisplayName) {
        this.DisplayName = DisplayName;
        objective.setDisplayName(DisplayName);
        return this;
    }

    public Scoreboard setDisplaySlot(DisplaySlot displaySlot) {
        this.displaySlot = displaySlot;
        objective.setDisplaySlot(displaySlot);
        return this;
    }

    public Scoreboard setRenderType(RenderType renderType) {
        this.renderType = renderType;
        objective.setRenderType(renderType);
        return this;
    }

    public void add() {
        assert manager != null;
        org.bukkit.scoreboard.Scoreboard board = manager.getNewScoreboard();
        this.objective = board.registerNewObjective(name, criteria, DisplayName);
    }

    public List<String> list() {
        return new ArrayList<>(Objects.requireNonNull(manager).getMainScoreboard().getEntries());
    }

    public void remove() {
        assert manager != null;
        Objects.requireNonNull(manager.getMainScoreboard().getObjective(name)).unregister();
    }

    public DisplaySlot getDisplaySlot() {
        return displaySlot;
    }

    public RenderType getRenderType() {
        return renderType;
    }
}
