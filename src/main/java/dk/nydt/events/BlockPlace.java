package dk.nydt.events;

import dk.nydt.OreGen;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    OreGen plugin;

    public BlockPlace(OreGen plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        UUID uuid = event.getPlayer().getUniqueId();


    }
}
