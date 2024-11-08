package com.pinont.piXLib.api.economy;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

@Setter
@Getter
public abstract class Economy {

    public abstract void setCurrency(String currency);

    public abstract String getCurrency();

    public abstract void setUser(UUID user);

    public abstract UUID getUser();

    public abstract void setAmount(double amount);

    public abstract Double getAmount();

    public abstract void setAccount(HashMap<UUID, Double> account);

    public abstract void saveAccount(HashMap<UUID, Double> account);

    public abstract HashMap<UUID, Double> getAccount(UUID player);

    public abstract double getBalance(UUID account);

    public abstract void setBalance(double amount);

    public abstract void addBalance(double amount);
}
