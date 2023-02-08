package dk.nydt.events;

import dk.nydt.OreGen;
import dk.nydt.config.UserData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    OreGen plugin;
    public PlayerQuit(OreGen plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (UserData.UserDataExist(p, plugin)) {
            UserData.saveUserData(p, plugin);
        }
    }

}
