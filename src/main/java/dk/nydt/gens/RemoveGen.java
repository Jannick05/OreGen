package dk.nydt.gens;

import dk.nydt.OreGen;
import dk.nydt.config.UserData;
import dk.nydt.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RemoveGen {


    public static void removeGen(Location location, Material material, Player player) {
        File file = new File(OreGen.getInstance().getDataFolder() + File.separator + "userdata" + File.separator + player.getUniqueId() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection gens = config.getConfigurationSection("Gens");
        for (String key : gens.getKeys(false)) {

            ConfigurationSection gen = gens.getConfigurationSection(key);
            World world = Bukkit.getWorld(gen.getString("Location.World"));
            double x = gen.getDouble("Location.X");
            double y = gen.getDouble("Location.Y");
            double z = gen.getDouble("Location.Z");
            Location location2 = new Location(world, x, y, z);

            if (location.equals(location2)) {
                location.getBlock().setType(Material.AIR);

                //TODO: Giv spilleren gen tilbage
                //REMOVING FROM CONFIG

                config.set("Gens." + key, null);
                int gensTotal = config.getInt("GensTotal");
                config.set("GensTotal", gensTotal - 1);

                //SAVING CONFIG
                try {
                    config.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
