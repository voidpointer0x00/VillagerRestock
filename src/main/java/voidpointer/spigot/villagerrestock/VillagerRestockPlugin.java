package voidpointer.spigot.villagerrestock;

import org.bukkit.plugin.java.JavaPlugin;
import voidpointer.spigot.villagerrestock.config.VillagerRestockConfig;

import static org.bukkit.ChatColor.GREEN;

public final class VillagerRestockPlugin extends JavaPlugin {
    private VillagerRestockConfig config;

    @Override public void onLoad() {
        getLogger().info(GREEN + "The plugin loaded");
        config = new VillagerRestockConfig(this, getConfig());
    }

    @Override public void onEnable() {

    }

    @Override public void onDisable() {

    }
}
