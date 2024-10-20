package speedcubing.BukkitEventListener;

import org.bukkit.event.entity.*;
import org.bukkit.event.*;

public class FoodLevelChange implements Listener
{
    public FoodLevelChange() {
        super();
    }
    
    @EventHandler
    public void FoodLevelChangeEvent(final FoodLevelChangeEvent e) {
        e.setFoodLevel(20);
    }
}
