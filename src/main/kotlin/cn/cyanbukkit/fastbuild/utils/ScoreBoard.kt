package cn.cyanbukkit.fastbuild.utils

import cn.cyanbukkit.fastbuild.CyanFastBuilder
import cn.cyanbukkit.fastbuild.score.BoardAdapter
import org.bukkit.entity.Player
import java.util.*

object ScoreBoard : BoardAdapter {
    val lines = mutableMapOf<UUID, MutableList<String>>()

    override fun getTitle(): String {
        return CyanFastBuilder.instance.config.getString("Scoreboard.title")!!
    }

    override fun getStrings(p: Player): MutableList<String> {
        return if (lines.containsKey(p.uniqueId)) lines[p.uniqueId]!! else mutableListOf()
    }
}