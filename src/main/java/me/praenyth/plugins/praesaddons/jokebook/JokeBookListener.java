package me.praenyth.plugins.praesaddons.jokebook;

import me.praenyth.plugins.praesaddons.PraesAddons;
import org.bukkit.Material;
import org.bukkit.SoundCategory;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class JokeBookListener implements Listener {

    private final PraesAddons plugin;

    public JokeBookListener(PraesAddons plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJokeBookOpen(PlayerInteractEvent event) {
        try {

            FileConfiguration config = plugin.getConfig();
            Player player = event.getPlayer();
            ItemStack mainHand = player.getInventory().getItemInMainHand();
            ItemStack offHand = player.getInventory().getItemInOffHand();

            if (config.getBoolean("enable-joke-book", false)) {
                if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (mainHand.getType().equals(Material.BOOK) || offHand.getType().equals(Material.BOOK)) {
                        if (mainHand.getItemMeta().getDisplayName().equals("Joke Book") || offHand.getItemMeta().getDisplayName().equals("Joke Book")) {

                            player.getWorld().playSound(
                                    player.getLocation(),
                                    config.getString("joke-book-sound", "prae.danielsmp.jokebook"),
                                    SoundCategory.MASTER,
                                    1f,
                                    1f
                            );

                        }
                    }
                }
            }

        } catch (NullPointerException exception) {
            // do nothing
        }
    }

}
