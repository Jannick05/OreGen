package dk.nydt.utils;

import dk.nydt.OreGen;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;

public class LoadGens {

    public static HashMap<Integer, GenData> genMap = new HashMap<>();
    ConfigurationSection genList = OreGen.material.getConfig().getConfigurationSection("Genlist");

    public void loadGens() {
        for (String key : genList.getKeys(false)) {
            ConfigurationSection gen = genList.getConfigurationSection(key);
            int id = Integer.parseInt(key);

            Material blockMaterial = Material.valueOf(gen.getString("BlockMaterial"));
            int blockId = Integer.parseInt(gen.getString("BlockID"));
            String blockName = gen.getString("BlockName");
            int blockPris = gen.getInt("BlockPris");
            String blockSpawner = gen.getString("BlockSpawner");

            // Create a new GenData object and add it to the hashmap
            GenData genData = new GenData(blockMaterial, blockId, blockName, blockPris, blockSpawner);
            System.out.println("---- GEN DATA-----");
            System.out.println(blockMaterial);
            System.out.println(blockId);
            System.out.println(blockName);

            genMap.put(id, genData);
        }
    }

}
