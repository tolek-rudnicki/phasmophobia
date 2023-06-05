package me.tolek.horror.commands;

import me.tolek.horror.Utils;
import me.tolek.horror.guis.TasksGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Goals implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && label.equalsIgnoreCase("goals")) {
            //Utils.takePic = !Utils.takePic;
            TasksGUI.updateInv(TasksGUI.getInv());
            if (sender instanceof Player) {
                Player p = (Player) sender;
                p.openInventory(TasksGUI.getInv());

            }
        }
        return false;
    }
}
