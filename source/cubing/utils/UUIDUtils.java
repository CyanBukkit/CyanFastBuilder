package cubing.utils;

import java.util.*;

public class UUIDUtils
{
    public UUIDUtils() {
        super();
    }
    
    public static String addDash(final String uuid) {
        return uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16) + "-" + uuid.substring(16, 20) + "-" + uuid.substring(20);
    }
    
    public static String removeDash(final String uuid) {
        return uuid.replace("-", "");
    }
    
    public static String removeDash(final UUID uuid) {
        return uuid.toString().replace("-", "");
    }
}
