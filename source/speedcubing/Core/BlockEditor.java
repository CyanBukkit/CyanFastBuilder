package speedcubing.Core;

import speedcubing.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import speedcubing.List.*;

import java.util.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;
import org.bukkit.plugin.java.*;

public class BlockEditor
{
    private static final Main plugin;
    public static final InventoryHolder BlockHolder;
    public static final InventoryHolder sandstoneholder;
    public static final InventoryHolder woolholder;
    public static final InventoryHolder oreblockeditor;
    public static final InventoryHolder oreeditor;
    public static final InventoryHolder quartzeditor;
    public static final InventoryHolder woodeditor;
    public static final InventoryHolder clayeditor;
    static int length;
    
    public BlockEditor() {
        super();
    }
    
    public void Load() {
        BlockEditor.length = ItemStackList.wooleditor.length + ItemStackList.sandstoneeditor.length + ItemStackList.oreblockeditor.length + ItemStackList.oreeditor.length + ItemStackList.quartzeditor.length + ItemStackList.woodeditor.length + ItemStackList.clayeditor.length;
    }
    
    public static void createMainPage(final Player player) {
        player.playSound(player.getLocation(), Sound.CLICK, 1.0f, 1.0f);
        final UUID uuid = player.getUniqueId();
        final Inventory inv = Bukkit.createInventory(BlockEditor.BlockHolder, 27, BlockEditor.plugin.getStringFromConfig("blockedit"));
        for (int i = 0; i < ItemStackList.categoryblockeditor.length; ++i) {
            inv.setItem(i + 1, ItemStackList.categoryblockeditor[i]);
        }
        for (int i = 0; i < ItemStackList.blockeditor.length; ++i) {
            inv.setItem(i + 12, ItemStackList.blockeditor[i]);
        }
        final int x = Core.integerCache.get(uuid)[0];
        final int index = x - BlockEditor.length;
        if (index >= 0) {
            inv.setItem(index + 12, ItemUtils.glow(ItemUtils.setLore(ItemStackList.blockeditor[index].clone(), Collections.singletonList(GlobalString.selected))));
        }
        player.openInventory(inv);
    }
    
