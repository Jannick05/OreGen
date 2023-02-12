package dk.nydt.utils;

import dk.nydt.config.UserData;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class Chat {
    public static String colored(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    public static String plain(String s) {
        return s.replaceAll("§", "&");

    }

    public static class GensVariables {

        public static void addMoreToMaxGens(Player player, int i) {
            UserData.setDataToUserData(player, "GensMax", i);
            UserData.saveUserData(player);
        }

        public static int getMaxGen(Player player) {
            return UserData.getintDataUserdata(player, "GensMax");
        }
    }
}
