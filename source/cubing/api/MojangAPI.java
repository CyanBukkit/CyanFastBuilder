package cubing.api;

import java.net.*;
import java.io.*;
import com.google.gson.*;
import java.util.*;

public class MojangAPI
{
    public MojangAPI() {
        super();
    }
    
    public static String getUUID(final String name) {
        try {
            final URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            final JsonElement element = new JsonParser().parse(new InputStreamReader(url.openStream()));
            return (element instanceof JsonObject && element.getAsJsonObject().has("id")) ? element.getAsJsonObject().get("id").getAsString() : "";
        }
        catch (final ConnectException e) {
            return "";
        }
        catch (final Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
    
    public static String[] getUUIDAndName(final String name) {
        try {
            final URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            final JsonElement element = new JsonParser().parse(new InputStreamReader(url.openStream()));
            if (element instanceof JsonObject) {
                final JsonObject object = element.getAsJsonObject();
                if (object.has("id")) {
                    return new String[] { object.get("id").getAsString(), object.get("name").getAsString() };
                }
            }
            return new String[] { "" };
        }
        catch (final FileNotFoundException e) {
            return new String[] { "" };
        }
        catch (final Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
    
    public static String getLatestName(final String uuid) {
        try {
            final URL url = new URL("https://api.mojang.com/user/profiles/" + uuid + "/names");
            final JsonElement element = new JsonParser().parse(new InputStreamReader(url.openStream()));
            if (element == null) {
                return "";
            }
            final JsonArray array = element.getAsJsonArray();
            return array.get(array.size() - 1).getAsJsonObject().get("name").getAsString();
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String getLatestName(final UUID uuid) {
        try {
            final URL url = new URL("https://api.mojang.com/user/profiles/" + uuid + "/names");
            final JsonElement element = new JsonParser().parse(new InputStreamReader(url.openStream()));
            if (element == null) {
                return "";
            }
            final JsonArray array = element.getAsJsonArray();
            return array.get(array.size() - 1).getAsJsonObject().get("name").getAsString();
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
