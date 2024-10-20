package cubing.bukkit;

import net.minecraft.server.v1_8_R3.*;

public class EntityPlayerUtils
{
    public EntityPlayerUtils() {
        super();
    }
    
    public static void enableOuterLayer(final DataWatcher watcher) {
        watcher.watch(10, (Object)127);
    }
}
