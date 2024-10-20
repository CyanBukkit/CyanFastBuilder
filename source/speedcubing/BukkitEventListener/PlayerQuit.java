package speedcubing.BukkitEventListener;

import org.bukkit.event.player.*;
import org.bukkit.*;
import speedcubing.Core.*;
import speedcubing.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.event.*;

public class PlayerQuit implements Listener
{
    public PlayerQuit() {
        super();
    }
    
    @EventHandler
    public void PlayerQuitEvent(final PlayerQuitEvent e) {
        final Player player = e.getPlayer();
        player.teleport(new Location(Bukkit.getWorld("world"), 0.5, 100.0, 0.5, 0.0f, 0.0f));
        final UUID uuid = player.getUniqueId();
        final Integer[] x = Core.integerCache.get(uuid);
        ((Main)Main.getPlugin((Class)Main.class)).getConfig().set("stats." + uuid, (Object)(x[0] + "/" + x[1] + "/" + x[2] + "/" + x[3] + "/" + x[4] + "/" + x[5] + "/" + x[6]));
        ((Main)Main.getPlugin((Class)Main.class)).saveConfig();
        Core.integerCache.remove(uuid);
    }
}
