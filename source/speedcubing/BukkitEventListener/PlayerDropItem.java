package speedcubing.BukkitEventListener;

import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class PlayerDropItem implements Listener
{
    public PlayerDropItem() {
        super();
    }
    
    @EventHandler
    public void PlayerDropItemEvent(final PlayerDropItemEvent e) {
        final Player player = e.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }
}
