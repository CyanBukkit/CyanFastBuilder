package cubing.bukkit;

import org.bukkit.*;
import org.bukkit.enchantments.*;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;
import org.bukkit.inventory.meta.*;
import java.util.*;
import com.mojang.authlib.*;
import com.mojang.authlib.properties.*;
import cubing.utils.*;

public class ItemUtils
{
    public ItemUtils() {
        super();
    }
    
    public static ItemStack item(final Material mat, final String name, final int st, final int data, final boolean glow, final boolean unbreak, final ItemFlag[] flags, final Enchantment[] ench, final int[] lvl, final List<String> lore) {
        ItemStack itemStack;
        if (data == -1) {
            itemStack = new ItemStack(mat, st);
        }
        else {
            itemStack = new ItemStack(mat, st, (short)data);
        }
        final ItemMeta meta = itemStack.getItemMeta();
        if (name != null) {
            meta.setDisplayName((glow ? "§r" : "") + name);
        }
        if (glow) {
            meta.addEnchant((Enchantment)new Glow(70), 1, true);
        }
        if (unbreak) {
            meta.spigot().setUnbreakable(true);
        }
        if (flags != null) {
            meta.addItemFlags(flags);
        }
        if (lore != null) {
            meta.setLore((List)lore);
        }
        if (ench != null) {
            for (int i = 0; i < ench.length; ++i) {
                meta.addEnchant(ench[i], lvl[i], true);
            }
        }
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    
    public static ItemStack potion(final String display, final PotionEffect effect, final int amount, final int data, final boolean hideeffect, final List<String> lore) {
        final ItemStack itemStack = new ItemStack(Material.POTION, amount, (short)data);
        final PotionMeta itemMeta = (PotionMeta)itemStack.getItemMeta();
        if (display != null) {
            itemMeta.setDisplayName(display);
        }
        if (hideeffect) {
            itemMeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
        }
        if (effect != null) {
            itemMeta.addCustomEffect(effect, true);
        }
        if (lore != null) {
            itemMeta.setLore((List)lore);
        }
        itemStack.setItemMeta((ItemMeta)itemMeta);
        return itemStack;
    }
    
    public static ItemStack skull(final Material mat, final String name, final int st, final int data, final boolean glow, final List<String> lore, final String url) {
        ItemStack itemStack;
        if (data == -1) {
            itemStack = new ItemStack(mat, st);
        }
        else {
            itemStack = new ItemStack(mat, st, (short)data);
        }
        final SkullMeta itemMeta = (SkullMeta)itemStack.getItemMeta();
        if (url.length() > 16) {
            final GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "");
            gameProfile.getProperties().put((Object)"textures", (Object)new Property("textures", url));
            Reflections.packetSetValue(itemMeta, "profile", gameProfile);
        }
        else {
            itemMeta.setOwner(url);
        }
        itemMeta.setDisplayName((glow ? "§r" : "") + name);
        if (glow) {
            itemMeta.addEnchant((Enchantment)new Glow(70), 1, true);
        }
        if (lore != null) {
            itemMeta.setLore((List)lore);
        }
        itemStack.setItemMeta((ItemMeta)itemMeta);
        return itemStack;
    }
    
    public static ItemStack setLore(final ItemStack itemStack, final List<String> lore) {
        final ItemMeta meta = itemStack.getItemMeta();
        meta.setLore((List)lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    
    public static ItemStack glow(final ItemStack itemStack) {
        final ItemMeta meta = itemStack.getItemMeta();
        meta.addEnchant((Enchantment)new Glow(70), 1, true);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
