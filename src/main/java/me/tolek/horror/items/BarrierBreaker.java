package me.tolek.horror.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BarrierBreaker implements Listener {

    @EventHandler
    public void interactEvent(PlayerInteractEvent event) {
        if (event == null || event.getItem() == null) { return; }

        Player p = event.getPlayer();
        String itemName = event.getItem().getItemMeta().getDisplayName();
        Action a = event.getAction();

        if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
            if (itemName.equalsIgnoreCase("Barrier Breaker")) {
                p.getWorld().getBlockAt(-24, 74, -34).setType(Material.STONE_BUTTON);
            }
        }
    }

}
