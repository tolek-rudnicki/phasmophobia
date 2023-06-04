package me.tolek.horror.items;

import me.tolek.horror.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Camera implements Listener {

    public boolean entityInPath(Entity e1, Player p) {
        Location eLoc = e1.getLocation();
        Location pLoc = p.getLocation();

        int o = pLoc.getBlockX() - eLoc.getBlockX();
        int a = pLoc.getBlockZ() - eLoc.getBlockZ();

        double yaw = Math.tan(o/a);

        if (pLoc.getYaw() + 60 < yaw && pLoc.getYaw() - 60 > yaw) {
            return true;
        }

        return false;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event == null || event.getItem() == null) { return; }

        Material itemMat = event.getItem().getType();
        ItemStack item = event.getItem();
        String name = item.getItemMeta().getDisplayName();
        Player p = event.getPlayer();


        if (name.equalsIgnoreCase("Camera")) {
            p.sendMessage("EIP: " + entityInPath(Utils.e, p));
            event.setCancelled(true);
        }
    }

}
