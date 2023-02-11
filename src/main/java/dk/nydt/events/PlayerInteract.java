package dk.nydt.events;

import dk.nydt.OreGen;
import dk.nydt.gens.RemoveGen;
import dk.nydt.utils.Chat;
import dk.nydt.utils.GetGen;
import org.bukkit.Bukkit;
import org.bukkit.Material;
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

        if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (event.getPlayer().getItemInHand().getType() == Material.AIR) {
                if (Boolean.TRUE.equals(GetGen.checkBlock(event.getClickedBlock().getLocation(), player))) {

                    if (player.isSneaking()) {

                        RemoveGen.removeGen(event.getClickedBlock().getLocation(), event.getClickedBlock().getType(), player);
                        player.sendMessage(Chat.colored(OreGen.config.getConfig().getString("GenRemoveBesked", "&8&L[ &e&lORE &8&L] &cDu fjernede en gen.")));
                        event.setCancelled(true);
                        return;
                    }
                    player.sendMessage(Chat.colored(OreGen.config.getConfig().getString("GenShiftRemoveBesked", "&8&L[ &e&lORE &8&L] &cDu skal shifte for at fjerne gens")));
                    event.setCancelled(true);
                }
            } else {
                player.sendMessage(Chat.colored(OreGen.config.getConfig().getString("ItemInHand", "&8&L[ &e&lORE &8&L] &cDu må ikke have noget i hånden.")));
                event.setCancelled(true);

            }
        }
    }
}
