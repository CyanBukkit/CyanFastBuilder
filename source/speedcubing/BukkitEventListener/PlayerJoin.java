package speedcubing.BukkitEventListener;

import org.bukkit.event.player.*;
import speedcubing.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.event.*;

public class PlayerJoin implements Listener
{
    public PlayerJoin() {
        super();
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerJoinEvent(final PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        if (!player.getWorld().getName().equals("world")) {
            player.teleport(new Location(Bukkit.getWorld("world"), 0.5, 100.0, 0.5));
        }
        final UUID uuid = player.getUniqueId();
        if (((Main)Main.getPlugin((Class)Main.class)).getConfig().get("stats." + uuid) == null) {
            ((Main)Main.getPlugin((Class)Main.class)).getConfig().set("stats." + uuid, (Object)"0/5/0/0/0/0/0");
            ((Main)Main.getPlugin((Class)Main.class)).saveConfig();
        }
        player.setGameMode(GameMode.SURVIVAL);
        Long.Join(player, true);
    }
}
