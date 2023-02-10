package dk.nydt.gens;

import dk.nydt.config.UserData;
import dk.nydt.utils.Chat;
import org.bukkit.entity.Player;

public class GensVariables {

    public static void addMoreToMaxGens(Player player, int i) {
        UserData.setDataToUserData(player, "GensMax", i);
        UserData.saveUserData(player);
    }

    public static int getMaxGen(Player player) {
        return UserData.getintDataUserdata(player, "GensMax");
    }
}
