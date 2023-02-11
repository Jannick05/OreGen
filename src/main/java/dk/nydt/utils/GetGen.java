package dk.nydt.utils;

import dk.nydt.OreGen;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;

public enum GetGen {
    WHITE_CLAY(Material.STAINED_CLAY, "&f&LWHITEGEN"),
    YELLOW_CLAY(Material.STAINED_CLAY, "&f&LWHITEGEN");


    private Material material;
    private String displayName;

    GetGen(Material material, String displayName) {
        this.material = material;
        this.displayName = displayName;
    }

    public Material getMaterial() {
        return material;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ItemStack getGen(Block b, GetGen blockType) {
        if (b.getType().equals(blockType.getMaterial())) {
            ItemStack item = new ItemStack(blockType.getMaterial(), 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(Chat.colored(blockType.getDisplayName()));
            item.setItemMeta(meta);
            return item;
        }
        return null;
    }

    public static Boolean checkBlock(Location location, Player player) {
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File(OreGen.getInstance().getDataFolder() + File.separator + "userdata" + File.separator + player.getUniqueId() + ".yml"));
        ConfigurationSection gens = config.getConfigurationSection("Gens");
        Bukkit.broadcastMessage(String.valueOf(gens.getKeys(false).size()));
        for (String key : gens.getKeys(false)) {

            //SETTING LOCATIONS
            ConfigurationSection gen = gens.getConfigurationSection(key);
            World world = Bukkit.getWorld(gen.getString("Location.World"));
            double x = gen.getDouble("Location.X");
            double y = gen.getDouble("Location.Y");
            double z = gen.getDouble("Location.Z");
            Location location2 = new Location(world, x, y, z);

            //Bukkit.broadcastMessage(Chat.colored("&8[ &c&lDEBUG CheckGen &8]"));
            //Bukkit.broadcastMessage(String.valueOf(location));
            //Bukkit.broadcastMessage(String.valueOf(location2));

            //CHECKING IF LOCATIONS MATCH
            if (location.equals(location2)) {
                return true;
            }
        }
        return false;
    }


}

