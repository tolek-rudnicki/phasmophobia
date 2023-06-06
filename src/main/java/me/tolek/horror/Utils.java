package me.tolek.horror;

import me.tolek.horror.ghost.GhostState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static Player hunter;
    //public static Entity e;
    public static Player player;
    public static ItemStack EMF_SCANNER;
    public static int state = 0; /* 0 = > 25, 1 = < 25 > 20, 2 = < 20 > 10, 3 = < 10 > 7, 4 = < 7*/
    public static boolean takePic = false;
    public static boolean elEngineer = false;
    public static boolean plumber = false;
    public static boolean allDone = false;
    public static boolean hasSaid = false;
    public static boolean detectable = false;
    public static boolean invisible = true;
    public static GhostState gstate = GhostState.SILENT;
    public static boolean setHunter = false;
    public static boolean setPlayer = false;

    public static void startMap() {
        //player = Bukkit.getPlayer("bear_with_me_XD");
        //hunter = Bukkit.getPlayer("bear_with_me_XD");
        //EMF_SCANNER = new ItemStack(Material.ACACIA_PLANKS);
        //updateMeta(EMF_SCANNER);
        //player.getInventory().addItem(EMF_SCANNER);
        //e = player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
        //e.addScoreboardTag("ghost");
        //LivingEntity le = (LivingEntity) e;
        //le.setAI(false);

        ItemStack item = new ItemStack(Material.OBSIDIAN);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Silent");
        item.setItemMeta(meta);
        hunter.getInventory().addItem(item);
        meta.setDisplayName("Not fully silent");
        item.setItemMeta(meta);
        hunter.getInventory().addItem(item);
        meta.setDisplayName("Hunt");
        item.setItemMeta(meta);
        hunter.getInventory().addItem(item);
        item = new ItemStack(Material.APPLE);
        meta.setDisplayName("Camera");
        item.setItemMeta(meta);
        player.getInventory().addItem(item);

        if (setPlayer && setHunter) {
            startSchedule();
        }
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
    public static double checkDistance(Player p1, Player p2) {
        Location l1 = p1.getLocation();
        Location l2 = p2.getLocation();

        return l1.distance(l2);
    }
    public static void startSchedule() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Horror"), () -> {

            double distance = checkDistance(player, Utils.hunter);
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
            if (detectable) {
                updateItem(state, player);
            } else {
                updateItem(4, player);
            }
            if (invisible) {
                hunter.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 30, 2));
            }
            if (elEngineer && takePic && plumber) {
                allDone = true;
            }
            if (!allDone) {
                if (hunter.getWorld().getBlockAt(10, 77, -64).getType() == Material.REDSTONE_BLOCK) {
                    elEngineer = true;
                } else {
                    elEngineer = false;
                }
                if (hunter.getWorld().getBlockAt(-50, 74, -43).getType() == Material.REDSTONE_BLOCK) {
                    plumber = true;
                } else {
                    plumber = false;
                }
            }
            if (!hasSaid && allDone) {
                player.sendTitle(ChatColor.DARK_RED + "RUN", ChatColor.DARK_RED + "The ghost is now hunting!");
                hunter.sendTitle(ChatColor.DARK_RED + "HUNT", "");
                hasSaid = true;
            }
        }, 0L, 20L);
    }

    public static void addItem(Inventory inv, int slot, ItemStack item) {
        inv.setItem(slot, item);
    }

    public static ItemStack createAndAddItem(Inventory inv, Material material, int amount, int slot, String displayName, String... lore) {
        ItemStack item;

        List<String> loreList = new ArrayList<String>();
        item = new ItemStack(material, amount);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);

        for (String s : lore) {
            loreList.add(s);
        }

        meta.setLore(loreList);
        item.setItemMeta(meta);

        inv.setItem(slot, item);
        return item;
    }

    public static void updateMeta(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("EMF Scanner");
        item.setItemMeta(meta);
    }

    public static void emf1() {
        EMF_SCANNER = new ItemStack(Material.ACACIA_BOAT);
        updateMeta(EMF_SCANNER);
    }

    public static void emf2() {
        EMF_SCANNER = new ItemStack(Material.ACACIA_CHEST_BOAT);
        updateMeta(EMF_SCANNER);
    }

    public static void emf3() {
        EMF_SCANNER = new ItemStack(Material.ACACIA_DOOR);
        updateMeta(EMF_SCANNER);
    }

    public static void emf4() {
        EMF_SCANNER = new ItemStack(Material.ACACIA_SIGN);
        updateMeta(EMF_SCANNER);
    }

    public static void emf5() {
        EMF_SCANNER = new ItemStack(Material.AMETHYST_SHARD);
        updateMeta(EMF_SCANNER);
    }

}
