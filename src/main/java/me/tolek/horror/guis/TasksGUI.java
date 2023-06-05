package me.tolek.horror.guis;

import me.tolek.horror.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TasksGUI {

    private static Inventory inv;
    public static String invName;
    public static int numSlots = 9;
    public static ItemStack g1;
    public static ItemStack g2;
    public static ItemStack filler;

    public static void Initialize() {
        invName = ChatColor.DARK_GRAY + "Heart of the Mountain";

        inv = Bukkit.createInventory(null, numSlots, invName);

        g1 = Utils.createAndAddItem(inv, Material.RED_STAINED_GLASS_PANE, 1, 0, "Scary Pictures", ChatColor.RESET + "" + ChatColor.GRAY + "You have to take a picture of the ghost.");
        filler = Utils.createAndAddItem(inv, Material.GRAY_STAINED_GLASS_PANE, 1, 1, "", "");
        g2 = Utils.createAndAddItem(inv, Material.RED_STAINED_GLASS_PANE, 1, 2, "Electrical Engineer", ChatColor.RESET + "" + ChatColor.GRAY + "Enable the power");
    }

    public static Inventory getInv() {
        return inv;
    }

    public static void updateInv(Inventory invs) {
        if (Utils.takePic) {
            invs.remove(g1);
            g1 = Utils.createAndAddItem(invs, Material.LIME_STAINED_GLASS_PANE, 1, 0, "Scary Pictures", ChatColor.RESET + "" + ChatColor.GRAY + "You have to take a picture of the ghost.");
        } else {
            invs.remove(g1);
            g1 = Utils.createAndAddItem(inv, Material.RED_STAINED_GLASS_PANE, 1, 0, "Scary Pictures", ChatColor.RESET + "" + ChatColor.GRAY + "You have to take a picture of the ghost.");

        }
    }

}
