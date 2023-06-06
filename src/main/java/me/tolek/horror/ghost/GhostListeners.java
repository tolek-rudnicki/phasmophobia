package me.tolek.horror.ghost;

import me.tolek.horror.Utils;
import me.tolek.horror.guis.TasksGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class GhostListeners implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event == null || event.getItem() == null) { return; }

        Player p = event.getPlayer();
        Material mat = event.getItem().getType();
        String itemName = event.getItem().getItemMeta().getDisplayName();
        TasksGUI.updateInv(TasksGUI.getInv());

        if (p == Utils.hunter) {

            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (mat == Material.OBSIDIAN && itemName.equalsIgnoreCase("Silent")) {
                    Utils.gstate = GhostState.SILENT;
                    p.sendMessage(ChatColor.DARK_RED + "SILET");
                } else if (mat == Material.OBSIDIAN && itemName.equalsIgnoreCase("Not fully silent")) {
                    Utils.gstate = GhostState.NFSILENT;
                    p.sendMessage(ChatColor.DARK_RED + "Not Fully Siler");
                } else if (mat == Material.OBSIDIAN && itemName.equalsIgnoreCase("Hunt")) {
                    Utils.gstate = GhostState.HUNT;
                    p.sendMessage(ChatColor.DARK_RED + "HUNTING");
                }
            }
            if (Utils.gstate == GhostState.SILENT) {
                Utils.detectable = false;
                Utils.invisible = true;
                event.setCancelled(true);
            } else if (Utils.gstate == GhostState.NFSILENT) {
                Utils.detectable = true;
                Utils.invisible = true;
            } else if (Utils.gstate == GhostState.HUNT) {
                Utils.detectable = true;
                Utils.invisible = false;
            }
        }
    }

}
