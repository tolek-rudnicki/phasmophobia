package me.tolek.horror.commands;

import me.tolek.horror.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Game implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //sender.sendMessage(args[1] + " " + args[0]);
        //if (args.length > 1) {
            //sender.sendMessage(">1, " + args);
        if (label.equalsIgnoreCase("game")) {
            if (args[0].equalsIgnoreCase("start")) {
                if (Utils.setHunter && Utils.setPlayer) {
                    Utils.startMap();
                    sender.sendMessage(ChatColor.DARK_RED + "You're in trouble now.");
                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "Set the required parameters first!");
                }
            } else if (args[0].equalsIgnoreCase("setplayer") && args.length > 1) {
                Utils.player = Bukkit.getPlayer(args[1]);
                sender.sendMessage(ChatColor.GREEN + "Player set to " + args[1]);
                Utils.setPlayer = true;
            } else if (args[0].equalsIgnoreCase("sethunter") && args.length > 1) {
                Utils.hunter = Bukkit.getPlayer(args[1]);
                sender.sendMessage(ChatColor.GREEN + "Hunter set to " + args[1]);
                Utils.setHunter = true;
            }
        }
        //}
        return false;
    }
}
