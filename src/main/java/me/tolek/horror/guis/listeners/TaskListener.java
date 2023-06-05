package me.tolek.horror.guis.listeners;

import me.tolek.horror.guis.TasksGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class TaskListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory clicked = event.getInventory();
        Player p = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        if (clicked == TasksGUI.getInv()) { event.setCancelled(true); }
    }

}
