package voidpointer.spigot.villagerrestock;

import org.bukkit.plugin.java.JavaPlugin;
import voidpointer.spigot.villagerrestock.command.ReloadCommand;
import voidpointer.spigot.villagerrestock.config.VillagerRestockConfig;
import voidpointer.spigot.villagerrestock.listener.VillagerRestockListener;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

public final class VillagerRestockPlugin extends JavaPlugin {
    private VillagerRestockConfig config;

    @Override public void onLoad() {
        getLogger().info(GREEN + "The plugin loaded");
        config = new VillagerRestockConfig(this, getConfig());
    }

    @Override public void onEnable() {
        new VillagerRestockListener(config).register(this);
        new ReloadCommand(config).register(this);
        getLogger().info(GREEN + "The plugin enabled");
    }

    @Override public void onDisable() {
        getLogger().info(RED + "The plugin disabled");
    }
}
