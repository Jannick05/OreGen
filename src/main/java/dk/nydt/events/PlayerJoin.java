package dk.nydt.events;

import dk.nydt.OreGen;
import dk.nydt.config.UserData;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    OreGen plugin;

    public PlayerJoin(OreGen plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayer(PlayerJoinEvent e){
        Player p = e.getPlayer();

        //Checks and creates userdata file, if it doesn't already exist.
        UserData.CreateUserData(p, plugin);
        UserData.addDataToUserData(p, "PlayerJoinEXP.exp", p.getExp(), plugin);
        Bukkit.broadcastMessage(UserData.getStringDataUserdata(p, "PlayerJoinEXP.exp", plugin));

    }
}
