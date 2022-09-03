package io.github.endergamerhun.enhancedarmors.items;

import io.github.endergamerhun.enhancedarmors.EnhancedArmors;
import io.github.endergamerhun.enhancedarmors.utils.Util;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Hashtable;

public class ItemManager {

    private static final Material[] ARMOR_TYPES = {Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS};
    private static final NamespacedKey KEY = new NamespacedKey(EnhancedArmors.getInstance(), "set");
    private static final Hashtable<String, ItemStack[]> sets = new Hashtable<>();

    public static ItemStack[] getSetItems(String s) {
        return sets.get(s);
    }
    public static int getSetSize(String s) {
        return sets.get(s).length;
    }

    public static boolean containsSet(String id) {
        return sets.containsKey(id);
    }

    public static String[] getSetList() {
        return sets.keySet().toArray(new String[0]);
    }

    public static boolean isSetPiece(ItemStack item) {
        PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
        return pdc.has(KEY, PersistentDataType.STRING);
    }
    public static String getItemId(ItemStack item) {
        PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
        return pdc.get(KEY, PersistentDataType.STRING);
    }

    public static void init() {
        sets.put("miner", new ItemStack[]{createArmor("miner", Material.LEATHER_HELMET, Color.GRAY)});
        sets.put("speed", createSet("speed", Color.WHITE, new boolean[]{false, false, true, true}));
    }

    private static ItemStack createArmor(String id, Material material, Color color) {
        ItemStack item = new ItemStack(material);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        assert meta != null;
        meta.setColor(color);
        meta.setUnbreakable(true);
        String ending = " Armor";
        if (material == ARMOR_TYPES[0]) ending = " Helmet";
        if (material == ARMOR_TYPES[1]) ending = " Chestplate";
        if (material == ARMOR_TYPES[2]) ending = " Leggings";
        if (material == ARMOR_TYPES[3]) ending = " Boots";
        meta.setDisplayName(ChatColor.YELLOW + Util.toTitleCase(id) + ending);
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        pdc.set(KEY, PersistentDataType.STRING, id);
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack[] createSet(String id, Color color, boolean[] set) {
        if (set.length != 4) throw new IllegalArgumentException("Set must contain 4 booleans");

        int size = 0;
        for (boolean b:set) if (b) size++;

        ItemStack[] items = new ItemStack[size];

        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (!set[i]) continue;
            items[count] = createArmor(id, ARMOR_TYPES[i], color);
            count++;
        }
        return items;
    }
}
