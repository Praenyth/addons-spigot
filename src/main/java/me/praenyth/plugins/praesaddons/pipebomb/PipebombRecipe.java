package me.praenyth.plugins.praesaddons.pipebomb;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import me.praenyth.plugins.praesaddons.PraesAddons;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class PipebombRecipe {

    public static void registerPipebombRecipe(PraesAddons plugin) {
        ItemStack pipebomb = new ItemStack(Material.TNT, 1);
        ItemMeta pipebombMeta = pipebomb.getItemMeta();
        pipebombMeta.setDisplayName(ChatColor.of("#f5424e")+"Pipebomb");
        pipebomb.setItemMeta(pipebombMeta);
        NBTCompound pipebombData = NBTItem.convertItemtoNBT(pipebomb);
        pipebombData.getCompound("tag").addCompound("pipebomb");
        pipebombData.getCompound("tag").setFloat("pipebomb", 1f);
        plugin.getLogger().info(pipebombData.toString());
        pipebomb = NBTItem.convertNBTtoItem(pipebombData);

        try {

            ShapedRecipe pipebombRecipe = new ShapedRecipe(new NamespacedKey(plugin, "pipebomb"), pipebomb);
            pipebombRecipe.shape(
                    "XXX",
                    "XOX",
                    "XXX"
            );
            pipebombRecipe.setIngredient('X', Material.TNT);
            pipebombRecipe.setIngredient('O', Material.IRON_INGOT);

            Bukkit.addRecipe(pipebombRecipe);

            plugin.getLogger().info("Registered the pipebomb recipe!");

        } catch (IllegalStateException e) {

            plugin.getLogger().info("Be careful when reloading this plugin! Unexpected issues may occur.");

        }

    }

}
