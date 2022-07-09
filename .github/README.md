<img src="https://user-images.githubusercontent.com/43143315/178108584-abd72f88-0efc-44b5-97bd-be206ce3738d.png" align=right />

# VillagerRestock

Villager restock is an optimized and configurable alternative to
[Villager Restocking][VRDpUrl] and [Infinite Trades][ITDpUrl] datapacks.

[VRDpUrl]: https://www.planetminecraft.com/data-pack/villager-restocking-v1-0/
[ITDpUrl]: https://www.planetminecraft.com/data-pack/no-trading-limits-infinite-trades-1-18x/

## The Problem

The Infinite Trades datapack works fine except it just doesn't on MC 1.19.

On the other hand, the Villager Restocking datapack does work on MC 1.19,
but it comes with a huge performance overhead — it runs **EVERY TICK**, so
it modifies NBT tags using commands on all villagers in the world causing lag
and MSPT growth _(1MSPT for a group of 3 villagers on my CPU)_.

## The Solution

That being said, I developed this plugin for my personal use and decided to
just simply share it if someone else needs it too.

The plugin is aimed to run on vanilla/semi-vanilla servers to remove annoying
restocking cooldown mechanic _([RestocksToday][EntityDataUrl])_, optionally 
reset demands _([the more you buy the higher the price][Supply&DemandUrl])_
and clear [players reputations][PopularityUrl].

[EntityDataUrl]: [https://minecraft.fandom.com/wiki/Villager#Entity_data]
[Supply&DemandUrl]: https://minecraft.fandom.com/wiki/Villager#Supply_and_demand
[PopularityUrl]: https://minecraft.fandom.com/wiki/Villager#Popularity

## Configuration

```yaml
# I'd only use reset-restock property to keep the game close
#   to vanilla behaviour and remove this annoying 10m cooldown
#   between restocks. Though, I still implemented reputations and
#   demands resets just in case someone reckons that it should also
#   be fixed on their servers.
settings:
  # Resets villager's RestocksToday NBT tag so that
  #   10m restock cooldown doesn't start.
  reset-restock: true
  # Resets the demand on merchant recipes, so they don't
  #   higher the price anymore. Doesn't affect negative
  #   demands (i.e. Hero of the Village effect).
  reset-demands: false
  # Clears all reputations on villager interaction.
  #   (i.e. you hit the villager or kill an iron golem)
  clear-reputations: false

```

## Requirements

* Java 17
* Paper 1.19

The plugin works **exclusively** on Paper and it's forks _(if it's compatible)_,
I'm **not** going to port it to Spigot. Right now it's built only for 1.19MC
using Java 17 features, but I'm planning to build it for latest minors of
1.14-1.18 MC versions.

## Build

Just `mvn package` and get the VillagerRestock jar from `/target` directory.
