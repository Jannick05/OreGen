package dk.nydt.events;

import dk.nydt.OreGen;
import dk.nydt.config.UserData;
import dk.nydt.gens.DefaultConfig;
import dk.nydt.gens.RegisterGen;
import dk.nydt.utils.Chat;
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

        //New
        if (UserData.UserDataExist(player)) {
            if (!UserData.getintDataUserdata(player, "GensTotal").equals(UserData.getintDataUserdata(player, "GensMax"))) {
                RegisterGen.registerGen(location, block, player);
                player.sendMessage(Chat.colored(OreGen.config.getConfig().getString("PlacedGenMessage")));
            } else {
                player.sendMessage(Chat.colored(OreGen.config.getConfig().getString("MaxGensMessage")));
                event.setCancelled(true);
            }
        } else {
            event.setCancelled(true);
            player.sendMessage(Chat.colored(OreGen.config.getConfig().getString("UserDataNotSet", "&8&L[ &e&lORE &8&L] &cKunne ikke finde din userdata... &aopretter userdata")));
            DefaultConfig.defaultConfig(player);
        }

    }
}
