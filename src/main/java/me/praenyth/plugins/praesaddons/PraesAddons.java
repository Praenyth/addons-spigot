package me.praenyth.plugins.praesaddons;

import de.tr7zw.nbtapi.NBTContainer;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIConfig;
import me.praenyth.plugins.praesaddons.config.Config;
import me.praenyth.plugins.praesaddons.jokebook.JokeBookListener;
import me.praenyth.plugins.praesaddons.pipebomb.InventoryOpenListener;
import me.praenyth.plugins.praesaddons.pipebomb.PipebombRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class PraesAddons extends JavaPlugin {

    @Override
    public void onEnable() {

        Config.loadConfig(this);

        getServer().getPluginManager().registerEvents(new JokeBookListener(this), this);
        getServer().getPluginManager().registerEvents(new InventoryOpenListener(this), this);

        CommandAPI.onLoad(new CommandAPIConfig().silentLogs(true).initializeNBTAPI(NBTContainer.class, NBTContainer::new));
        Config.reloadConfigCommandRegister(this);

        PipebombRecipe.registerPipebombRecipe(this);

        getLogger().info("Plugin loaded. Enjoy the stupidity the plugin breeds!");

    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin stopped! thanks for passing by.");
    }
}
