package me.praenyth.plugins.praesaddons.config;

import dev.jorel.commandapi.CommandAPICommand;
import me.praenyth.plugins.praesaddons.PraesAddons;
import org.bukkit.ChatColor;

public class Config {

    public static void loadConfig(PraesAddons plugin) {
        plugin.getConfig().options().copyDefaults();
        plugin.saveDefaultConfig();

        plugin.getLogger().info("Loaded config!");
    }

    public static void reloadConfigCommandRegister(PraesAddons plugin) {

        new CommandAPICommand("pareload")
                .executes((sender, args) -> {
                    sender.sendMessage(ChatColor.GREEN+"[Prae's Addons] "+ChatColor.YELLOW+"Successfully reloaded the config!");
                    plugin.reloadConfig();
                }).register();

    }

}
