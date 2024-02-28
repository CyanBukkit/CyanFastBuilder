package cn.cyanbukkit.fastbuild.world

import cn.cyanbukkit.fastbuild.CyanFastBuilder
import cn.cyanbukkit.fastbuild.arena.Long
import cn.cyanbukkit.fastbuild.core.Core
import net.minecraft.server.v1_8_R3.BlockPosition
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import kotlin.math.abs


object PlaceBreakListener : Listener {

    @EventHandler
    fun onBlockPlace(e: BlockPlaceEvent) {
        val block  = e.block
        val player  = e.player
        if (player.gameMode != GameMode.CREATIVE) {
            if (Core.waiting.contains(player.uniqueId)
                || block.type == Material.REDSTONE_COMPARATOR_OFF) {
                e.isCancelled = true
                return
            }
            val x = block.x
            val y = block.y
            val z = block.z
            val name = player.world.name
            when (name) {
                "Long" -> {
                    if (x < 3 || x > 56 || abs(z - Long.PlayerCoord[player.uniqueId]!!) > 6 || y < 92) {
                        cancelEvent(e, player, block)
                    } else {
                        Long.validPlaceBlock(player, BlockPosition(x, y, z))
                    }
                }

//                "Short", "Inclinedshort", "Onestack" -> {
//                    if (x < 3 || x > 33 || Math.abs(z - Short.PlayerCoord[player.uniqueId]!!) > 6 || y < 92) {
//                        cancelEvent(e, player, block)
//                    } else {
//                        Short.validPlaceBlock(player, BlockPosition(x, y, z))
//                    }
//                }
//
//                "Inclined" -> {
//                    if (Math.abs((x + z) - (2 * Inclined.PlayerCoord[player.uniqueId]!!)) > 10 || x - z < 1 || x - z > 58 || y < 92) {
//                        cancelEvent(e, player, block)
//                    } else {
//                        Inclined.validPlaceBlock(player, BlockPosition(x, y, z))
//                    }
//                }
            }
        }
    }

    private fun cancelEvent(e: BlockPlaceEvent, player: Player, block: Block) {
        e.isCancelled = true
        Bukkit.getScheduler().scheduleSyncDelayedTask(CyanFastBuilder.instance) {
            player.sendBlockChange(block.location, Material.AIR, 0.toByte())
        }
    }


    @EventHandler
    fun blockBreakEvent(e: BlockBreakEvent) {
        val player = e.player;
        if (player.gameMode != GameMode.CREATIVE) e.isCancelled = true;
    }





}