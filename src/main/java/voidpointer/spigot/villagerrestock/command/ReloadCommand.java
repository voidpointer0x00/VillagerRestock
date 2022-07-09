package voidpointer.spigot.villagerrestock.command;

import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import voidpointer.spigot.villagerrestock.config.VillagerRestockConfig;

@RequiredArgsConstructor
public final class ReloadCommand implements CommandExecutor {
    public static final String COMMAND_NAME = "vr-reload";

    private final VillagerRestockConfig config;

    @Override public boolean onCommand(@NotNull final CommandSender sender, @NotNull final Command command, @NotNull final String label, final @NotNull String[] args) {
        config.reload();
        sender.sendMessage(ChatColor.GREEN + "VillagerRestock reloaded!");
        return true;
    }

    public void register(final JavaPlugin plugin) {
        final PluginCommand command = plugin.getCommand(COMMAND_NAME);
        assert command != null : "Plugin description file doesn't contain " + COMMAND_NAME + " command!";
        command.setExecutor(this);
    }
}
