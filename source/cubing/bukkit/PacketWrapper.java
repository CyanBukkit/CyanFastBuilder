package cubing.bukkit;

import java.util.*;
import cubing.utils.*;
import net.minecraft.server.v1_8_R3.*;

public class PacketWrapper
{
    public PacketWrapper() {
        super();
    }
    
    public static PacketPlayOutScoreboardTeam packetPlayOutScreboardTeam(final String team, final String prefix, final String suffix, final String Enum, final Collection<String> entries, final int JoinorLeave) {
        final PacketPlayOutScoreboardTeam packet = new PacketPlayOutScoreboardTeam();
        if (team != null) {
            Reflections.packetSetValue(packet, "a", team);
        }
        if (prefix != null) {
            Reflections.packetSetValue(packet, "c", prefix);
        }
        if (suffix != null) {
            Reflections.packetSetValue(packet, "d", suffix);
        }
        if (Enum != null) {
            Reflections.packetSetValue(packet, "e", Enum);
        }
        if (entries != null) {
            Reflections.packetSetValue(packet, "g", entries);
        }
        if (JoinorLeave != -1) {
            Reflections.packetSetValue(packet, "h", JoinorLeave);
        }
        return packet;
    }
    
    public static PacketPlayOutEntityHeadRotation packetPlayOutEntityHeadRotation(final int id, final byte Byte) {
        final PacketPlayOutEntityHeadRotation packet = new PacketPlayOutEntityHeadRotation();
        Reflections.packetSetValue(packet, "a", id);
        Reflections.packetSetValue(packet, "b", Byte);
        return packet;
    }
    
    public static PacketPlayOutAnimation packetPlayOutAnimation(final int id, final byte animation) {
        final PacketPlayOutAnimation packet = new PacketPlayOutAnimation();
        Reflections.packetSetValue(packet, "a", id);
        Reflections.packetSetValue(packet, "b", animation);
        return packet;
    }
    
    public static PacketPlayOutPlayerListHeaderFooter PacketPlayOutPlayerListHeaderFooter(final String header, final String footer) {
        final PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        Reflections.packetSetValue(packet, "a", new ChatComponentText(header));
        Reflections.packetSetValue(packet, "b", new ChatComponentText(footer));
        return packet;
    }
}
