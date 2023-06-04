package me.tolek.horror.items;

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

        }
    }

}
