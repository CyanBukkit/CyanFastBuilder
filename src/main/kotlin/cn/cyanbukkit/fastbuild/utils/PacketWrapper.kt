package cn.cyanbukkit.fastbuild.utils

import cn.cyanbukkit.fastbuild.utils.Reflections.packetSetValue
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardTeam;


object PacketWrapper {

    fun packetPlayOutScreboardTeam(
        team: String?,
        prefix: String?,
        suffix: String?,
        Enum: String?,
        entries: Collection<String?>?,
        JoinorLeave: Int
    ): PacketPlayOutScoreboardTeam {
        val packet: PacketPlayOutScoreboardTeam = PacketPlayOutScoreboardTeam()
        if (team != null) packetSetValue(packet, "a", team)
        if (prefix != null) packetSetValue(packet, "c", prefix)
        if (suffix != null) packetSetValue(packet, "d", suffix)
        if (Enum != null) packetSetValue(packet, "e", Enum)
        if (entries != null) packetSetValue(packet, "g", entries)
        if (JoinorLeave != -1) packetSetValue(packet, "h", JoinorLeave)
        return packet
    }

    fun packetPlayOutEntityHeadRotation(id: Int, Byte: Byte): PacketPlayOutEntityHeadRotation {
        val packet: PacketPlayOutEntityHeadRotation = PacketPlayOutEntityHeadRotation()
        packetSetValue(packet, "a", id)
        packetSetValue(packet, "b", Byte)
        return packet
    }

    fun packetPlayOutAnimation(id: Int, animation: Byte): PacketPlayOutAnimation {
        val packet: PacketPlayOutAnimation = PacketPlayOutAnimation()
        packetSetValue(packet, "a", id)
        packetSetValue(packet, "b", animation)
        return packet
    }

    fun PacketPlayOutPlayerListHeaderFooter(header: String?, footer: String?): PacketPlayOutPlayerListHeaderFooter {
        val packet: PacketPlayOutPlayerListHeaderFooter = PacketPlayOutPlayerListHeaderFooter()
        packetSetValue(packet, "a", ChatComponentText(header))
        packetSetValue(packet, "b", ChatComponentText(footer))
        return packet
    }


}