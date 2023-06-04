package me.tolek.horror;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {

    public static Player hunter;
    public static Entity e;
    public static Player player;
    public static ItemStack EMF_SCANNER;
    public static int state = 0; /* 0 = > 25, 1 = < 25 > 20, 2 = < 20 > 10, 3 = < 10 > 7, 4 = < 7*/

    public static void startMap() {
        player = Bukkit.getPlayer("bear_with_me_XD");
        //EMF_SCANNER = new ItemStack(Material.ACACIA_PLANKS);
        //updateMeta(EMF_SCANNER);
        //player.getInventory().addItem(EMF_SCANNER);
        e = player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
        startSchedule();
    }

    public static void updateItem(int state, Player p) {
        if (state == 0) {
            p.getInventory().remove(Utils.EMF_SCANNER);
            Utils.emf1();
            p.getInventory().addItem(Utils.EMF_SCANNER);
        } else if (state == 1) {
            p.getInventory().remove(Utils.EMF_SCANNER);
            Utils.emf2();
            p.getInventory().addItem(Utils.EMF_SCANNER);
        } else if (state == 2) {
            p.getInventory().remove(Utils.EMF_SCANNER);
            Utils.emf3();
            p.getInventory().addItem(Utils.EMF_SCANNER);
        } else if (state == 3) {
            p.getInventory().remove(Utils.EMF_SCANNER);
            Utils.emf4();
            p.getInventory().addItem(Utils.EMF_SCANNER);
        } else if (state == 4) {
            p.getInventory().remove(Utils.EMF_SCANNER);
            Utils.emf5();
            p.getInventory().addItem(Utils.EMF_SCANNER);
        }
    }
    public static double checkDistance(Player p1, Entity p2) {
        Location l1 = p1.getLocation();
        Location l2 = p2.getLocation();

        return l1.distance(l2);
    }
    public static void startSchedule() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Horror"), () -> {

            double distance = checkDistance(player, Utils.e);
            if (distance > 25) {
                state = 0;
            } else if (distance < 25 && distance > 20) {
                state = 1;
            } else if (distance < 20 && distance > 10) {
                state = 2;
            } else if (distance < 10 && distance > 7) {
                state = 3;
            } else if (distance < 7) {
                state = 4;
            }
            updateItem(state, player);
        }, 0L, 20L);
    }

    public static void updateMeta(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("EMF Scanner");
        item.setItemMeta(meta);
    }

    public static void emf1() {
        EMF_SCANNER = new ItemStack(Material.ACACIA_PLANKS);
        updateMeta(EMF_SCANNER);
    }

    public static void emf2() {
        EMF_SCANNER = new ItemStack(Material.STRIPPED_ACACIA_LOG);
        updateMeta(EMF_SCANNER);
    }

    public static void emf3() {
        EMF_SCANNER = new ItemStack(Material.STRIPPED_ACACIA_WOOD);
        updateMeta(EMF_SCANNER);
    }

    public static void emf4() {
        EMF_SCANNER = new ItemStack(Material.ACACIA_STAIRS);
        updateMeta(EMF_SCANNER);
    }

    public static void emf5() {
        EMF_SCANNER = new ItemStack(Material.ACACIA_LEAVES);
        updateMeta(EMF_SCANNER);
    }

}
