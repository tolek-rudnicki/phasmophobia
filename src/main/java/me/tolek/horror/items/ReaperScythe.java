package me.tolek.horror.items;

import me.tolek.horror.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ReaperScythe implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event == null || event.getItem() == null || Utils.player == null || Utils.hunter == null) { return; }

        String itemName = event.getItem().getItemMeta().getDisplayName();
        Player p = event.getPlayer();
        Action action = event.getAction();

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (itemName.equalsIgnoreCase("Reaper Scythe") && event.getItem().getType() == Material.DIAMOND_HOE && Utils.setHunter && Utils.setPlayer) {
                Utils.startMap();
                p.sendMessage(ChatColor.DARK_RED + "You're in trouble now.");
            }
        }
    }

}
