package cubing.api;

import java.net.*;
import java.io.*;
import com.google.gson.*;
import java.util.*;

public class SessionServer
{
    public SessionServer() {
        super();
    }
    
    public static String[] getSkin(final String uuid) {
        try {
            final URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
            final JsonElement element = new JsonParser().parse(new InputStreamReader(url.openStream()));
            if (element == null) {
                return new String[] { "", "" };
            }
            final JsonObject property = element.getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
            return new String[] { property.get("value").getAsString(), property.get("signature").getAsString() };
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String[] getSkin(final UUID uuid) {
        try {
            final URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
            final JsonElement element = new JsonParser().parse(new InputStreamReader(url.openStream()));
            if (element == null) {
                return new String[] { "", "" };
            }
            final JsonObject property = element.getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
            return new String[] { property.get("value").getAsString(), property.get("signature").getAsString() };
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
