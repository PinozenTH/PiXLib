package com.pinont.piXLib.api.economy;

import com.pinont.piXLib.PiXLib;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

@Setter
@Getter
public class Balance {

    private Float balance;

    private NamespacedKey bal = new NamespacedKey(PiXLib.getPlugin(), "balance");

    public Balance(Player player) {
        if (!player.getPersistentDataContainer().has(bal, PersistentDataType.FLOAT)) {
            player.getPersistentDataContainer().set(bal, PersistentDataType.FLOAT, 0.0f);
        }
        this.balance = player.getPersistentDataContainer().get(bal, PersistentDataType.FLOAT);
    }

    public void set(Player player, float balance) {
        player.getPersistentDataContainer().set(bal, PersistentDataType.FLOAT, balance);
    }

    public void get(Player player) {
        player.getPersistentDataContainer().get(bal, PersistentDataType.FLOAT);
    }

}
