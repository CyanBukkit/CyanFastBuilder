package speedcubing.BukkitEventListener;

import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import speedcubing.List.*;
import speedcubing.Core.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.*;

public class InventoryClick implements Listener
{
    public InventoryClick() {
        super();
    }
    
    @EventHandler
    public void InventoryClickEvent(final InventoryClickEvent e) {
        final Player player = (Player)e.getWhoClicked();
        final Inventory inventory = e.getInventory();
        final int RawSlot = e.getRawSlot();
        final InventoryHolder holder = inventory.getHolder();
        if (RawSlot != 999) {
            if (inventory.getName().equals("container.crafting")) {
                if (player.getGameMode() != GameMode.CREATIVE) {
                    e.setCancelled(true);
                }
            }
            else if (holder == ItemStackList.menu) {
                e.setCancelled(true);
                switch (RawSlot) {
                    case 10: {
                        if (player.getWorld().getName().equals("Long")) {
                            break;
                        }
                        Long.Join(player, false);
                        break;
                    }
                    case 11: {
                        if (player.getWorld().getName().equals("Short")) {
                            break;
                        }
                        Short.Join(player);
                        break;
                    }
                    case 13: {
                        if (player.getWorld().getName().equals("Onestack")) {
                            break;
                        }
                        OneStack.Join(player);
                        break;
                    }
                    case 15: {
                        if (player.getWorld().getName().equals("Inclined")) {
                            break;
                        }
                        Inclined.Join(player);
                        break;
                    }
                    case 16: {
                        if (player.getWorld().getName().equals("Inclinedshort")) {
                            break;
                        }
                        InclinedShort.Join(player);
                        break;
                    }
                }
            }
            else if (holder == ItemStackList.settings) {
                e.setCancelled(true);
                switch (RawSlot) {
                    case 11: {
                        switch (e.getAction()) {
                            case PICKUP_ALL: {
                                final UUID uuid = player.getUniqueId();
                                player.playSound(player.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                                final int x = Core.integerCache.get(uuid)[1];
                                if (x != 1) {
                                    Core.integerCache.get(uuid)[1] = x - 1;
                                    final ItemStack stack = inventory.getItem(RawSlot);
                                    final ItemMeta meta = stack.getItemMeta();
                                    final String[] str = StringList.FallDelayLore;
                                    meta.setLore((List)Arrays.asList(str[1], str[2].replace("%int%", Integer.toString(x - 1)), str[3], str[4], str[5]));
                                    stack.setItemMeta(meta);
                                    break;
                                }
                                player.sendMessage(StringList.min);
                                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                                break;
                            }
                            case PICKUP_HALF: {
                                final UUID uuid = player.getUniqueId();
                                player.playSound(player.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                                final int x = Core.integerCache.get(uuid)[1];
                                if (x != 20) {
                                    Core.integerCache.get(uuid)[1] = x + 1;
                                    final ItemStack stack = inventory.getItem(RawSlot);
                                    final ItemMeta meta = stack.getItemMeta();
                                    final String[] str = StringList.FallDelayLore;
                                    meta.setLore((List)Arrays.asList(str[1], str[2].replace("%int%", Integer.toString(x + 1)), str[3], str[4], str[5]));
                                    stack.setItemMeta(meta);
                                    break;
                                }
                                player.sendMessage(StringList.max);
                                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                                break;
                            }
                        }
                        break;
                    }
                    case 13: {
                        BlockEditor.createMainPage(player);
                        break;
                    }
                    case 15: {
                        player.openInventory(ItemStackList.ResetConfirm);
                        break;
                    }
                }
            }
            else if (holder == ItemStackList.confirm) {
                e.setCancelled(true);
                switch (RawSlot) {
                    case 11: {
                        final UUID uuid = player.getUniqueId();
                        int index = -1;
                        final String name = player.getWorld().getName();
                        switch (name) {
                            case "Long": {
                                index = 2;
                                break;
                            }
                            case "Short": {
                                index = 3;
                                break;
                            }
                            case "Inclined": {
                                index = 4;
                                break;
                            }
                            case "Onestack": {
                                index = 5;
                                break;
                            }
                            case "Inclinedshort": {
                                index = 6;
                                break;
                            }
                        }
                        Core.integerCache.get(uuid)[index] = 0;
                        player.getScoreboard().getTeam(StringList.SideBar[9]).setSuffix("¡ì7-,---");
                        player.closeInventory();
                        break;
                    }
                    case 15: {
                        player.closeInventory();
                        break;
                    }
                }
            }
            else {
                BlockEditor.select(holder, e, RawSlot, player);
            }
        }
    }
}
