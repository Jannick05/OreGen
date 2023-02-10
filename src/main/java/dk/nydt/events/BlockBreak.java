package dk.nydt.events;

import dk.nydt.OreGen;
import dk.nydt.gens.RemoveGen;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    OreGen plugin;

    public BlockBreak(OreGen plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Bukkit.broadcastMessage("triggered removeGen()");
        RemoveGen.removeGen(event.getBlock().getLocation(), event.getBlock().getType(), player);
    }
}
