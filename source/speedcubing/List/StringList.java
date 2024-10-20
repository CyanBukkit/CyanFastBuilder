package speedcubing.List;

import speedcubing.*;
import org.bukkit.plugin.java.*;

public class StringList
{
    private static final Main plugin;
    public static final String[] typename;
    public static final String MenuTitle;
    public static String min;
    public static String max;
    public static String[] FallDelayLore;
    public static final String[] SideBar;
    public static String nomapsavaliable;
    public static String cancelled;
    
    public StringList() {
        super();
    }
    
    static {
        plugin = (Main)JavaPlugin.getPlugin((Class)Main.class);
        typename = StringList.plugin.getStringListFromConfig("typename");
        MenuTitle = StringList.plugin.getStringFromConfig("MenuTitle");
        StringList.min = StringList.plugin.getStringFromConfig("min");
        StringList.max = StringList.plugin.getStringFromConfig("max");
        StringList.FallDelayLore = StringList.plugin.getStringListFromConfig("FallDelayLore");
        SideBar = StringList.plugin.getStringListFromConfig("SideBar");
        StringList.nomapsavaliable = "¡ì7[¡ìbFastBuilder¡ì7] ¡ìcWe couldn't find any available slot.";
        StringList.cancelled = "¡ì7[¡ìbFastBuilder¡ì7] ¡ìc The bridging attempt is cancelled! (timed out)";
    }
}
