package dk.nydt.events;

import dk.nydt.OreGen;
import dk.nydt.gens.RegisterGen;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
        Location location = event.getBlock().getLocation();
        Material block = event.getBlock().getType();
        Bukkit.broadcastMessage("block " + block);
        RegisterGen.registerGen(location, block, player);

    }
}
