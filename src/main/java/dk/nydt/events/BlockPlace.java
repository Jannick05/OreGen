package dk.nydt.events;

import dk.nydt.OreGen;
import dk.nydt.config.UserData;
import dk.nydt.gens.DefaultConfig;
import dk.nydt.gens.RegisterGen;
import dk.nydt.utils.Chat;
import dk.nydt.utils.GenData;
import dk.nydt.utils.LoadGens;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashMap;
import java.util.Map;

public class BlockPlace implements Listener {

    OreGen plugin;


    public BlockPlace(OreGen plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material material = block.getType();
        Location location = block.getLocation();
        Bukkit.broadcastMessage(Chat.colored("&8[ &a&lBLOCK DATA &8] &f") + block.getData());

        HashMap genMap = LoadGens.genMap;

        //GenData genData2 = (GenData) genMap.get(1);
        //System.out.println("Block Material: " + genData2.getBlockMaterial());
        //System.out.println("Block ID: " + genData2.getBlockId());
        //System.out.println("getBlockName(): " + genData2.getBlockName());

        GenData genData = null;
        boolean isGenerator = false;

        for (int i = 0; i < genMap.size(); i++) {
            if (((GenData) genMap.get(i + 1)).getBlockMaterial() == material) {

                genData = (GenData) genMap.get(i + 1);

                if (block.getData() == genData.getBlockId()) {

                    if(player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Chat.colored(genData.getBlockName()))) {
                        isGenerator = true;
                        break;
                    }
                }
            }
        }


        if (isGenerator) {

            // get the GenData object associated with the material
            Material blockMaterial = genData.getBlockMaterial();
            int blockId = genData.getBlockId();
            String blockName = genData.getBlockName();
            int blockPris = genData.getBlockPris();
            String blockSpawner = genData.getBlockSpawner();


            if (UserData.UserDataExist(player)) {
                if (!UserData.getintDataUserdata(player, "GensTotal").equals(UserData.getintDataUserdata(player, "GensMax"))) {
                    RegisterGen.registerGen(location, material, event.getBlock(), player);
                    player.sendMessage(Chat.colored(OreGen.config.getConfig().getString("PlacedGenMessage")));
                } else {
                    player.sendMessage(Chat.colored(OreGen.config.getConfig().getString("MaxGensMessage")));
                    event.setCancelled(true);
                }
            } else {
                event.setCancelled(true);
                player.sendMessage(Chat.colored(OreGen.config.getConfig().getString("UserDataNotSet", "&8&L[ &e&lORE &8&L] &cKunne ikke finde din userdata... &aopretter userdata")));
                DefaultConfig.defaultConfig(player);
            }
        }

    }




}

