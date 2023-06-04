package me.tolek.horror;

import me.tolek.horror.commands.StartGame;
import me.tolek.horror.items.Camera;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Horror extends JavaPlugin {

    @Override
    public void onEnable() {
        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage(ChatColor.DARK_RED + "Plugin Loaded");
        console.sendMessage(ChatColor.DARK_RED + "Boy you are in some trouble");
        //Utils.startMap();

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new Camera(), this);

        this.getCommand("startgame").setExecutor(new StartGame());
    }

    @Override
    public void onDisable() {
        ConsoleCommandSender console = getServer().getConsoleSender();
        console.sendMessage(ChatColor.DARK_RED + "Plugin Unloaded");
        console.sendMessage(ChatColor.DARK_RED + "Someone got scared");
    }
}
