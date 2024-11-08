package com.pinont.piXLib.api.economy;

import org.bukkit.entity.Player;

public class User extends Balance {

    public void setUser(Player player) {
        super.setUser(player.getUniqueId());
    }

    public void setBalance(Player player, double amount) {
        super.setUser(player.getUniqueId());
        super.setAmount(amount);
    }

    public double getBalance(Player player) {
        super.setUser(player.getUniqueId());
        return super.getAmount();
    }

    public void addBalance(Player player, double amount) {
        super.setUser(player.getUniqueId());
        super.addBalance(amount);
    }

    public void saveBalance(Player player) {
        super.setUser(player.getUniqueId());
        super.saveAccount(super.getAccount());
    }

}
