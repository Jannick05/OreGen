package dk.nydt.commands;

import dk.nydt.utils.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        String _command = (label == null) ? String.valueOf(command) : label;
        if(args.length == 0) {
            if (!p.hasPermission("OreGen.admin")) {
                p.sendMessage(Chat.colored("&8&L[ &e&lORE &8&L] &cDu har ikke adgang til denne command."));
            } else {
                sendAdminDefaultCommand(p, _command);
            }
            return true;

        }
        return false;
    }

    private void sendAdminDefaultCommand(CommandSender sender, String command){
        String sb = "";
        sb = sb + "\n ";
        sb = sb + "&7/" + command + " reload &8&fReloader &econfig.yml\n ";
        sender.sendMessage(Chat.colored(sb));
    }

}
