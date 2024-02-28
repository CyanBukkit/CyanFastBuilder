package cn.cyanbukkit.fastbuild.player

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.FoodLevelChangeEvent



object PlayerHealFood : org.bukkit.event.Listener {

    @EventHandler
    fun foodLevelChangeEvent(e: FoodLevelChangeEvent) {
        e.foodLevel = 20
    }


    @EventHandler
    fun entityDamageEvent(e: EntityDamageEvent) {
        if (e.entity is Player) e.isCancelled = true
    }


}