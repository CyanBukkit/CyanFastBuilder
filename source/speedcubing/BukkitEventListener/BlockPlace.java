package speedcubing.BukkitEventListener;

import org.bukkit.event.block.*;
import org.bukkit.*;
import speedcubing.*;
import org.bukkit.plugin.*;
import net.minecraft.server.v1_8_R3.*;
import speedcubing.Core.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class BlockPlace implements Listener
{
    public BlockPlace() {
        super();
    }
    
    @EventHandler
    public void BlockPlaceEvent(final BlockPlaceEvent e) {
        final Block block = e.getBlock();
        final Player player = e.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE) {
            if (Core.waiting.contains(player.getUniqueId()) || block.getType().equals((Object)Material.REDSTONE_COMPARATOR_OFF)) {
                e.setCancelled(true);
                return;
            }
            final int x = block.getX();
            final int y = block.getY();
            final int z = block.getZ();
            final String name = player.getWorld().getName();
            switch (name) {
                case "Long": {
                    if (x < 3 || x > 56 || Math.abs(z - Long.PlayerCoord.get(player.getUniqueId())) > 6 || y < 92) {
                        e.setCancelled(true);
                        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getPlugin((Class)Main.class), () -> player.sendBlockChange(block.getLocation(), Material.AIR, (byte)0), 10L);
                        break;
                    }
                    Long.validPlaceBlock(player, new BlockPosition(x, y, z));
                    break;
                }
                case "Short": {
                    if (x < 3 || x > 33 || Math.abs(z - Short.PlayerCoord.get(player.getUniqueId())) > 6 || y < 92) {
                        e.setCancelled(true);
                        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getPlugin((Class)Main.class), () -> player.sendBlockChange(block.getLocation(), Material.AIR, (byte)0), 10L);
                        break;
                    }
                    Short.validPlaceBlock(player, new BlockPosition(x, y, z));
                    break;
                }
                case "Inclined": {
                    if (Math.abs(x + z - 2 * Inclined.PlayerCoord.get(player.getUniqueId())) > 10 || x - z < 1 || x - z > 58 || y < 92) {
                        e.setCancelled(true);
                        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getPlugin((Class)Main.class), () -> player.sendBlockChange(block.getLocation(), Material.AIR, (byte)0), 10L);
                        break;
                    }
                    Inclined.validPlaceBlock(player, new BlockPosition(x, y, z));
                    break;
                }
                case "Onestack": {
                    if (x < 3 || x > 33 || Math.abs(z - OneStack.PlayerCoord.get(player.getUniqueId())) > 6 || y < 92) {
                        e.setCancelled(true);
                        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getPlugin((Class)Main.class), () -> player.sendBlockChange(block.getLocation(), Material.AIR, (byte)0), 10L);
                        break;
                    }
                    OneStack.validPlaceBlock(player, new BlockPosition(x, y, z));
                    break;
                }
                case "Inclinedshort": {
                    if (Math.abs(x + z - 2 * InclinedShort.PlayerCoord.get(player.getUniqueId())) > 10 || x - z < 1 || x - z > 38 || y < 92) {
                        e.setCancelled(true);
                        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getPlugin((Class)Main.class), () -> player.sendBlockChange(block.getLocation(), Material.AIR, (byte)0), 10L);
                        break;
                    }
                    InclinedShort.validPlaceBlock(player, new BlockPosition(x, y, z));
                    break;
                }
            }
        }
    }
    
    private static /* synthetic */ void lambda$BlockPlaceEvent$4(final Player player, final Block block) {
        player.sendBlockChange(block.getLocation(), Material.AIR, (byte)0);
    }
    
    private static /* synthetic */ void lambda$BlockPlaceEvent$3(final Player player, final Block block) {
        player.sendBlockChange(block.getLocation(), Material.AIR, (byte)0);
    }
    
    private static /* synthetic */ void lambda$BlockPlaceEvent$2(final Player player, final Block block) {
        player.sendBlockChange(block.getLocation(), Material.AIR, (byte)0);
    }
    
    private static /* synthetic */ void lambda$BlockPlaceEvent$1(final Player player, final Block block) {
        player.sendBlockChange(block.getLocation(), Material.AIR, (byte)0);
    }
    
    private static /* synthetic */ void lambda$BlockPlaceEvent$0(final Player player, final Block block) {
        player.sendBlockChange(block.getLocation(), Material.AIR, (byte)0);
    }
}
