package dk.nydt.gens;

import dk.nydt.config.UserData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class RegisterGen {
    //Block.getData() virker ikke måske fordi den ikke var en string

    public static void registerGen(Location location, Material material, Block block, Player player) {
        int i = UserData.getintDataUserdata(player, "GensTotal");
        i++;
        UserData.setDataToUserData(player, "GensTotal", i);

        String loc = String.valueOf(location).replace(".", " ");

        UserData.setDataToUserData(player, "Gens."+ loc +".Location.World", location.getWorld().getName());
        UserData.setDataToUserData(player, "Gens."+ loc +".Location.X", location.getX());
        UserData.setDataToUserData(player, "Gens."+ loc +".Location.Y", location.getY());
        UserData.setDataToUserData(player, "Gens."+ loc +".Location.Z", location.getZ());
        UserData.setDataToUserData(player, "Gens."+ loc +".Type", material.name());

        UserData.saveUserData(player);
    }

}
