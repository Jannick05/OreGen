package dk.nydt.utils;

import dk.nydt.OreGen;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class GenData {

    private Material blockMaterial;
    private int blockId;
    private String blockName;
    private int blockPris;
    private String blockSpawner;

    public GenData(Material blockMaterial, int blockId, String blockName, int blockPris, String blockSpawner) {
        this.blockMaterial = blockMaterial;
        this.blockId = blockId;
        this.blockName = blockName;
        this.blockPris = blockPris;
        this.blockSpawner = blockSpawner;
    }

    // Define getters for each field
    public Material getBlockMaterial() {
        return blockMaterial;
    }

    public int getBlockId() {
        return blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public int getBlockPris() {
        return blockPris;
    }

    public String getBlockSpawner() {
        return blockSpawner;
    }

}