    public static void select(final InventoryHolder holder, final InventoryClickEvent e, final int RawSlot, final Player player) {
        if (holder == BlockEditor.BlockHolder) {
            e.setCancelled(true);
            if (RawSlot >= 12 && RawSlot < 12 + ItemStackList.blockeditor.length) {
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                final int x = RawSlot + BlockEditor.length - 12;
                final UUID uuid = player.getUniqueId();
                Core.integerCache.get(uuid)[0] = x;
                player.closeInventory();
                final ItemStack stack = ItemStackList.Blocks[x];
                player.getInventory().setItem(0, stack);
                player.getInventory().setItem(1, stack);
                player.sendMessage(GlobalString.SavedSettings);
            }
            else if (RawSlot >= 1 && RawSlot <= ItemStackList.categoryblockeditor.length) {
                e.setCancelled(true);
                switch (RawSlot) {
                    case 1: {
                        player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                        final UUID uuid = player.getUniqueId();
                        final Inventory inv = Bukkit.createInventory(BlockEditor.sandstoneholder, 27, BlockEditor.plugin.getStringFromConfig("sandstoneedit"));
                        for (int i = 0; i < 3; ++i) {
                            inv.setItem(i + 10, ItemStackList.sandstoneeditor[i]);
                        }
                        for (int i = 3; i < 6; ++i) {
                            inv.setItem(i + 11, ItemStackList.sandstoneeditor[i]);
                        }
                        final int set = Core.integerCache.get(uuid)[0];
                        if (set < 6) {
                            inv.setItem((set < 3) ? (set + 10) : (set + 11), ItemUtils.glow(ItemUtils.setLore(ItemStackList.sandstoneeditor[set].clone(), Collections.singletonList(GlobalString.selected))));
                        }
                        player.openInventory(inv);
                        break;
                    }
                    case 2: {
                        player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                        final UUID uuid2 = player.getUniqueId();
                        final Inventory inv2 = Bukkit.createInventory(BlockEditor.woolholder, 27, BlockEditor.plugin.getStringFromConfig("wooledit"));
                        for (int j = 0; j < ItemStackList.wooleditor.length; ++j) {
                            inv2.setItem(j, ItemStackList.wooleditor[j]);
                        }
                        final int index = Core.integerCache.get(uuid2)[0] - ItemStackList.sandstoneeditor.length;
                        if (index < ItemStackList.wooleditor.length && index >= 0) {
                            inv2.setItem(index, ItemUtils.glow(ItemUtils.setLore(ItemStackList.wooleditor[index].clone(), Collections.singletonList(GlobalString.selected))));
                        }
                        player.openInventory(inv2);
                        break;
                    }
                    case 3: {
                        player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                        final UUID uuid2 = player.getUniqueId();
                        final Inventory inv2 = Bukkit.createInventory(BlockEditor.oreblockeditor, 27, BlockEditor.plugin.getStringFromConfig("oreblockedit"));
                        for (int j = 0; j < ItemStackList.oreblockeditor.length; ++j) {
                            inv2.setItem(j + 10, ItemStackList.oreblockeditor[j]);
                        }
                        final int index = Core.integerCache.get(uuid2)[0] - (ItemStackList.sandstoneeditor.length + ItemStackList.wooleditor.length);
                        if (index < ItemStackList.oreblockeditor.length && index >= 0) {
                            inv2.setItem(index + 10, ItemUtils.glow(ItemUtils.setLore(ItemStackList.oreblockeditor[index].clone(), Collections.singletonList(GlobalString.selected))));
                        }
                        player.openInventory(inv2);
                        break;
                    }
                    case 4: {
                        player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                        final UUID uuid2 = player.getUniqueId();
                        final Inventory inv2 = Bukkit.createInventory(BlockEditor.oreeditor, 27, BlockEditor.plugin.getStringFromConfig("oreedit"));
                        for (int j = 0; j < ItemStackList.oreeditor.length; ++j) {
                            inv2.setItem(j + 10, ItemStackList.oreeditor[j]);
                        }
                        final int index = Core.integerCache.get(uuid2)[0] - (ItemStackList.sandstoneeditor.length + ItemStackList.wooleditor.length + ItemStackList.oreblockeditor.length);
                        if (index < ItemStackList.oreeditor.length && index >= 0) {
                            inv2.setItem(index + 10, ItemUtils.glow(ItemUtils.setLore(ItemStackList.oreeditor[index].clone(), Collections.singletonList(GlobalString.selected))));
                        }
                        player.openInventory(inv2);
                        break;
                    }
                    case 5: {
                        player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                        final UUID uuid2 = player.getUniqueId();
                        final Inventory inv2 = Bukkit.createInventory(BlockEditor.quartzeditor, 27, BlockEditor.plugin.getStringFromConfig("quartzedit"));
                        for (int j = 0; j < ItemStackList.quartzeditor.length; ++j) {
                            inv2.setItem(j + 12, ItemStackList.quartzeditor[j]);
                        }
                        final int index = Core.integerCache.get(uuid2)[0] - (ItemStackList.sandstoneeditor.length + ItemStackList.wooleditor.length + ItemStackList.oreblockeditor.length + ItemStackList.oreeditor.length);
                        if (index < ItemStackList.quartzeditor.length && index >= 0) {
                            inv2.setItem(index + 12, ItemUtils.glow(ItemUtils.setLore(ItemStackList.quartzeditor[index].clone(), Collections.singletonList(GlobalString.selected))));
                        }
                        player.openInventory(inv2);
                        break;
                    }
                    case 6: {
                        player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                        final UUID uuid = player.getUniqueId();
                        final Inventory inv = Bukkit.createInventory(BlockEditor.woodeditor, 27, BlockEditor.plugin.getStringFromConfig("woodedit"));
                        for (int i = 0; i < 3; ++i) {
                            inv.setItem(i + 1, ItemStackList.woodeditor[i]);
                        }
                        for (int i = 3; i < 6; ++i) {
                            inv.setItem(i + 2, ItemStackList.woodeditor[i]);
                        }
                        for (int i = 6; i < 9; ++i) {
                            inv.setItem(i + 4, ItemStackList.woodeditor[i]);
                        }
                        for (int i = 9; i < 12; ++i) {
                            inv.setItem(i + 5, ItemStackList.woodeditor[i]);
                        }
                        final int index2 = Core.integerCache.get(uuid)[0] - (ItemStackList.sandstoneeditor.length + ItemStackList.wooleditor.length + ItemStackList.oreblockeditor.length + ItemStackList.oreeditor.length + ItemStackList.quartzeditor.length);
                        if (index2 < ItemStackList.woodeditor.length && index2 >= 0) {
                            inv.setItem((index2 < 3) ? (index2 + 1) : ((index2 < 6) ? (index2 + 2) : ((index2 < 9) ? (index2 + 4) : (index2 + 5))), ItemUtils.glow(ItemUtils.setLore(ItemStackList.woodeditor[index2].clone(), Collections.singletonList(GlobalString.selected))));
                        }
                        player.openInventory(inv);
                        break;
                    }
                    case 7: {
                        player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                        final UUID uuid2 = player.getUniqueId();
                        final Inventory inv2 = Bukkit.createInventory(BlockEditor.clayeditor, 27, BlockEditor.plugin.getStringFromConfig("clayedit"));
                        for (int j = 0; j < ItemStackList.clayeditor.length; ++j) {
                            inv2.setItem(j, ItemStackList.clayeditor[j]);
                        }
                        final int index = Core.integerCache.get(uuid2)[0] - (ItemStackList.sandstoneeditor.length + ItemStackList.wooleditor.length + ItemStackList.oreblockeditor.length + ItemStackList.oreeditor.length + ItemStackList.quartzeditor.length + ItemStackList.woodeditor.length);
                        if (index < ItemStackList.clayeditor.length && index >= 0) {
                            inv2.setItem(index, ItemUtils.glow(ItemUtils.setLore(ItemStackList.clayeditor[index].clone(), Collections.singletonList(GlobalString.selected))));
                        }
                        player.openInventory(inv2);
                        break;
                    }
                }
            }
        }
        else if (holder == BlockEditor.sandstoneholder) {
            e.setCancelled(true);
            if (RawSlot > 9 && RawSlot < 17 && RawSlot != 13) {
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                final int x = (RawSlot > 12) ? (RawSlot - 11) : (RawSlot - 10);
                final UUID uuid = player.getUniqueId();
                Core.integerCache.get(uuid)[0] = x;
                player.closeInventory();
                final ItemStack stack = ItemStackList.Blocks[x];
                player.getInventory().setItem(0, stack);
                player.getInventory().setItem(1, stack);
                player.sendMessage(GlobalString.SavedSettings);
            }
        }
        else if (holder == BlockEditor.woolholder) {
            e.setCancelled(true);
            if (RawSlot < 16) {
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                final int x = RawSlot + ItemStackList.sandstoneeditor.length;
                final UUID uuid = player.getUniqueId();
                Core.integerCache.get(uuid)[0] = x;
                player.closeInventory();
                final ItemStack stack = ItemStackList.Blocks[x];
                player.getInventory().setItem(0, stack);
                player.getInventory().setItem(1, stack);
                player.sendMessage(GlobalString.SavedSettings);
            }
        }
        else if (holder == BlockEditor.oreblockeditor) {
            e.setCancelled(true);
            if (RawSlot <= 16 && RawSlot >= 10) {
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                final int x = RawSlot + ItemStackList.sandstoneeditor.length + ItemStackList.wooleditor.length - 10;
                final UUID uuid = player.getUniqueId();
                Core.integerCache.get(uuid)[0] = x;
                player.closeInventory();
                final ItemStack stack = ItemStackList.Blocks[x];
                player.getInventory().setItem(0, stack);
                player.getInventory().setItem(1, stack);
                player.sendMessage(GlobalString.SavedSettings);
            }
        }
        else if (holder == BlockEditor.oreeditor) {
            e.setCancelled(true);
            if (RawSlot <= 16 && RawSlot >= 10) {
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                final int x = RawSlot + ItemStackList.sandstoneeditor.length + ItemStackList.wooleditor.length + ItemStackList.oreblockeditor.length - 10;
                final UUID uuid = player.getUniqueId();
                Core.integerCache.get(uuid)[0] = x;
                player.closeInventory();
                final ItemStack stack = ItemStackList.Blocks[x];
                player.getInventory().setItem(0, stack);
                player.getInventory().setItem(1, stack);
                player.sendMessage(GlobalString.SavedSettings);
            }
        }
        else if (holder == BlockEditor.quartzeditor) {
            e.setCancelled(true);
            if (RawSlot <= 14 && RawSlot >= 12) {
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                final int x = RawSlot + ItemStackList.sandstoneeditor.length + ItemStackList.wooleditor.length + ItemStackList.oreblockeditor.length + ItemStackList.oreeditor.length - 12;
                final UUID uuid = player.getUniqueId();
                Core.integerCache.get(uuid)[0] = x;
                player.closeInventory();
                final ItemStack stack = ItemStackList.Blocks[x];
                player.getInventory().setItem(0, stack);
                player.getInventory().setItem(1, stack);
                player.sendMessage(GlobalString.SavedSettings);
            }
        }
        else if (holder == BlockEditor.woodeditor) {
            e.setCancelled(true);
            if (RawSlot <= 16 && RawSlot >= 1 && RawSlot != 13 && RawSlot != 4 && RawSlot != 8 && RawSlot != 9) {
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                final int x = ((RawSlot < 4) ? (RawSlot - 1) : ((RawSlot < 8) ? (RawSlot - 2) : ((RawSlot < 13) ? (RawSlot - 4) : (RawSlot - 5)))) + ItemStackList.sandstoneeditor.length + ItemStackList.wooleditor.length + ItemStackList.oreblockeditor.length + ItemStackList.oreeditor.length + ItemStackList.quartzeditor.length;
                final UUID uuid = player.getUniqueId();
                Core.integerCache.get(uuid)[0] = x;
                player.closeInventory();
                final ItemStack stack = ItemStackList.Blocks[x];
                player.getInventory().setItem(0, stack);
                player.getInventory().setItem(1, stack);
                player.sendMessage(GlobalString.SavedSettings);
            }
        }
        else if (holder == BlockEditor.clayeditor) {
            e.setCancelled(true);
            if (RawSlot < 17) {
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                final int x = RawSlot + ItemStackList.sandstoneeditor.length + ItemStackList.wooleditor.length + ItemStackList.oreblockeditor.length + ItemStackList.oreeditor.length + ItemStackList.quartzeditor.length + ItemStackList.woodeditor.length;
                final UUID uuid = player.getUniqueId();
                Core.integerCache.get(uuid)[0] = x;
                player.closeInventory();
                final ItemStack stack = ItemStackList.Blocks[x];
                player.getInventory().setItem(0, stack);
                player.getInventory().setItem(1, stack);
                player.sendMessage(GlobalString.SavedSettings);
            }
        }
    }
    
    private static /* synthetic */ Inventory lambda$static$7() {
        return null;
    }
    
    private static /* synthetic */ Inventory lambda$static$6() {
        return null;
    }
    
    private static /* synthetic */ Inventory lambda$static$5() {
        return null;
    }
    
    private static /* synthetic */ Inventory lambda$static$4() {
        return null;
    }
    
    private static /* synthetic */ Inventory lambda$static$3() {
        return null;
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
        BlockHolder = (() -> null);
        sandstoneholder = (() -> null);
        woolholder = (() -> null);
        oreblockeditor = (() -> null);
        oreeditor = (() -> null);
        quartzeditor = (() -> null);
        woodeditor = (() -> null);
        clayeditor = (() -> null);
    }
}
