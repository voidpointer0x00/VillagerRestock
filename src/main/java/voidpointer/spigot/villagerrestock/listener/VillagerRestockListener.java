package voidpointer.spigot.villagerrestock.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerReplenishTradeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import voidpointer.spigot.villagerrestock.config.VillagerRestockConfig;

@RequiredArgsConstructor
public final class VillagerRestockListener implements Listener {
    private final VillagerRestockConfig config;

    @EventHandler public void resetRestocksOnReplenish(final VillagerReplenishTradeEvent event) {
        if (!(event.getEntity() instanceof final Villager villager))
            return; // what kind of entity is that??
        if (config.resetRestock())
            villager.setRestocksToday(0);
    }

    @EventHandler public void resetDemandsAndReputationsOnRightClick(final PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof final Villager villager))
            return;
        if (config.clearReputations())
            villager.clearReputations();
        if (config.resetDemands())
            resetDemands(villager);
    }

    private void resetDemands(final Villager villager) {
        for (final MerchantRecipe recipe : villager.getRecipes())
            if (recipe.getDemand() > 0)
                recipe.setDemand(0);
    }

    public void register(final JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
