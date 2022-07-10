package voidpointer.spigot.villagerrestock.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public final class VillagerRestockConfig {
    private final Plugin plugin;
    private FileConfiguration fileConfiguration;

    public VillagerRestockConfig(final Plugin plugin, final FileConfiguration fileConfiguration) {
        this.plugin = plugin;
        this.fileConfiguration = fileConfiguration;
        saveDefaultIfNotExists();
    }

    public boolean resetRestock() {
        return fileConfiguration.getBoolean("settings.reset-restock", true);
    }

    public boolean resetDemands() {
        return fileConfiguration.getBoolean("settings.reset-demands", false);
    }

    public boolean clearNegativeReputations() {
        return fileConfiguration.getBoolean("settings.reputations.clear-negative", false);
    }

    public boolean clearTradeReputations() {
        return fileConfiguration.getBoolean("settings.reputations.clear-trade", false);
    }

    public boolean clearCureReputations() {
        return fileConfiguration.getBoolean("settings.reputations.clear-cure", false);
    }

    public void reload() {
        saveDefaultIfNotExists();
        plugin.reloadConfig();
        this.fileConfiguration = plugin.getConfig();
    }

    private void saveDefaultIfNotExists() {
        if (!new File(plugin.getDataFolder(), "config.yml").exists())
            plugin.saveDefaultConfig();
    }
}
