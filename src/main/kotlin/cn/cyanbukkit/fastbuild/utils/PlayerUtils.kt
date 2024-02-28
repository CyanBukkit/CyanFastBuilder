package cn.cyanbukkit.fastbuild.utils

import com.mojang.authlib.properties.Property
import net.minecraft.server.v1_8_R3.*
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack


object PlayerUtils {

    fun explosionCrash(connection: PlayerConnection) {
        connection.sendPacket(
            PacketPlayOutExplosion(
                Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Float.MAX_VALUE, mutableListOf(), Vec3D(
                    Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE
                )
            ) as Packet<*>
        )
    }

    fun removeArrows(watcher: DataWatcher) {
        watcher.watch(9, 0.toByte())
    }

    fun sendActionBar(connection: PlayerConnection, message: String) {
        connection.sendPacket(
            PacketPlayOutChat(
                IChatBaseComponent.ChatSerializer.a("{\"text\":\"$message\"}"),
                2.toByte()
            ) as Packet<*>
        )
    }

    fun teleport(connection: PlayerConnection, x: Double, y: Double, z: Double, yaw: Float, pitch: Float) {
        connection.a(x, y, z, yaw, pitch)
    }


    fun changeSkin(entityPlayer: EntityPlayer, skin: Array<String?>): Array<List<Packet<*>>> {
        val player = entityPlayer.bukkitEntity.player
        val world = entityPlayer.getWorld()
        val gameProfile = entityPlayer.profile
        gameProfile.properties.removeAll("textures")
        gameProfile.properties.put("textures", Property("textures", skin[0], skin[1]))
        val removePlayerPacket =
            PacketPlayOutPlayerInfo(
                PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER,
                mutableListOf(entityPlayer)
            )
        val addPlayerPacket =
            PacketPlayOutPlayerInfo(
                PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER,
                mutableListOf(entityPlayer)
            )
        val destroyPacket = PacketPlayOutEntityDestroy(entityPlayer.id)
        val inventory = player.inventory
        val l = player.location
        val packets: Array<List<Packet<*>>> = arrayOf(
            listOf(
                removePlayerPacket,
                addPlayerPacket,
                PacketPlayOutRespawn(
                    world.world.environment.id,
                    world.difficulty,
                    world.worldData.type,
                    entityPlayer.playerInteractManager.gameMode
                ),
                PacketPlayOutPosition(
                    l.x,
                    l.y,
                    l.z,
                    l.yaw,
                    l.pitch,
                    HashSet()
                ),
                PacketPlayOutHeldItemSlot(player.inventory.heldItemSlot)
            ),
            listOf(
                removePlayerPacket,
                addPlayerPacket,
                destroyPacket,
                PacketPlayOutNamedEntitySpawn(entityPlayer as EntityHuman),
                PacketPlayOutEntity.PacketPlayOutEntityLook(
                    entityPlayer.id,
                    (l.yaw * 256.0F / 360.0F).toInt().toByte(),
                    (l.pitch * 256.0F / 360.0F).toInt().toByte(),
                    true
                ),
                PacketPlayOutEntityHeadRotation(
                    entityPlayer,
                    (l.yaw * 256.0F / 360.0F).toInt().toByte()
                ),
                PacketPlayOutEntityEquipment(
                    entityPlayer.id,
                    0,
                    CraftItemStack.asNMSCopy(player.itemInHand)
                ),
                PacketPlayOutEntityEquipment(
                    entityPlayer.id,
                    1,
                    CraftItemStack.asNMSCopy(inventory.boots)
                ),
                PacketPlayOutEntityEquipment(
                    entityPlayer.id,
                    2,
                    CraftItemStack.asNMSCopy(inventory.leggings)
                ),
                PacketPlayOutEntityEquipment(
                    entityPlayer.id,
                    3,
                    CraftItemStack.asNMSCopy(inventory.chestplate)
                ),
                PacketPlayOutEntityEquipment(
                    entityPlayer.id,
                    4,
                    CraftItemStack.asNMSCopy(inventory.helmet)
                )
            ),
            listOf(
                removePlayerPacket,
                addPlayerPacket,
                destroyPacket
            )
        )
        return packets
    }


    fun sendTabHeaderFooter(connection: PlayerConnection, header: String?, footer: String?) {
        connection.sendPacket(PacketWrapper.PacketPlayOutPlayerListHeaderFooter(header, footer) as Packet<*>)
    }


}