package io.github.endergamerhun.enhancedarmors.items;

import io.github.endergamerhun.enhancedarmors.EnhancedArmors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Arrays;
import java.util.Hashtable;


public class RecipeManager {

    private static final Hashtable<String, NamespacedKey[]> recipeLink = new Hashtable<>();

    public static NamespacedKey[] getSetRecipes(String s) {
        return recipeLink.get(s);
    }

    public static void init() {
        for (String set: ItemManager.getRegisteredSets()) {
            ItemStack[] setItems = ItemManager.getSetItems(set);
            int len = setItems.length;
            NamespacedKey[] recipes = new NamespacedKey[len];
            for (int j = 0; j < len; j++) {
                ItemStack item = setItems[j];
                String type = "_armor";
                switch (item.getType()) {
                    case LEATHER_HELMET -> type = "_helmet";
                    case LEATHER_CHESTPLATE -> type = "_chestplate";
                    case LEATHER_LEGGINGS -> type = "_leggings";
                    case LEATHER_BOOTS -> type = "_boots";
                }
                NamespacedKey key = new NamespacedKey(EnhancedArmors.getInstance(), set + type);
                ShapedRecipe recipe = new ShapedRecipe(key, item);
                switch (item.getType()) {
                    case LEATHER_HELMET -> recipe.shape("101", "2 2", "   ");
                    case LEATHER_CHESTPLATE -> recipe.shape("3 3", "101", "212");
                    case LEATHER_LEGGINGS -> recipe.shape("101", "1 1", "2 2");
                    case LEATHER_BOOTS -> recipe.shape("   ", "1 1", "2 2");
                    default -> recipe.shape("  1", " 2 ", "3  ");
                }
                recipe.setGroup(set);

                Material[] materials = ItemManager.getSetMaterials(set);
                for (int i = 0; i < 4; i++) {
                    String match = String.valueOf(i);
                    if (Arrays.stream(recipe.getShape()).anyMatch(line -> line.contains(match))) {
                        recipe.setIngredient(match.charAt(0), materials[Math.min(i, materials.length - 1)]);
                    }
                }
                Bukkit.addRecipe(recipe);
                recipes[j] = key;
            }
            recipeLink.put(set, recipes);
        }
    }
}
