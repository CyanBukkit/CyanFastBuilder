package cn.cyanbukkit.fastbuild.player

import cn.cyanbukkit.fastbuild.CyanFastBuilder
import cn.cyanbukkit.fastbuild.arena.Long
import cn.cyanbukkit.fastbuild.core.Core
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent


object PlayerJoinQuit : Listener {

    @EventHandler
    fun onPlayerQuitEvent(e: PlayerQuitEvent) {
        val player = e.player
        player.teleport(Location(Bukkit.getWorld("world"), 0.5, 100.0, 0.5, 0.0f, 0.0f))
        val uuid = player.uniqueId
        val x = Core.integerCache[uuid]!!
        CyanFastBuilder.instance.config.set(
            "stats.$uuid",
            x[0].toString() + "/" + x[1] + "/" + x[2] + "/" + x[3] + "/" + x[4] + "/" + x[5] + "/" + x[6]
        )
        CyanFastBuilder.instance.saveConfig()
        Core.integerCache.remove(uuid)
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerJoinEvent(e: PlayerJoinEvent) {
        val player = e.player
        if (player.world.name != "world") player.teleport(Location(Bukkit.getWorld("world"), 0.5, 100.0, 0.5))
        val uuid = player.uniqueId
        if (CyanFastBuilder.instance.config.get("stats.$uuid") == null) {
            CyanFastBuilder.instance.config.set("stats.$uuid", "0/5/0/0/0/0/0")
            CyanFastBuilder.instance.saveConfig()
        }
        player.gameMode = GameMode.SURVIVAL
        Long.join(player, true)
    }


}