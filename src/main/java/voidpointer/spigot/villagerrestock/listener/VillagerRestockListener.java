package voidpointer.spigot.villagerrestock.listener;

import com.destroystokyo.paper.entity.villager.Reputation;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerReplenishTradeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import voidpointer.spigot.villagerrestock.config.VillagerRestockConfig;

import java.util.Map;
import java.util.UUID;

import static com.destroystokyo.paper.entity.villager.ReputationType.MAJOR_NEGATIVE;
import static com.destroystokyo.paper.entity.villager.ReputationType.MAJOR_POSITIVE;
import static com.destroystokyo.paper.entity.villager.ReputationType.MINOR_NEGATIVE;
import static com.destroystokyo.paper.entity.villager.ReputationType.MINOR_POSITIVE;
import static com.destroystokyo.paper.entity.villager.ReputationType.TRADING;
import static org.bukkit.event.EventPriority.HIGHEST;

@RequiredArgsConstructor
public final class VillagerRestockListener implements Listener {
    private final VillagerRestockConfig config;

    @EventHandler(priority=HIGHEST)
    public void resetRestocksOnReplenish(final VillagerReplenishTradeEvent event) {
        if (event.isCancelled())
            return;
        if (!(event.getEntity() instanceof final Villager villager))
            return; // what kind of entity is that??
        if (config.resetRestock())
            villager.setRestocksToday(0);
        if (config.resetDemands())
            resetDemands(villager);
    }

    private void resetDemands(final Merchant merchant) {
        for (final MerchantRecipe recipe : merchant.getRecipes())
            if (recipe.getDemand() > 0)
                recipe.setDemand(0);
    }

    @EventHandler(priority=HIGHEST)
    public void clearReputationsOnRightClick(final PlayerInteractEntityEvent event) {
        if (event.isCancelled())
            return;
        if (!(event.getRightClicked() instanceof final Villager villager))
            return;

        final Map<UUID, Reputation> reputations = villager.getReputations();
        Reputation reputation = reputations.get(event.getPlayer().getUniqueId());
        if (reputation == null)
            reputations.put(event.getPlayer().getUniqueId(), reputation = new Reputation());

        if (config.clearNegativeReputations())
            clearNegativeReputations(reputation);
        if (config.clearTradeReputations())
            clearTradeReputations(reputation);
        if (config.clearCureReputations())
            clearCureReputations(reputation);

        // save new reputations if any was changed
        if (!reputation.equals(villager.getReputation(event.getPlayer().getUniqueId()))) {
            villager.clearReputations();
            villager.setReputations(reputations);
        }
    }

    private void clearNegativeReputations(final Reputation reputation) {
        if (reputation.getReputation(MAJOR_NEGATIVE) > 0)
            reputation.setReputation(MAJOR_NEGATIVE, 0);
        if (reputation.getReputation(MINOR_NEGATIVE) > 0)
            reputation.setReputation(MINOR_NEGATIVE, 0);
    }

    private void clearTradeReputations(final Reputation reputation) {
        if (reputation.getReputation(TRADING) > 0)
            reputation.setReputation(TRADING, 0);
    }

    private void clearCureReputations(final Reputation reputation) {
        if (reputation.getReputation(MINOR_POSITIVE) > 0)
            reputation.setReputation(MINOR_POSITIVE, 0);
        if (reputation.getReputation(MAJOR_POSITIVE) > 0)
            reputation.setReputation(MAJOR_POSITIVE, 0);
    }

    public void register(final JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
