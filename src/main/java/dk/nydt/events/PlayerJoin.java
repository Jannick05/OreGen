package dk.nydt.events;

import dk.nydt.OreGen;
import dk.nydt.config.UserData;
import dk.nydt.gens.DefaultConfig;
import org.bukkit.Bukkit;
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
        Bukkit.broadcastMessage("UserData.UserDataExist(p) " + UserData.UserDataExist(p));


        if(!UserData.UserDataExist(p)){
            UserData.CreateUserData(p);


            //New
            DefaultConfig.defaultConfig(p);
        }

    }
}
