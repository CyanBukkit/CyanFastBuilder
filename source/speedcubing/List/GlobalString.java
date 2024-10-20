package speedcubing.List;

import speedcubing.*;
import org.bukkit.plugin.java.*;

public class GlobalString
{
    private static final Main plugin;
    public static String selected;
    public static String SavedSettings;
    
    public GlobalString() {
        super();
    }
    
    static {
        plugin = (Main)JavaPlugin.getPlugin((Class)Main.class);
        GlobalString.selected = GlobalString.plugin.getStringFromConfig("selected");
        GlobalString.SavedSettings = GlobalString.plugin.getStringFromConfig("SavedSettings");
    }
}
