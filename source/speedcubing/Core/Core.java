package speedcubing.Core;

import java.util.*;

public class Core
{
    public static Set<UUID> waiting;
    public static Map<UUID, Integer[]> integerCache;
    
    public Core() {
        super();
    }
    
    public static String msToTime(final int ms) {
        return String.format("%.3f", ms / 1000.0);
    }
    
    public static String tickToTime(final int tick) {
        return String.format("%.3f", tick * 0.05);
    }
    
    public static int cal(int oldroom, final boolean left, final boolean[] b) {
        final int l = b.length;
        if (left) {
            while (oldroom != l - 2) {
                if (!b[oldroom += ((oldroom == 0) ? 1 : ((oldroom % 2 == 0) ? -2 : 2))]) {
                    continue;
                }
                return oldroom;
            }
        }
        else {
            while (oldroom != l - 1) {
                if (!b[oldroom += ((oldroom == 1) ? -1 : ((oldroom % 2 == 0) ? 2 : -2))]) {
                    continue;
                }
                return oldroom;
            }
        }
        return -1;
    }
    
    static {
        Core.waiting = new HashSet<UUID>();
        Core.integerCache = new HashMap<UUID, Integer[]>();
    }
}
