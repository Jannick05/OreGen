package dk.nydt.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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


}

