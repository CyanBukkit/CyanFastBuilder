package speedcubing.BukkitEventListener;

import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import speedcubing.Core.*;
import org.bukkit.event.*;

public class PlayerChangedWorld implements Listener
{
    public PlayerChangedWorld() {
        super();
    }
    
    @EventHandler
    public void PlayerChangedWorldEvent(final PlayerChangedWorldEvent e) {
        final Player player = e.getPlayer();
        if (player.getWorld().getName().equals("Long")) {
            for (final Player p : player.getWorld().getPlayers()) {
                p.hidePlayer(player);
                p.showPlayer(player);
            }
        }
        final String name = e.getFrom().getName();
        switch (name) {
            case "Long": {
                Long.Quit(player);
                break;
            }
            case "Short": {
                Short.Quit(player);
                break;
            }
            case "Inclined": {
                Inclined.Quit(player);
                break;
            }
            case "Onestack": {
                OneStack.Quit(player);
                break;
            }
            case "Inclinedshort": {
                InclinedShort.Quit(player);
                break;
            }
        }
    }
}
