package speedcubing.BukkitEventListener;

import org.bukkit.event.block.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class BlockBreak implements Listener
{
    public BlockBreak() {
        super();
    }
    
    @EventHandler
    public void BlockBreakEvent(final BlockBreakEvent e) {
        final Player player = e.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }
}
