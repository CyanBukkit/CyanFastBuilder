package cubing.bukkit;

import com.mojang.authlib.properties.*;
import org.bukkit.craftbukkit.v1_8_R3.inventory.*;
import org.bukkit.entity.*;
import net.minecraft.server.v1_8_R3.*;
import com.mojang.authlib.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.*;
import cubing.utils.*;
import org.bukkit.*;
import java.lang.reflect.*;
import java.util.*;

public class PlayerUtils
{
    public PlayerUtils() {
        super();
    }
    
    public static void explosionCrash(final PlayerConnection connection) {
        connection.sendPacket((Packet)new PacketPlayOutExplosion(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Float.MAX_VALUE, (List)new ArrayList(), new Vec3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE)));
    }
    
    public static void removeArrows(final DataWatcher watcher) {
        watcher.watch(9, (Object)0);
    }
    
    public static void sendActionBar(final PlayerConnection connection, final String message) {
        connection.sendPacket((Packet)new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}"), (byte)2));
    }
    
    public static void teleport(final PlayerConnection connection, final double x, final double y, final double z, final float yaw, final float pitch) {
        connection.a(x, y, z, yaw, pitch);
    }
    
    public static List<Packet>[] changeSkin(final EntityPlayer entityPlayer, final String[] skin) {
        final Player player = entityPlayer.getBukkitEntity().getPlayer();
        final World world = entityPlayer.getWorld();
        final GameProfile gameProfile = entityPlayer.getProfile();
        gameProfile.getProperties().removeAll((Object)"textures");
        gameProfile.getProperties().put((Object)"textures", (Object)new Property("textures", skin[0], skin[1]));
        final PacketPlayOutPlayerInfo removePlayerPacket = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[] { entityPlayer });
        final PacketPlayOutPlayerInfo addPlayerPacket = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[] { entityPlayer });
        final PacketPlayOutEntityDestroy destroyPacket = new PacketPlayOutEntityDestroy(new int[] { entityPlayer.getId() });
        final PlayerInventory inventory = player.getInventory();
        final Location l = player.getLocation();
        return new List[] { Arrays.asList((Packet)removePlayerPacket, (Packet)addPlayerPacket, (Packet)new PacketPlayOutRespawn(world.getWorld().getEnvironment().getId(), world.getDifficulty(), world.getWorldData().getType(), entityPlayer.playerInteractManager.getGameMode()), (Packet)new PacketPlayOutPosition(l.getX(), l.getY(), l.getZ(), l.getYaw(), l.getPitch(), (Set)new HashSet()), (Packet)new PacketPlayOutHeldItemSlot(player.getInventory().getHeldItemSlot())), Arrays.asList((Packet)removePlayerPacket, (Packet)addPlayerPacket, (Packet)destroyPacket, (Packet)new PacketPlayOutNamedEntitySpawn((EntityHuman)entityPlayer), (Packet)new PacketPlayOutEntity.PacketPlayOutEntityLook(entityPlayer.getId(), (byte)(l.getYaw() * 256.0f / 360.0f), (byte)(l.getPitch() * 256.0f / 360.0f), true), (Packet)new PacketPlayOutEntityHeadRotation((Entity)entityPlayer, (byte)(l.getYaw() * 256.0f / 360.0f)), (Packet)new PacketPlayOutEntityEquipment(entityPlayer.getId(), 0, CraftItemStack.asNMSCopy(player.getItemInHand())), (Packet)new PacketPlayOutEntityEquipment(entityPlayer.getId(), 1, CraftItemStack.asNMSCopy(inventory.getBoots())), (Packet)new PacketPlayOutEntityEquipment(entityPlayer.getId(), 2, CraftItemStack.asNMSCopy(inventory.getLeggings())), (Packet)new PacketPlayOutEntityEquipment(entityPlayer.getId(), 3, CraftItemStack.asNMSCopy(inventory.getChestplate())), (Packet)new PacketPlayOutEntityEquipment(entityPlayer.getId(), 4, CraftItemStack.asNMSCopy(inventory.getHelmet()))), Arrays.asList((Packet)removePlayerPacket, (Packet)addPlayerPacket, (Packet)destroyPacket) };
    }
    
    public static void sendTabHeaderFooter(final PlayerConnection connection, final String header, final String footer) {
        connection.sendPacket((Packet)PacketWrapper.PacketPlayOutPlayerListHeaderFooter(header, footer));
    }
    
    public static void ChangeName(final String name, final Player player) {
        player.setCustomName(player.getName());
        player.setPlayerListName(name);
        player.setDisplayName(name);
        final GameProfile profile = ((CraftPlayer)player).getHandle().getProfile();
        Reflections.packetSetValue(profile, "name", name);
        try {
            final Field f = profile.getClass().getDeclaredField("name");
            f.setAccessible(true);
            f.set(profile, name);
        }
        catch (final Exception ex) {}
        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(player);
            p.showPlayer(player);
        }
    }
}
