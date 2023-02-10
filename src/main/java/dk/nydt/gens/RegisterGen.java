package dk.nydt.gens;

import dk.nydt.config.UserData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class RegisterGen {
    //Block.getData() virker ikke måske fordi den ikke var en string

    public static void registerGen(Location location, Material material, Player player) {
        Bukkit.broadcastMessage(String.valueOf(UserData.getintDataUserdata(player, "GensTotal")));
        int i = UserData.getintDataUserdata(player, "GensTotal");
        i++;
        UserData.setDataToUserData(player, "GensTotal", i);
        //DEbug
        Bukkit.broadcastMessage("i - " + i);
        Bukkit.broadcastMessage("Material - " + material);
        Bukkit.broadcastMessage("location - " + location);

        UserData.setDataToUserData(player, "Gens."+ i +".Location.World", location.getWorld().getName());
        UserData.setDataToUserData(player, "Gens."+ i +".Location.X", location.getX());
        UserData.setDataToUserData(player, "Gens."+ i +".Location.Y", location.getY());
        UserData.setDataToUserData(player, "Gens."+ i +".Location.Z", location.getZ());
        UserData.setDataToUserData(player, "Gens."+ i +".Type", material.name());

        UserData.saveUserData(player);
    }

}