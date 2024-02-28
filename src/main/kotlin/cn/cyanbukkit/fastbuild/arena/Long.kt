package cn.cyanbukkit.fastbuild.arena

import cn.cyanbukkit.fastbuild.CyanFastBuilder
import cn.cyanbukkit.fastbuild.utils.BungeePluginMessage
import cn.cyanbukkit.fastbuild.core.Core
import cn.cyanbukkit.fastbuild.core.ItemStackList
import cn.cyanbukkit.fastbuild.core.StringList
import cn.cyanbukkit.fastbuild.utils.PlayerUtils
import cn.cyanbukkit.fastbuild.utils.ScoreBoard
import me.clip.placeholderapi.PlaceholderAPI
import net.minecraft.server.v1_8_R3.BlockPosition
import net.minecraft.server.v1_8_R3.IChatBaseComponent
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import kotlin.math.pow


object Long {

    const val size = 31
    var used = 0
    var rooms = BooleanArray(size)
    var times = mutableMapOf<UUID, Int>()
    var MainTimer = mutableMapOf<UUID, Int>()
    var session = mutableListOf<UUID>()
    var finishedtask = mutableMapOf<UUID, Int>()
    var Blocks = mutableMapOf<UUID, MutableSet<BlockPosition>>()
    var score = mutableMapOf<UUID, Int>()
    var PlayerCoord = mutableMapOf<UUID, Int>()

    fun load() {
        for (i in 0 until size) {
            rooms[i] = true
        }
    }

    fun finish(player: Player) {
        val world = Bukkit.getWorld("Long")
        val uuid = player.uniqueId
        val time = times[uuid] ?: return BungeePluginMessage.switchServer(player, "Lobby")
        if (time < 140 || !MainTimer.containsKey(uuid)) {
            BungeePluginMessage.switchServer(player, "Lobby")
            return
        }
        Core.waiting.add(uuid)
        Bukkit.getScheduler().cancelTask(MainTimer[uuid]!!)
        MainTimer.remove(uuid)
        val msrecord = time * 50
        val display = Core.tickToTime(time)
        val pb = Core.integerCache[uuid]?.get(2) ?: 0
        if (pb == 0 || pb > msrecord) {
            Core.integerCache[uuid]?.set(2, msrecord)
        }
        times[uuid] = -1
        player.playSound(player.location, Sound.LEVEL_UP, 1.0F, 1.0F)
        // TITLE
        val connection = (player as CraftPlayer).handle.playerConnection
        connection.sendPacket(PacketPlayOutTitle(0, 60, 5))
        connection.sendPacket(
            PacketPlayOutTitle(
                PacketPlayOutTitle.EnumTitleAction.TITLE,
                IChatBaseComponent.ChatSerializer.a(
                    "{\"text\":\"" + "§aTime: §e%time%".replace(
                        "%time%",
                        display
                    ) + "\"}"
                )
            )
        )
        // SCOREBOARD
//        val scoreTime = score[uuid] ?: 0
//        if (scoreTime > time || scoreTime == 0) {
//            var i = 0
//            while (i < session.size) {
//                val sessionScore = score[session[i]] ?: 0
//                if (sessionScore <= time && sessionScore != 0) {
//                    i++
//                    continue
//                }
//                session.remove(uuid)
//                session.add(i, uuid)
//                break
//            }
//            score[uuid] = time
//            if (i < 4) {
//                val prefix = mutableListOf<String>()
//                val suffix = mutableListOf<String>()
//                val size = session.size
//                for (j in i until 3) {
//                    if (size <= j) {
//                        prefix.add("§7-,---")
//                        suffix.add("")
//                    } else {
//                        val ses = session[j]
//                        val sc = score[ses] ?: 0
//                        if (sc == 0) {
//                            prefix.add("§7-,---")
//                            suffix.add("")
//                        } else {
//                            val x = SideBarAPI.prefixSuffixSplit(
//                                "§7" + Bukkit.getPlayer(ses).name + "§8: §e" + Core.tickToTime(sc)
//                            )
//                            prefix.add(x[0])
//                            suffix.add(x[1])
//                        }
//                    }
//                }
//                for (j in i until 3) {
//                    for (p in world.players) {
//                        val t = p.scoreboard.getTeam("§" + (j + 4) + "§f")
//                        t.prefix = prefix[j - i]
//                        t.suffix = suffix[j - i]
//                    }
//                }
//            }
//        }
        finishedtask[uuid] = Bukkit.getScheduler().scheduleSyncDelayedTask(CyanFastBuilder.instance) {
            if (PlayerCoord.containsKey(uuid)) {
                PlayerUtils.teleport(connection, 0.5, 100.0, PlayerCoord[uuid]!! + 0.5, 270.0F, 0.0F)
                Blocks[uuid]?.forEach { b ->
                    world.getBlockAt(b.x, b.y, b.z).type = Material.AIR
                }
                Blocks[uuid]?.clear()
                Core.waiting.remove(uuid)
                val inventory = player.inventory
                val stack = ItemStackList.Blocks[Core.integerCache[uuid]?.get(0) ?: 0]
                inventory.setItem(0, stack)
                inventory.setItem(1, stack)
            }
        }
    }


