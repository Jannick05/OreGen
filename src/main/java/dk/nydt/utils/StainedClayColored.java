package dk.nydt.utils;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum StainedClayColored {
    WHITE_STAINED_CLAY(Material.STAINED_CLAY, 1,DyeColor.WHITE),
    YELLOW_STAINED_CLAY(Material.STAINED_CLAY, 1, DyeColor.YELLOW);


    private Material material;
    private DyeColor color;
    private Integer amount;

    StainedClayColored(Material material,Integer amount, DyeColor color) {
        this.material = material;
        this.color = color;
        this.amount = amount;
    }

    public Material getMaterial() {
        return material;
    }

    public DyeColor getColor() {
        return color;
    }
    public Integer getAmount() {
        return amount;
    }


    public static void createStainedClay(StainedClayColored material) {
        ItemStack item = new ItemStack(material.getMaterial(), material.getAmount());
        item.setDurability(material.getColor().getData());
    }
}

