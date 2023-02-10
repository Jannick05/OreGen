package dk.nydt.gens;

import dk.nydt.OreGen;
import dk.nydt.config.UserData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class RemoveGen {
    public static void removeGen(Location location, Material material, Player player) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File(OreGen.getInstance().getDataFolder() + File.separator + "userdata" + File.separator + player.getUniqueId() + ".yml"));
        ConfigurationSection gens = config.getConfigurationSection("Gens");
        for (String key : gens.getKeys(false)) {
            int keyInt = Integer.parseInt(key);
            ConfigurationSection gen = gens.getConfigurationSection(key);
            World world = Bukkit.getWorld(gen.getString("Location.World"));
            double x = gen.getDouble("Location.X");
            double y = gen.getDouble("Location.Y");
            double z = gen.getDouble("Location.Z");
            Location location2 = new Location(world, x, y, z);
            //System.out.println("Gen " + keyInt + " is at " + location2 + " and is of type " + gen.getString("Type"));

            Bukkit.broadcastMessage(String.valueOf(location));
            Bukkit.broadcastMessage(String.valueOf(location2));
            if (location.equals(location2)) {
                Bukkit.broadcastMessage(key);
                config.set("Gens."+key, null);

                int genstotal = config.getInt("GensTotal");
                config.set("GensTotal", genstotal - 1);

                Bukkit.broadcastMessage(String.valueOf(genstotal));
                UserData.saveUserData(player);
            }
        }
    }
}
