package me.tolek.horror.items;

import me.tolek.horror.Utils;
import me.tolek.horror.guis.TasksGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;
import java.util.UUID;

public class Camera implements Listener {

    private HashMap<UUID, Integer> cooldown = new HashMap<>();
    private int taskId;
    final BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

    private void launchCooldown(Player p, int time) {
        if (time == 0) return;

        final UUID id = p.getUniqueId();

        if (!cooldown.containsKey(id)) {
            cooldown.put(id, time);
            taskId = scheduler.scheduleSyncRepeatingTask(Bukkit.getServer().getPluginManager().getPlugin("Horror"), () -> {
                int remain = cooldown.get(id);
                //p.sendMessage("Tick: " + cooldown.get(id));
                if (remain <= 0L) {
                    //p.sendMessage("Cooldown is done!, you can use it now!");
                    cooldown.remove(id);
                    scheduler.cancelTask(taskId);
                }
                --remain;
                cooldown.put(id, remain);
            }, 0L, 20L);
            return;
        }
        //cooldown.put(id, time);

    }

    private boolean checkIfPlayerHasCooldown(Player p) {
        return cooldown.containsKey(p.getUniqueId()) && cooldown.get(p.getUniqueId()) > 0;
    }

    public boolean entityInPath(Player e1, Player p) {
        Location eLoc = e1.getLocation();
        Location pLoc = p.getLocation();

        int o = pLoc.getBlockX() - eLoc.getBlockX();
        int a = pLoc.getBlockZ() - eLoc.getBlockZ();

        if (o == 0 || a == 0) {
            return false;
        }
        double yaw = Math.tan(o/a);


        if (pLoc.getYaw() - 40 < yaw && pLoc.getYaw() + 80 > yaw) {
            return true;
        }

        return false;
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event == null || event.getItem() == null || Utils.e == null) { return; }

        Material itemMat = event.getItem().getType();
        ItemStack item = event.getItem();
        String name = item.getItemMeta().getDisplayName();
        Player p = event.getPlayer();


        if (name.equalsIgnoreCase("Camera") && itemMat == Material.ACACIA_SAPLING) {
            if (!checkIfPlayerHasCooldown(p)) {
                launchCooldown(p, 5);

                //p.sendMessage("EIP: " + entityInPath(Utils.e, p));
                Utils.takePic = entityInPath(Utils.hunter, p);
                if (cooldown.get(p.getUniqueId()) <= 0) {
                    cooldown.remove(p.getUniqueId());
                    launchCooldown(p, 5);
                    //p.sendMessage("EIP: " + entityInPath(Utils.e, p));
                    Utils.takePic = entityInPath(Utils.hunter, p);
                }
            } else {
                launchCooldown(p, cooldown.get(p.getUniqueId()));
                int remaining = cooldown.get(p.getUniqueId());
                p.sendMessage(ChatColor.RED + "Wait " + remaining + " seconds before using this again!");
            }
            //p.sendMessage("EIP: " + entityInPath(Utils.e, p));
            TasksGUI.updateInv(TasksGUI.getInv());
            event.setCancelled(true);
        }
    }

}
