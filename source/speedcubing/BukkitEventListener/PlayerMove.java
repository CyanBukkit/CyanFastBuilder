package speedcubing.BukkitEventListener;

import org.bukkit.event.player.*;
import speedcubing.Core.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.event.*;

public class PlayerMove implements Listener
{
    public PlayerMove() {
        super();
    }
    
    @EventHandler
    public void PlayerMoveEvent(final PlayerMoveEvent e) {
        final Player player = e.getPlayer();
        final Location to = e.getTo();
        if (player.getGameMode() != GameMode.CREATIVE) {
            final String name = player.getWorld().getName();
            switch (name) {
                case "Long": {
                    final UUID uuid = player.getUniqueId();
                    final boolean bl;
                    final boolean fell = bl = (to.getY() <= 100 - Core.integerCache.get(uuid)[1]);
                    if (!fell && Math.abs(to.getZ() - Long.PlayerCoord.get(uuid) - 0.5) <= 5.5) {
                        break;
                    }
                    Long.Fell(player, fell);
                    break;
                }
                case "Short": {
                    final UUID uuid = player.getUniqueId();
                    final boolean bl;
                    final boolean fell = bl = (to.getY() <= 100 - Core.integerCache.get(uuid)[1]);
                    if (!fell && Math.abs(to.getZ() - Short.PlayerCoord.get(uuid) - 0.5) <= 5.5) {
                        break;
                    }
                    Short.Fell(player, fell);
                    break;
                }
                case "Inclined": {
                    final UUID uuid = player.getUniqueId();
                    final boolean bl;
                    final boolean fell = bl = (to.getY() <= 100 - Core.integerCache.get(uuid)[1]);
                    if (!fell && Math.abs(to.getX() + to.getZ() - 2 * Inclined.PlayerCoord.get(uuid) - 1.0) <= 7.5) {
                        break;
                    }
                    Inclined.Fell(player, fell);
                    break;
                }
                case "Onestack": {
                    final UUID uuid = player.getUniqueId();
                    final boolean bl;
                    final boolean fell = bl = (to.getY() <= 100 - Core.integerCache.get(uuid)[1]);
                    if (!fell && Math.abs(to.getZ() - OneStack.PlayerCoord.get(uuid) - 0.5) <= 5.5) {
                        break;
                    }
                    OneStack.Fell(player, fell);
                    break;
                }
                case "Inclinedshort": {
                    final UUID uuid = player.getUniqueId();
                    final boolean bl;
                    final boolean fell = bl = (to.getY() <= 100 - Core.integerCache.get(uuid)[1]);
                    if (!fell && Math.abs(to.getX() + to.getZ() - 2 * InclinedShort.PlayerCoord.get(uuid) - 1.0) <= 7.5) {
                        break;
                    }
                    InclinedShort.Fell(player, fell);
                    break;
                }
            }
        }
    }
}
