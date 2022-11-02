package me.praenyth.plugins.praesaddons.pipebomb;

import de.tr7zw.nbtapi.NBTContainer;
import de.tr7zw.nbtapi.NBTItem;
import me.praenyth.plugins.praesaddons.PraesAddons;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class InventoryOpenListener implements Listener {

    private final PraesAddons plugin;

    public InventoryOpenListener(PraesAddons plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {

        FileConfiguration config = plugin.getConfig();

        Player player = (Player) event.getPlayer();
        float explosionPower = 0f;

        try {

            if (config.getBoolean("enable-pipebombs", false)) {
                if (event.getInventory().contains(Material.TNT)) {
                    for (ItemStack item : event.getInventory().getContents()) {
                        if (item != null) {
                            if (item.hasItemMeta()) {

                                NBTContainer itemNBT = NBTItem.convertItemtoNBT(item);

                                if (itemNBT.getCompound("tag").getFloat("pipebomb") == 1f) {

                                    explosionPower += item.getAmount();
                                    event.getInventory().removeItem(item);

                                }

                            }
                        }
                    }
                }

                Block targetBlock = player.getTargetBlockExact(5);
                if (explosionPower > config.getDouble("max-explosion-power", 20)) {

                    player.breakBlock(targetBlock);
                    for (Entity entity : targetBlock.getWorld().getNearbyEntities(targetBlock.getLocation(), 5, 5, 5)) {
                        entity.setVelocity(entity.getLocation().toVector().subtract(targetBlock.getLocation().toVector()));
                    }
                    player.getWorld().createExplosion(
                            targetBlock.getLocation(),
                            (float) config.getDouble("max-explosion-power", 20),
                            config.getBoolean("pipebombs-spawn-fire", false),
                            config.getBoolean("destroys-environment", false)
                    );

                } else if (explosionPower > 0) {

                    player.breakBlock(targetBlock);
                    for (Entity entity : targetBlock.getWorld().getNearbyEntities(targetBlock.getLocation(), 5, 5, 5)) {
                        entity.setVelocity(entity.getLocation().toVector().subtract(targetBlock.getLocation().toVector()));
                    }
                    player.getWorld().createExplosion(
                            targetBlock.getLocation(),
                            (float) (explosionPower * config.getDouble("explosion-power-per-bomb", 0.5)),
                            config.getBoolean("pipebombs-spawn-fire", false),
                            config.getBoolean("destroys-environment", false)
                    );

                }
            }
        } catch (NullPointerException ignored) {
            // do nothing if error occurs
        }

    }
}
