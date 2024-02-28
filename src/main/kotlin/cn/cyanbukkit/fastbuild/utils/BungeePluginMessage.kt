package cn.cyanbukkit.fastbuild.utils

import cn.cyanbukkit.fastbuild.CyanFastBuilder
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.io.IOException

object BungeePluginMessage {
    fun switchServer(player: Player, s: String) {
        Bukkit.getMessenger().registerOutgoingPluginChannel(CyanFastBuilder.instance, "BungeeCord")
        // 这个怎么办
        val buf = ByteArrayOutputStream()
        val out = DataOutputStream(buf)
        try {
            out.writeUTF("Connect")
            out.writeUTF(s)
            player.sendPluginMessage(CyanFastBuilder.instance, "BungeeCord", buf.toByteArray())
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
    }
}