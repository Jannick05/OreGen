package dk.nydt.gens;

import dk.nydt.config.UserData;
import org.bukkit.entity.Player;

public class DefaultConfig {
    public static void defaultConfig(Player player) {
        UserData.setDataToUserData(player, "GensTotal", 0);
        UserData.setDataToUserData(player, "GensMax", 20);
        UserData.saveUserData(player);
    }
}
