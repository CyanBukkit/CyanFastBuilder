package cubing.api;

import java.net.*;
import org.apache.commons.io.*;
import java.util.*;

public class NameMcAPI
{
    public NameMcAPI() {
        super();
    }
    
    public static boolean likedServer(final String uuid, final String ip) {
        try {
            return Boolean.parseBoolean(IOUtils.toString(new URL("https://api.namemc.com/server/" + ip + "/likes?profile=" + uuid)));
        }
        catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean likedServer(final UUID uuid, final String ip) {
        try {
            return Boolean.parseBoolean(IOUtils.toString(new URL("https://api.namemc.com/server/" + ip + "/likes?profile=" + uuid)));
        }
        catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
