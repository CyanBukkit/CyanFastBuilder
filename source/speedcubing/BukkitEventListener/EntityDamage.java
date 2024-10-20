package speedcubing.BukkitEventListener;

import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class EntityDamage implements Listener
{
    public EntityDamage() {
        super();
    }
    
    @EventHandler
    public void EntityDamageEvent(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            e.setCancelled(true);
        }
    }
}
