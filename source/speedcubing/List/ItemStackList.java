package speedcubing.List;

import speedcubing.*;
import org.bukkit.inventory.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.plugin.java.*;

public class ItemStackList
{
    private static final Main plugin;
    public static final InventoryHolder menu;
    public static final InventoryHolder settings;
    public static final InventoryHolder confirm;
    public static ItemStack openkit;
    public static ItemStack navigator;
    public static ItemStack pickaxe;
    public static ItemStack Settings;
    public static ItemStack Reset;
    public static ItemStack[] Blocks;
    public static ItemStack[] categoryblockeditor;
    public static ItemStack[] blockeditor;
    public static ItemStack[] wooleditor;
    public static ItemStack[] sandstoneeditor;
    public static ItemStack[] oreblockeditor;
    public static ItemStack[] oreeditor;
    public static ItemStack[] quartzeditor;
    public static ItemStack[] woodeditor;
    public static ItemStack[] clayeditor;
    public static final String blockedit;
    public static final String sandstoneedit;
    public static final String wooledit;
    public static final String oreblockedit;
    public static final String oreedit;
    public static final String quartzedit;
    public static final String woodedit;
    public static final String clayedit;
    public static Inventory ResetConfirm;
    
    public ItemStackList() {
        super();
    }
    
    public void Load() {
        final String resetconfirm = ItemStackList.plugin.getStringFromConfig("resetconfirm");
        final String breaktool = ItemStackList.plugin.getStringFromConfig("breaktool");
        final String nv = ItemStackList.plugin.getStringFromConfig("nv");
        final String settings = ItemStackList.plugin.getStringFromConfig("settings");
        final String reset = ItemStackList.plugin.getStringFromConfig("reset");
        final String category = ItemStackList.plugin.getStringFromConfig("category");
        final String[] block = ItemStackList.plugin.getStringListFromConfig("block");
        final String yes = ItemStackList.plugin.getStringFromConfig("yess");
        final String no = ItemStackList.plugin.getStringFromConfig("noo");
        ItemStackList.navigator = ItemUtils.item(Material.COMPASS, nv, 1, -1, false, false, null, null, null, null);
        ItemStackList.pickaxe = ItemUtils.item(Material.DIAMOND_PICKAXE, breaktool, 1, -1, true, true, new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES }, null, null, null);
        ItemStackList.Settings = ItemUtils.item(Material.REDSTONE_COMPARATOR, settings, 1, -1, false, false, null, null, null, null);
        ItemStackList.Reset = ItemUtils.item(Material.BARRIER, reset, 1, -1, false, false, null, null, null, null);
        ItemStackList.openkit = ItemUtils.item(Material.SANDSTONE, ItemStackList.blockedit, 1, 2, false, false, null, null, null, null);
        final Inventory inv = Bukkit.createInventory(ItemStackList.confirm, 27, resetconfirm);
        inv.setItem(11, ItemUtils.item(Material.STAINED_CLAY, yes, 1, 13, false, false, null, null, null, null));
        inv.setItem(15, ItemUtils.item(Material.STAINED_CLAY, no, 1, 14, false, false, null, null, null, null));
        ItemStackList.ResetConfirm = inv;
        final ItemStack[] categoryblockeditor = { ItemUtils.item(Material.SANDSTONE, block[0], 1, -1, false, false, null, null, null, Collections.singletonList(category)), ItemUtils.item(Material.WOOL, block[1], 1, -1, false, false, null, null, null, Collections.singletonList(category)), ItemUtils.item(Material.DIAMOND_BLOCK, block[2], 1, -1, false, false, null, null, null, Collections.singletonList(category)), ItemUtils.item(Material.DIAMOND_ORE, block[3], 1, -1, false, false, null, null, null, Collections.singletonList(category)), ItemUtils.item(Material.QUARTZ_BLOCK, block[4], 1, -1, false, false, null, null, null, Collections.singletonList(category)), ItemUtils.item(Material.WOOD, block[5], 1, -1, false, false, null, null, null, Collections.singletonList(category)), ItemUtils.item(Material.STAINED_CLAY, block[6], 1, -1, false, false, null, null, null, Collections.singletonList(category)) };
        final ItemStack[] blockeditor = { ItemUtils.item(Material.MELON_BLOCK, null, 1, -1, false, false, null, null, null, null), ItemUtils.item(Material.SNOW_BLOCK, null, 1, -1, false, false, null, null, null, null), ItemUtils.item(Material.OBSIDIAN, null, 1, -1, false, false, null, null, null, null), ItemUtils.item(Material.ENDER_STONE, null, 1, -1, false, false, null, null, null, null) };
        final ItemStack[] sandstoneeditor = { ItemUtils.item(Material.SANDSTONE, null, 1, 0, false, false, null, null, null, null), ItemUtils.item(Material.SANDSTONE, null, 1, 1, false, false, null, null, null, null), ItemUtils.item(Material.SANDSTONE, null, 1, 2, false, false, null, null, null, null), ItemUtils.item(Material.RED_SANDSTONE, null, 1, 0, false, false, null, null, null, null), ItemUtils.item(Material.RED_SANDSTONE, null, 1, 1, false, false, null, null, null, null), ItemUtils.item(Material.RED_SANDSTONE, null, 1, 2, false, false, null, null, null, null) };
        final ItemStack[] wooleditor = { ItemUtils.item(Material.WOOL, null, 1, 0, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 1, 1, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 1, 2, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 1, 3, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 1, 4, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 1, 5, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 1, 6, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 1, 7, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 1, 8, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 1, 9, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 1, 10, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 1, 11, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 1, 12, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 1, 13, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 1, 14, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 1, 15, false, false, null, null, null, null) };
        final ItemStack[] oreblockeditor = { ItemUtils.item(Material.DIAMOND_BLOCK, null, 1, -1, false, false, null, null, null, null), ItemUtils.item(Material.IRON_BLOCK, null, 1, -1, false, false, null, null, null, null), ItemUtils.item(Material.REDSTONE_BLOCK, null, 1, -1, false, false, null, null, null, null), ItemUtils.item(Material.LAPIS_BLOCK, null, 1, -1, false, false, null, null, null, null), ItemUtils.item(Material.EMERALD_BLOCK, null, 1, -1, false, false, null, null, null, null), ItemUtils.item(Material.GOLD_BLOCK, null, 1, -1, false, false, null, null, null, null), ItemUtils.item(Material.COAL_BLOCK, null, 1, -1, false, false, null, null, null, null) };
        final ItemStack[] oreeditor = { ItemUtils.item(Material.DIAMOND_ORE, null, 1, -1, false, false, null, null, null, null), ItemUtils.item(Material.IRON_ORE, null, 1, -1, false, false, null, null, null, null), ItemUtils.item(Material.REDSTONE_ORE, null, 1, -1, false, false, null, null, null, null), ItemUtils.item(Material.LAPIS_ORE, null, 1, -1, false, false, null, null, null, null), ItemUtils.item(Material.EMERALD_ORE, null, 1, -1, false, false, null, null, null, null), ItemUtils.item(Material.GOLD_ORE, null, 1, -1, false, false, null, null, null, null), ItemUtils.item(Material.COAL_ORE, null, 1, -1, false, false, null, null, null, null) };
        final ItemStack[] quartzeditor = { ItemUtils.item(Material.QUARTZ_BLOCK, null, 1, 0, false, false, null, null, null, null), ItemUtils.item(Material.QUARTZ_BLOCK, null, 1, 1, false, false, null, null, null, null), ItemUtils.item(Material.QUARTZ_BLOCK, null, 1, 2, false, false, null, null, null, null) };
        final ItemStack[] woodeditor = { ItemUtils.item(Material.WOOD, null, 1, 0, false, false, null, null, null, null), ItemUtils.item(Material.WOOD, null, 1, 1, false, false, null, null, null, null), ItemUtils.item(Material.WOOD, null, 1, 2, false, false, null, null, null, null), ItemUtils.item(Material.WOOD, null, 1, 3, false, false, null, null, null, null), ItemUtils.item(Material.WOOD, null, 1, 4, false, false, null, null, null, null), ItemUtils.item(Material.WOOD, null, 1, 5, false, false, null, null, null, null), ItemUtils.item(Material.LOG, null, 1, 0, false, false, null, null, null, null), ItemUtils.item(Material.LOG, null, 1, 1, false, false, null, null, null, null), ItemUtils.item(Material.LOG, null, 1, 2, false, false, null, null, null, null), ItemUtils.item(Material.LOG, null, 1, 3, false, false, null, null, null, null), ItemUtils.item(Material.LOG_2, null, 1, 0, false, false, null, null, null, null), ItemUtils.item(Material.LOG_2, null, 1, 1, false, false, null, null, null, null) };
        final ItemStack[] clayeditor = { ItemUtils.item(Material.STAINED_CLAY, null, 1, 0, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 1, 1, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 1, 2, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 1, 3, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 1, 4, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 1, 5, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 1, 6, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 1, 7, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 1, 8, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 1, 9, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 1, 10, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 1, 11, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 1, 12, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 1, 13, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 1, 14, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 1, 15, false, false, null, null, null, null), ItemUtils.item(Material.HARD_CLAY, null, 1, -1, false, false, null, null, null, null) };
        final ItemStack[] blocks = { ItemUtils.item(Material.SANDSTONE, null, 64, 0, false, false, null, null, null, null), ItemUtils.item(Material.SANDSTONE, null, 64, 1, false, false, null, null, null, null), ItemUtils.item(Material.SANDSTONE, null, 64, 2, false, false, null, null, null, null), ItemUtils.item(Material.RED_SANDSTONE, null, 64, 0, false, false, null, null, null, null), ItemUtils.item(Material.RED_SANDSTONE, null, 64, 1, false, false, null, null, null, null), ItemUtils.item(Material.RED_SANDSTONE, null, 64, 2, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 64, 0, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 64, 1, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 64, 2, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 64, 3, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 64, 4, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 64, 5, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 64, 6, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 64, 7, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 64, 8, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 64, 9, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 64, 10, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 64, 11, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 64, 12, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 64, 13, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 64, 14, false, false, null, null, null, null), ItemUtils.item(Material.WOOL, null, 64, 15, false, false, null, null, null, null), ItemUtils.item(Material.DIAMOND_BLOCK, null, 64, -1, false, false, null, null, null, null), ItemUtils.item(Material.IRON_BLOCK, null, 64, -1, false, false, null, null, null, null), ItemUtils.item(Material.REDSTONE_BLOCK, null, 64, -1, false, false, null, null, null, null), ItemUtils.item(Material.LAPIS_BLOCK, null, 64, -1, false, false, null, null, null, null), ItemUtils.item(Material.EMERALD_BLOCK, null, 64, -1, false, false, null, null, null, null), ItemUtils.item(Material.GOLD_BLOCK, null, 64, -1, false, false, null, null, null, null), ItemUtils.item(Material.COAL_BLOCK, null, 64, -1, false, false, null, null, null, null), ItemUtils.item(Material.DIAMOND_ORE, null, 64, -1, false, false, null, null, null, null), ItemUtils.item(Material.IRON_ORE, null, 64, -1, false, false, null, null, null, null), ItemUtils.item(Material.REDSTONE_ORE, null, 64, -1, false, false, null, null, null, null), ItemUtils.item(Material.LAPIS_ORE, null, 64, -1, false, false, null, null, null, null), ItemUtils.item(Material.EMERALD_ORE, null, 64, -1, false, false, null, null, null, null), ItemUtils.item(Material.GOLD_ORE, null, 64, -1, false, false, null, null, null, null), ItemUtils.item(Material.COAL_ORE, null, 64, -1, false, false, null, null, null, null), ItemUtils.item(Material.QUARTZ_BLOCK, null, 64, 0, false, false, null, null, null, null), ItemUtils.item(Material.QUARTZ_BLOCK, null, 64, 1, false, false, null, null, null, null), ItemUtils.item(Material.QUARTZ_BLOCK, null, 64, 2, false, false, null, null, null, null), ItemUtils.item(Material.WOOD, null, 64, 0, false, false, null, null, null, null), ItemUtils.item(Material.WOOD, null, 64, 1, false, false, null, null, null, null), ItemUtils.item(Material.WOOD, null, 64, 2, false, false, null, null, null, null), ItemUtils.item(Material.WOOD, null, 64, 3, false, false, null, null, null, null), ItemUtils.item(Material.WOOD, null, 64, 4, false, false, null, null, null, null), ItemUtils.item(Material.WOOD, null, 64, 5, false, false, null, null, null, null), ItemUtils.item(Material.LOG, null, 64, 0, false, false, null, null, null, null), ItemUtils.item(Material.LOG, null, 64, 1, false, false, null, null, null, null), ItemUtils.item(Material.LOG, null, 64, 2, false, false, null, null, null, null), ItemUtils.item(Material.LOG, null, 64, 3, false, false, null, null, null, null), ItemUtils.item(Material.LOG_2, null, 64, 0, false, false, null, null, null, null), ItemUtils.item(Material.LOG_2, null, 64, 1, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 64, 0, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 64, 1, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 64, 2, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 64, 3, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 64, 4, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 64, 5, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 64, 6, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 64, 7, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 64, 8, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 64, 9, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 64, 10, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 64, 11, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 64, 12, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 64, 13, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 64, 14, false, false, null, null, null, null), ItemUtils.item(Material.STAINED_CLAY, null, 64, 15, false, false, null, null, null, null), ItemUtils.item(Material.HARD_CLAY, null, 64, -1, false, false, null, null, null, null), ItemUtils.item(Material.MELON_BLOCK, null, 64, 2, false, false, null, null, null, null), ItemUtils.item(Material.SNOW_BLOCK, null, 64, -1, false, false, null, null, null, null), ItemUtils.item(Material.OBSIDIAN, null, 64, -1, false, false, null, null, null, null), ItemUtils.item(Material.ENDER_STONE, null, 64, -1, false, false, null, null, null, null) };
        for (int j = 0; j < blocks.length; ++j) {
            ItemStackList.Blocks[j] = blocks[j];
        }
        for (int j = 0; j < categoryblockeditor.length; ++j) {
            ItemStackList.categoryblockeditor[j] = categoryblockeditor[j];
        }
        for (int j = 0; j < blockeditor.length; ++j) {
            ItemStackList.blockeditor[j] = blockeditor[j];
        }
        for (int j = 0; j < sandstoneeditor.length; ++j) {
            ItemStackList.sandstoneeditor[j] = sandstoneeditor[j];
        }
        for (int j = 0; j < wooleditor.length; ++j) {
            ItemStackList.wooleditor[j] = wooleditor[j];
        }
        for (int j = 0; j < oreblockeditor.length; ++j) {
            ItemStackList.oreblockeditor[j] = oreblockeditor[j];
        }
        for (int j = 0; j < oreeditor.length; ++j) {
            ItemStackList.oreeditor[j] = oreeditor[j];
        }
        for (int j = 0; j < quartzeditor.length; ++j) {
            ItemStackList.quartzeditor[j] = quartzeditor[j];
        }
        for (int j = 0; j < woodeditor.length; ++j) {
            ItemStackList.woodeditor[j] = woodeditor[j];
        }
        for (int j = 0; j < clayeditor.length; ++j) {
            ItemStackList.clayeditor[j] = clayeditor[j];
        }
    }
    
    private static /* synthetic */ Inventory lambda$static$2() {
        return null;
    }
    
    private static /* synthetic */ Inventory lambda$static$1() {
        return null;
    }
    
    private static /* synthetic */ Inventory lambda$static$0() {
        return null;
    }
    
    static {
        plugin = (Main)JavaPlugin.getPlugin((Class)Main.class);
        menu = (() -> null);
        settings = (() -> null);
        confirm = (() -> null);
        blockedit = ItemStackList.plugin.getStringFromConfig("blockedit");
        sandstoneedit = ItemStackList.plugin.getStringFromConfig("sandstoneedit");
        wooledit = ItemStackList.plugin.getStringFromConfig("wooledit");
        oreblockedit = ItemStackList.plugin.getStringFromConfig("oreblockedit");
        oreedit = ItemStackList.plugin.getStringFromConfig("oreedit");
        quartzedit = ItemStackList.plugin.getStringFromConfig("quartzedit");
        woodedit = ItemStackList.plugin.getStringFromConfig("woodedit");
        clayedit = ItemStackList.plugin.getStringFromConfig("clayedit");
        ItemStackList.Blocks = new ItemStack[72];
        ItemStackList.categoryblockeditor = new ItemStack[7];
        ItemStackList.blockeditor = new ItemStack[4];
        ItemStackList.wooleditor = new ItemStack[16];
        ItemStackList.sandstoneeditor = new ItemStack[6];
        ItemStackList.oreblockeditor = new ItemStack[7];
        ItemStackList.oreeditor = new ItemStack[7];
        ItemStackList.quartzeditor = new ItemStack[3];
        ItemStackList.woodeditor = new ItemStack[12];
        ItemStackList.clayeditor = new ItemStack[17];
    }
}
