package cn.cyanbukkit.fastbuild

import cn.cyanbukkit.fastbuild.world.PlaceBreakListener
import cn.cyanbukkit.fastbuild.core.ItemStackList
import cn.cyanbukkit.fastbuild.player.PlayerHealFood
import cn.cyanbukkit.fastbuild.player.PlayerJoinQuit
import cn.cyanbukkit.fastbuild.score.BoardManager
import cn.cyanbukkit.fastbuild.utils.ScoreBoard
import cn.cyanbukkit.fastbuild.world.HandleWorld
import org.bukkit.command.Command
import org.bukkit.command.SimpleCommandMap
import org.bukkit.plugin.java.JavaPlugin

class CyanFastBuilder : JavaPlugin() {

    companion object {
        lateinit var instance: CyanFastBuilder

        fun Command.register() {
            val pluginManagerClazz = instance.server.pluginManager.javaClass
            val field = pluginManagerClazz.getDeclaredField("commandMap")
            field.isAccessible = true
            val commandMap = field.get(instance.server.pluginManager) as SimpleCommandMap
            commandMap.register(instance.name, this)
        }

    }

    override fun onEnable() {
        // Start
        instance = this
        BoardManager(this, ScoreBoard)
        ItemStackList.load()
        saveDefaultConfig()
        server.pluginManager.registerEvents(PlaceBreakListener, this)
        server.pluginManager.registerEvents(PlayerHealFood, this)
        server.pluginManager.registerEvents(PlayerJoinQuit, this)
        server.pluginManager.registerEvents(HandleWorld, this)

        logger.info("CyanFastBuilder is enabled!")
        logger.info("CyanFastBuilder by CyanBukkit Code")
    }

    override fun onDisable() {
        logger.info("CyanFastBuilder is disabled!")
        logger.info("CyanFastBuilder by CyanBukkit Code")
    }

}