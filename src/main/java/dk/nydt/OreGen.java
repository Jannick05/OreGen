package dk.nydt;

import dk.nydt.commands.OreCommand;
import dk.nydt.config.Config;
import dk.nydt.config.UserData;
import dk.nydt.events.PlayerInteract;
import dk.nydt.events.BlockPlace;
import dk.nydt.events.PlayerJoin;
import dk.nydt.events.PlayerQuit;
import dk.nydt.utils.LoadGens;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class OreGen extends JavaPlugin {
    //Todo:
    private static PluginManager pluginManager;
    public static Config config, material;
    public static FileConfiguration configYML, materialYML;

    public static OreGen instance;
    public static LoadGens loadGens;
    @Override
    public void onEnable() {
        // Plugin startup logic
        pluginManager = getServer().getPluginManager();
        instance = this;
        //Registering Listeners
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockPlace(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteract(this), this);
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

        if (!(new File(getDataFolder(), "material.yml")).exists())
            saveResource("material.yml", false);

        material = new Config(this, null, "material.yml");
        materialYML = material.getConfig();

        getCommand("oregen").setExecutor(new OreCommand());

        loadGens = new LoadGens();
        loadGens.loadGens();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for(Player player : Bukkit.getOnlinePlayers()) {
            System.out.println("Saver alt userdata");
            UserData.saveUserData(player);
        }
    }

    public static OreGen getInstance(){
        return instance;
    }
    public static LoadGens getLoadgens(){
        return loadGens;
    }
}
