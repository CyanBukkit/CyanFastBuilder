package speedcubing.Core;

import java.util.*;
import org.bukkit.entity.*;

class Long$1 extends TimerTask {
    private final /* synthetic */ Player val$player;
    
    Long$1(final Player val$player) {
        this.val$player = val$player;
        super();
    }
    
    @Override
    public void run() {
        BungeePluginMessage.switchServer(this.val$player, "lobby");
    }
}