    fun join(player: Player, kick: Boolean) {
        if (used == 31) {
            player.sendMessage("There is no enough servers. please report this to speedcubing.")
            if (kick) {
                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        // Your code here
                    }
                }, 500L)
            }
            return
        }
        val room = (0 until 31).firstOrNull { rooms[it] } ?: return
        rooms[room] = false
        used++
        val uuid = player.uniqueId
        val coord = roomToZ(room)
        PlayerCoord[uuid] = coord
        // 计分板部分
        var line = CyanFastBuilder.instance.config.getStringList("scoreboard.lines")
        // 根据 score 内找出分数最低的三个玩家
        val sorted = score.toList().sortedBy { (_, value) -> value }.toMap()
        val sess = sorted.keys.toList()
        line = line.map { it
                .replace("%date%", SimpleDateFormat("yyyy/MM/dd").format(Date()))
                .replace("%first%", sess.getOrNull(0)?.let { Bukkit.getPlayer(it)?.name } ?: "§7-,---")
                .replace("%second%", sess.getOrNull(1)?.let { Bukkit.getPlayer(it)?.name } ?: "§7-,---")
                .replace("%third%", sess.getOrNull(2)?.let { Bukkit.getPlayer(it)?.name } ?: "§7-,---")
                .replace("%small%", score[uuid]?.let { Core.tickToTime(it) } ?: "§7-,---")
                .replace("%time%", "§7-,---")
        }
        line = PlaceholderAPI.setPlaceholders(player, line)
        ScoreBoard.lines[uuid] = line
        // 计分板部分
        val stats = CyanFastBuilder.instance.config.getString("stats.$uuid") ?: "0/5/0/0/0/0/0"
        CyanFastBuilder.instance.config.set("stats.$uuid", stats)
        CyanFastBuilder.instance.saveConfig()
        val prof = stats.split("/")
        val x = prof.map { it.toInt() }
        Core.integerCache[uuid] = x.toMutableList()
        val inventory = player.inventory
        inventory.clear()
        val stack = ItemStackList.Blocks[x[0]]
        inventory.setItem(0, stack)
        inventory.setItem(1, stack)
        inventory.setItem(2, ItemStackList.pickaxe)
        inventory.setItem(7, ItemStackList.Settings)
        inventory.setItem(8, ItemStackList.navigator)
        player.teleport(Location(Bukkit.getWorld("Long"), 0.5, 100.0, coord + 0.5, 270.0F, 0.0F))
        Blocks[uuid] = mutableSetOf()
        session.add(uuid)
        times[uuid] = -1
        score[uuid] = 0
    }


    fun quit(player: Player) {
        val uuid = player.uniqueId
        MainTimer[uuid]?.let { Bukkit.getScheduler().cancelTask(it) }
        MainTimer.remove(uuid)
        val world = Bukkit.getWorld("Long")
        Blocks[uuid]?.forEach { b ->
            world.getBlockAt(b.x, b.y, b.z).type = Material.AIR
        }
        Blocks[uuid]?.clear()
        finishedtask.remove(uuid)
        times[uuid] = -1
        Core.waiting.remove(uuid)
        val x = session.indexOf(uuid)
        session.remove(uuid)
        score.remove(uuid)
        ScoreBoard.lines.remove(uuid)
        used--
        rooms[coordToRoom(PlayerCoord[uuid] ?: 0)] = true
        PlayerCoord.remove(uuid)
    }


    fun validPlaceBlock(player: Player, b: BlockPosition) {
        val uuid = player.uniqueId
        if (times[uuid] == -1) {
            times[uuid] = 0
            MainTimer[uuid] = Bukkit.getScheduler().scheduleSyncRepeatingTask(CyanFastBuilder.instance, {
                val up = times[uuid]!! + 1
                times[uuid] = up
//                player.scoreboard.getTeam(StringList.SideBar[1]).suffix = Core.tickToTime(up)
                if (up >= 2400) {
                    fell(player, false)
                    player.sendMessage(StringList.cancelled)
                }
            }, 0L, 20L)
        }
        Blocks[uuid]?.add(b)
        val inventory = player.inventory
        val empty = 1 - player.inventory.heldItemSlot
        if (inventory.getItem(empty) == null)
            inventory.setItem(empty, ItemStackList.Blocks[Core.integerCache[uuid]?.get(0) ?: 0])
    }


    fun fell(player: Player, fell: Boolean) {
        val uuid = player.uniqueId
        if (Core.waiting.contains(uuid)) {
            Bukkit.getScheduler().cancelTask(finishedtask[uuid]!!)
            finishedtask.remove(uuid)
            val world = player.world
            Blocks[uuid]?.forEach { b ->
                world.getBlockAt(b.x, b.y, b.z).type = Material.AIR
            }
            Blocks[uuid]?.clear()
            Core.waiting.remove(uuid)
            val inventory = player.inventory
            val stack = ItemStackList.Blocks[Core.integerCache[uuid]?.get(0) ?: 0]
            inventory.setItem(0, stack)
            inventory.setItem(1, stack)
        } else {
            if (times[uuid] != -1) {
                MainTimer[uuid]?.let { Bukkit.getScheduler().cancelTask(it) }
                MainTimer.remove(uuid)
                val world = player.world
                Blocks[uuid]?.forEach { b ->
                    world.getBlockAt(b.x, b.y, b.z).type = Material.AIR
                }
                Blocks[uuid]?.clear()
                val inventory = player.inventory
                val stack = ItemStackList.Blocks[Core.integerCache[uuid]?.get(0) ?: 0]
                inventory.setItem(0, stack)
                inventory.setItem(1, stack)
                times[uuid] = -1
            } else if (!fell) {
                val coord = PlayerCoord[uuid] ?: 0
                val oldroom = coordToRoom(coord)
                val newroom = Core.cal(oldroom, player.location.z > coord, rooms)
                if (newroom == -1) {
                    player.sendMessage(StringList.nomapsavaliable)
                } else {
                    PlayerCoord[uuid] = roomToZ(newroom)
                    rooms[oldroom] = true
                    rooms[newroom] = false
                }
            }
            player.closeInventory()
        }
        PlayerUtils.teleport(
            (player as CraftPlayer).handle.playerConnection,
            0.5,
            100.0,
            PlayerCoord[uuid]!! + 0.5,
            270.0F,
            0.0F
        )
    }


    private fun roomToZ(room: Int): Int {
        return 6 * (room * (-1.0).pow(room + 1) + room % 2).toInt()
    }

    private fun coordToRoom(z: Int): Int {
        return if (z == 0) 0 else (abs(z / 6) - (abs(z / 12) + z / 12) / z / 6)
    }

}