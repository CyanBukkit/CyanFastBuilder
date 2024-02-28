package cn.cyanbukkit.fastbuild.world

import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerDropItemEvent


object HandleEntity : Listener {

    @EventHandler
    fun onPlayerDropItemEvent(e: PlayerDropItemEvent) {
        val player = e.player
        if (player.gameMode != GameMode.CREATIVE) e.isCancelled = true
    }


    @EventHandler
    fun onPlayerChangedWorldEvent(e: PlayerChangedWorldEvent) {
        val player = e.player
        if (player.world.name == "Long") for (p in player.world.players) {
            p.hidePlayer(player)
            p.showPlayer(player)
        }
        when (e.from.name) {
//            "Long" -> Long.quit(player)
//            "Short" -> Short.Quit(player)
//            "Inclined" -> Inclined.Quit(player)
//            "Onestack" -> OneStack.Quit(player)
//            "Inclinedshort" -> InclinedShort.Quit(player)
        }
    }


}