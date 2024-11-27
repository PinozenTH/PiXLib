package com.pinont.experiences.api.interactable;

import com.pinont.experiences.api.builders.ItemBuilder;
import com.pinont.experiences.api.utils.texts.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractionListener implements Listener {

    // TODO: Implement the onInteract validation method to handle the PlayerInteractEvent

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!player.getInventory().getItemInMainHand().getType().isAir()) {
            ItemStack item = player.getInventory().getItemInMainHand();
            if (new ItemBuilder(item).isInteractable()) {
                new Message("interactable").sendConsole();
                Action action = event.getAction();
                for (Interaction interaction : new ItemBuilder(item).getInteractions()) {
                    if (interaction.getInteractions().contains(InteractionType.getInteractionType(action))) {
                        interaction.onInteract(player);
                    }
                }
            }
        }
    }

}
