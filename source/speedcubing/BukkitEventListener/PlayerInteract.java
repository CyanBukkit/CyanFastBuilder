package speedcubing.BukkitEventListener;

import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import speedcubing.Core.*;
import org.bukkit.*;
import speedcubing.List.*;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.event.*;

public class PlayerInteract implements Listener
{
    public PlayerInteract() {
        super();
    }
    
    @EventHandler
    public void PlayerInteractEvent(final PlayerInteractEvent e) {
        final Player player = e.getPlayer();
        final UUID uuid = player.getUniqueId();
        final Action action = e.getAction();
        if (Core.waiting.contains(uuid)) {
            return;
        }
        if (player.getGameMode() != GameMode.CREATIVE) {
            if (action.equals((Object)Action.PHYSICAL)) {
                if (e.getClickedBlock().getType() == Material.GOLD_PLATE) {
                    final String name = player.getWorld().getName();
                    switch (name) {
                        case "Long": {
                            Long.Finish(player);
                            break;
                        }
                        case "Short": {
                            Short.Finish(player);
                            break;
                        }
                        case "Inclined": {
                            Inclined.Finish(player);
                            break;
                        }
                        case "Onestack": {
                            OneStack.Finish(player);
                            break;
                        }
                        case "Inclinedshort": {
                            InclinedShort.Finish(player);
                            break;
                        }
                    }
                }
            }
            else {
                switch (player.getItemInHand().getType()) {
                    case REDSTONE_COMPARATOR: {
                        final Inventory inventory = Bukkit.createInventory(ItemStackList.settings, 27, "��cSettings");
                        final String[] hit = StringList.FallDelayLore;
                        inventory.setItem(11, ItemUtils.item(Material.RAW_FISH, hit[0], 1, 3, false, false, null, null, null, Arrays.asList(hit[1], hit[2].replace("%int%", Integer.toString(Core.integerCache.get(player.getUniqueId())[1])), hit[3], hit[4], hit[5])));
                        inventory.setItem(13, ItemStackList.openkit);
                        inventory.setItem(15, ItemStackList.Reset);
                        player.openInventory(inventory);
                        break;
                    }
                    case COMPASS: {
                        final Inventory inventory = Bukkit.createInventory(ItemStackList.menu, 27, "��bFastBuilder Menu");
                        inventory.setItem(10, ItemUtils.item(Material.SANDSTONE, StringList.typename[0], 51, 2, player.getWorld().getName().equals("Long"), false, null, null, null, null));
                        inventory.setItem(11, ItemUtils.item(Material.SANDSTONE, StringList.typename[1], 28, 1, player.getWorld().getName().equals("Short"), false, null, null, null, null));
                        inventory.setItem(13, ItemUtils.item(Material.SANDSTONE_STAIRS, StringList.typename[3], 28, 1, player.getWorld().getName().equals("Onestack"), false, null, null, null, null));
                        inventory.setItem(15, ItemUtils.item(Material.STEP, StringList.typename[2], 50, 1, player.getWorld().getName().equals("Inclined"), false, null, null, null, null));
                        inventory.setItem(16, ItemUtils.item(Material.STEP, StringList.typename[4], 30, 1, player.getWorld().getName().equals("Inclinedshort"), false, null, null, null, null));
                        player.openInventory(inventory);
                        break;
                    }
                }
            }
        }
        if (player.getItemInHand().getType().equals((Object)Material.DIAMOND_PICKAXE) && action.equals((Object)Action.LEFT_CLICK_BLOCK)) {
            final Block b = e.getClickedBlock();
            final BlockPosition position = new BlockPosition(b.getX(), b.getY(), b.getZ());
            final String name2 = player.getWorld().getName();
            switch (name2) {
                case "Long": {
                    if (!Long.Blocks.get(uuid).contains(position)) {
                        break;
                    }
                    b.setType(Material.AIR);
                    Long.Blocks.get(uuid).remove(position);
                    break;
                }
                case "Short": {
                    if (!Short.Blocks.get(uuid).contains(position)) {
                        break;
                    }
                    b.setType(Material.AIR);
                    Short.Blocks.get(uuid).remove(position);
                    break;
                }
                case "Inclined": {
                    if (!Inclined.Blocks.get(uuid).contains(position)) {
                        break;
                    }
                    b.setType(Material.AIR);
                    Inclined.Blocks.get(uuid).remove(position);
                    break;
                }
                case "Onestack": {
                    if (!OneStack.Blocks.get(uuid).contains(position)) {
                        break;
                    }
                    b.setType(Material.AIR);
                    OneStack.Blocks.get(uuid).remove(position);
                    break;
                }
                case "Inclinedshort": {
                    if (!InclinedShort.Blocks.get(uuid).contains(position)) {
                        break;
                    }
                    b.setType(Material.AIR);
                    InclinedShort.Blocks.get(uuid).remove(position);
                    break;
                }
            }
        }
    }
}
