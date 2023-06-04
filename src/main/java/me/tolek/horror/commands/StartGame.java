package me.tolek.horror.commands;

import me.tolek.horror.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartGame implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("startgame")) {
            Utils.startMap();
            sender.sendMessage(ChatColor.DARK_RED + "You're in trouble now.");
        }
        return false;
    }
}
