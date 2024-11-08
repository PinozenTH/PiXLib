package com.pinont.piXLib.api.economy;

import com.pinont.piXLib.api.data.SQLite;
import lombok.Getter;

import java.util.HashMap;
import java.util.UUID;

public class Balance extends Economy {

    SQLite balance = new SQLite("balance");

    private String currency;
    @Getter
    private HashMap<UUID, Double> account = new HashMap<>();
    private UUID uuid;

    public double getDefaultBalance() {
        return 100.0;
    }

    @Override
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    @Override
    public void setUser(UUID user) {
        this.uuid = user;
    }

    public UUID getUser() {
        return uuid;
    }

    @Override
    public void setAmount(double amount) {
        account.put(uuid, amount);
    }

    @Override
    public Double getAmount() {
        return account.get(uuid);
    }

    @Override
    public void setAccount(HashMap<UUID, Double> account) {
        balance.createTable("balance", "player TEXT", "balance DOUBLE");
    }

    @Override
    public void saveAccount(HashMap<UUID, Double> account) {
        balance.insert("balance", "player, balance", uuid + ", " + account.get(uuid));
    }

    public HashMap<UUID, Double> getAccount(UUID uuid) {
        HashMap<UUID, Double> account = new HashMap<>();
        account.put(uuid, balance.getDouble(String.valueOf(uuid), "balance"));
        return account;
    }

    @Override
    public double getBalance(UUID player) {
        return account.get(player);
    }

    @Override
    public void setBalance(double amount) {
        account.put(uuid, amount);
    }

    @Override
    public void addBalance(double amount) {
        account.put(uuid, account.get(uuid) + amount);

    }
}
