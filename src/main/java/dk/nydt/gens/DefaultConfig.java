package dk.nydt.gens;

import dk.nydt.OreGen;
import dk.nydt.config.UserData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DefaultConfig {



    public static void defaultConfig(Player player) {
        UserData.setDataToUserData(player, "GensTotal", 0);
        UserData.setDataToUserData(player, "GensMax", 20);
        UserData.saveUserData(player);
    }


}
