package dk.nydt;

import dk.nydt.config.Config;
import dk.nydt.config.UserData;
import dk.nydt.events.BlockPlace;
import dk.nydt.events.PlayerJoin;
import dk.nydt.events.PlayerQuit;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class OreGen extends JavaPlugin {
    //Todo:
    private static PluginManager pluginManager;
    public static Config config;
    public static FileConfiguration configYML;
    public static OreGen instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        pluginManager = getServer().getPluginManager();
        instance = this;

        //Registering Listeners
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockPlace(this), this);

        //Creating userdata directory
        File directory = new File(this.getDataFolder(), "userdata");
        if(directory.exists() || directory.mkdirs()) {
            System.out.println("lortet virkede?!");
        }

        //Config.yml
        if (!(new File(getDataFolder(), "config.yml")).exists())
            saveResource("config.yml", false);

        config = new Config(this, null, "config.yml");
        configYML = config.getConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for(Player player : Bukkit.getOnlinePlayers()) {
            System.out.println("Saver alt userdata");
            UserData.saveUserData(player, instance);
        }
    }

    public static OreGen getInstance(){
        return instance;
    }
}
