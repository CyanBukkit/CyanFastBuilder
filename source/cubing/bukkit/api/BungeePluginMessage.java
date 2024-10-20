package cubing.bukkit.api;

import org.bukkit.entity.*;
import com.google.common.io.*;
import org.bukkit.*;
import cubing.*;
import org.bukkit.plugin.*;

public class BungeePluginMessage
{
    public BungeePluginMessage() {
        super();
    }
    
    public static void switchServer(final Player player, final String server) {
        final ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        sendPluginMessage(player, "BungeeCord", out.toByteArray());
    }
    
    public static void sendRawMessage(final Player player, final String target, final String text) {
        final ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("MessageRaw");
        out.writeUTF(target);
        out.writeUTF(text);
        sendPluginMessage(player, "BungeeCord", out.toByteArray());
    }
    
    public static void msgPlayerCount(final Player player, String server) {
        if (server == null) {
            server = "ALL";
        }
        final ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF(server);
        sendPluginMessage(player, "BungeeCord", out.toByteArray());
    }
    
    public static void msgPlayerList(final Player player, String server) {
        if (server == null) {
            server = "ALL";
        }
        final ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerList");
        out.writeUTF(server);
        sendPluginMessage(player, "BungeeCord", out.toByteArray());
    }
    
    public static void sendPluginMessage(final Player player, final String channel, final byte[] out) {
        if (player == null) {
            Bukkit.getServer().sendPluginMessage((Plugin)Main.getPlugin((Class)Main.class), channel, out);
        }
        else {
            player.sendPluginMessage((Plugin)Main.getPlugin((Class)Main.class), channel, out);
        }
    }
}
