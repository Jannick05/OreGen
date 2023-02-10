package dk.nydt.config;

import dk.nydt.OreGen;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class UserData {
    //Todo
    //Create a UserData File for a player.
    public static void CreateUserData(Player player) {

        File userFile = new File(OreGen.getInstance().getDataFolder() + File.separator + "userdata" + File.separator + player.getUniqueId() + ".yml");

        try {
            if (!userFile.exists()) {
                if (!userFile.createNewFile()) {
                    throw new IOException("Unable to create file");
                }
                Bukkit.broadcastMessage("User" + player.getName() + " does not have a config file! Generating a new one!");
            }
        } catch (IOException exception) {
            OreGen.getInstance().getLogger().log(Level.SEVERE, "Could not create config file", exception);
        }

    }

    //Add data to UserData File.
    public static void setDataToUserData(Player player, String key, Object value) {
        File userFile = new File(OreGen.getInstance().getDataFolder() + File.separator + "userdata" + File.separator + player.getUniqueId() + ".yml");
        YamlConfiguration userData = YamlConfiguration.loadConfiguration(userFile);

        userData.set(key, value);
        try {
            userData.save(userFile);
        } catch (IOException exception) {
            OreGen.getInstance().getLogger().log(Level.SEVERE, "Could not save data to user file", exception);
        }
    }


    //Check if UserData File exist.
    public static boolean UserDataExist(Player player) {
        File userFile = new File(OreGen.getInstance().getDataFolder() + File.separator + "userdata" + File.separator + player.getUniqueId() + ".yml");
        System.out.println("Use -" + userFile.exists());
        return userFile.exists();
    }

    //Remove data from UserData File.
    public static void removeUserData(Player player) {
        File userFile = new File(OreGen.getInstance().getDataFolder() + File.separator + "userdata" + File.separator + player.getUniqueId() + ".yml");

        if (!userFile.exists()) {
            OreGen.getInstance().getLogger().log(Level.WARNING, "User file for player " + player.getName() + " does not exist");
            return;
        }

        // Read the data from the file and save it in memory if necessary
        // ...

        if (!userFile.delete()) {
            OreGen.getInstance().getLogger().log(Level.SEVERE, "Could not delete user file for player " + player.getName());
        }
    }

    public static void saveUserData(Player player) {
        File userFile = new File(OreGen.getInstance().getDataFolder() + File.separator + "userdata" + File.separator + player.getUniqueId() + ".yml");
        YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);
        try {
            userConfig.save(userFile);
        } catch (IOException exception) {
            OreGen.getInstance().getLogger().log(Level.SEVERE, "Could not save config file for user " + player.getName(), exception);
        }
    }


    public static String getStringDataUserdata(Player player, String path) {
        File userFile = new File(OreGen.getInstance().getDataFolder() + File.separator + "userdata" + File.separator + player.getUniqueId() + ".yml");
        FileConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);
        return userConfig.getString(path);
    }

    public static Integer getintDataUserdata(Player player, String path) {
        File userFile = new File(OreGen.getInstance().getDataFolder() + File.separator + "userdata" + File.separator + player.getUniqueId() + ".yml");
        FileConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);
        return userConfig.getInt(path);
    }

    public static Double getDoubleDataUserdata(Player player, String path) {
        File userFile = new File(OreGen.getInstance().getDataFolder() + File.separator + "userdata" + File.separator + player.getUniqueId() + ".yml");
        FileConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);
        return userConfig.getDouble(path);
    }


}

