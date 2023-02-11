package dk.nydt.events;

import dk.nydt.OreGen;
import dk.nydt.gens.RemoveGen;
import dk.nydt.utils.Chat;
import dk.nydt.utils.GetGen;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {

    OreGen plugin;

    public PlayerInteract(OreGen plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Bukkit.broadcastMessage("Block break Event");

        if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (Boolean.TRUE.equals(GetGen.checkBlock(event.getClickedBlock().getLocation(), player))) {
                Bukkit.broadcastMessage("Block is a Generator");
                if (player.isSneaking()) {
                    Bukkit.broadcastMessage("Player is Sneaking");
                    RemoveGen.removeGen(event.getClickedBlock().getLocation(), event.getClickedBlock().getType(), player);
                    player.sendMessage(Chat.colored(OreGen.config.getConfig().getString("GenRemoveBesked", "&8&L[ &e&lORE &8&L] &cDu fjernede en gen.")));
                    return;
                }
                player.sendMessage(Chat.colored(OreGen.config.getConfig().getString("GenShiftRemoveBesked", "&8&L[ &e&lORE &8&L] &cDu skal shifte for at fjerne gens")));
                event.setCancelled(true);
            }
        }
    }
}
