package speedcubing.Core;

import org.bukkit.entity.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.*;
import speedcubing.*;
import speedcubing.List.*;
import org.bukkit.plugin.*;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.*;
import java.text.*;
import java.util.*;
import org.bukkit.scoreboard.*;

public class Short
{
    public static final int size = 31;
    public static int used;
    public static boolean[] rooms;
    public static Map<UUID, Integer> times;
    public static Map<UUID, Integer> MainTimer;
    public static List<UUID> session;
    public static Map<UUID, Integer> finishedtask;
    public static Map<UUID, Set<BlockPosition>> Blocks;
    public static Map<UUID, Integer> score;
    public static final Map<UUID, Integer> PlayerCoord;
    
    static {
        Short.used = 0;
        Short.rooms = new boolean[31];
        Short.times = new HashMap<UUID, Integer>();
        Short.MainTimer = new HashMap<UUID, Integer>();
        Short.session = new ArrayList<UUID>();
        Short.finishedtask = new HashMap<UUID, Integer>();
        Short.Blocks = new HashMap<UUID, Set<BlockPosition>>();
        Short.score = new HashMap<UUID, Integer>();
        PlayerCoord = new HashMap<UUID, Integer>();
    }
    
    public Short() {
        super();
    }
    
    public void Load() {
        for (int i = 0; i < 31; ++i) {
            Short.rooms[i] = true;
        }
    }
    
    public static void Finish(final Player player) {
        final World world = Bukkit.getWorld("Short");
        final UUID uuid = player.getUniqueId();
        final int time = Short.times.get(uuid);
        if (time < 60 || !Short.MainTimer.containsKey(uuid)) {
            BungeePluginMessage.switchServer(player, "Lobby");
            return;
        }
        Core.waiting.add(uuid);
        Bukkit.getScheduler().cancelTask((int)Short.MainTimer.get(uuid));
        Short.MainTimer.remove(uuid);
        final int msrecord = time * 50;
        final String display = Core.tickToTime(time);
        final String[] sidebar = StringList.SideBar;
        final int pb = Core.integerCache.get(uuid)[3];
        if (pb == 0 || pb > msrecord) {
            player.getScoreboard().getTeam(sidebar[9]).setSuffix(display);
            Core.integerCache.get(uuid)[3] = msrecord;
        }
        Short.times.put(uuid, -1);
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
        final PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        connection.sendPacket((Packet)new PacketPlayOutTitle(0, 60, 5));
        connection.sendPacket((Packet)new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + "��aTime: ��e%time%".replace("%time%", display) + "\"}")));
        if (Short.score.get(uuid) > time || Short.score.get(uuid) == 0) {
            int i;
            for (i = 0; i < Short.session.size(); ++i) {
                if (Short.score.get(Short.session.get(i)) > time || Short.score.get(Short.session.get(i)) == 0) {
                    Short.session.remove(uuid);
                    Short.session.add(i, uuid);
                    break;
                }
            }
            Short.score.put(uuid, time);
            if (i < 4) {
                final ArrayList<String> prefix = new ArrayList<String>();
                final ArrayList<String> suffix = new ArrayList<String>();
                final int size = Short.session.size();
                for (int j = i; j < 3; ++j) {
                    if (size <= j) {
                        prefix.add("��7-,---");
                        suffix.add("");
                    }
                    else {
                        final UUID ses = Short.session.get(j);
                        final int sc = Short.score.get(ses);
                        if (sc == 0) {
                            prefix.add("��7-,---");
                            suffix.add("");
                        }
                        else {
                            final String[] xx = SideBarAPI.prefixSuffixSplit("��7" + Bukkit.getPlayer(ses).getName() + "��8: ��e" + Core.tickToTime(sc));
                            prefix.add(xx[0]);
                            suffix.add(xx[1]);
                        }
                    }
                }
                for (int j = i; j < 3; ++j) {
                    for (final Player p : world.getPlayers()) {
                        final Team t = p.getScoreboard().getTeam("��" + (j + 4) + "��f");
                        t.setPrefix((String)prefix.get(j - i));
                        t.setSuffix((String)suffix.get(j - i));
                    }
                }
            }
        }
        Short.finishedtask.put(uuid, Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getPlugin((Class)Main.class), () -> {
            if (Short.PlayerCoord.containsKey(uuid2)) {
                PlayerUtils.teleport(connection2, 0.5, 100.0, Short.PlayerCoord.get(uuid2) + 0.5, 270.0f, 0.0f);
                Short.Blocks.get(uuid2).iterator();
                final Iterator iterator2;
                while (iterator2.hasNext()) {
                    final BlockPosition b = iterator2.next();
                    world2.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
                }
                Short.Blocks.get(uuid2).clear();
                Core.waiting.remove(player2.getUniqueId());
                final PlayerInventory inventory = player2.getInventory();
                final ItemStack stack = ItemStackList.Blocks[Core.integerCache.get(uuid2)[0]];
                inventory.setItem(0, stack);
                inventory.setItem(1, stack);
            }
        }, 40L));
    }
    
    public static void Join(final Player player) {
        if (Short.used == 31) {
            player.sendMessage("There is no enough servers. please report this to speedcubing.");
            player.closeInventory();
            return;
        }
        int room = -1;
        for (int h = 0; h < 31; ++h) {
            if (Short.rooms[h]) {
                room = h;
                break;
            }
        }
        Short.rooms[room] = false;
        ++Short.used;
        final UUID uuid = player.getUniqueId();
        final int coord = roomToZ(room);
        player.teleport(new Location(Bukkit.getWorld("Short"), 0.5, 100.0, coord + 0.5, 270.0f, 0.0f));
        Short.PlayerCoord.put(uuid, coord);
        final Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        final Objective objective = scoreboard.registerNewObjective("sidebar", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        final String[] sidebar = StringList.SideBar;
        objective.setDisplayName(sidebar[13]);
        final Integer[] x = Core.integerCache.get(uuid);
        SideBarAPI.sidebarTeams(scoreboard, new String[][] { { sidebar[12].replace("%date%", new SimpleDateFormat("yyyy/MM/dd").format(new Date())) }, { sidebar[11] }, { sidebar[10] }, { "", sidebar[9], (x[3] == 0) ? "��7-,---" : String.format("%.3f", x[3] * 0.001) }, { sidebar[8] }, { sidebar[7] }, { "", sidebar[6], "" }, { "", sidebar[5], "" }, { "", sidebar[4], "" }, { sidebar[3] }, { sidebar[2] }, { "", sidebar[1], "��7-,---" }, { sidebar[0] } }, new int[] { 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 });
        final int size = Short.session.size();
        for (int j = 0; j < 3; ++j) {
            String prefix;
            String suffix;
            if (size <= j) {
                prefix = "��7-,---";
                suffix = "";
            }
            else {
                final UUID ses = Short.session.get(j);
                final int sc = Short.score.get(ses);
                if (sc == 0) {
                    prefix = "��7-,---";
                    suffix = "";
                }
                else {
                    final String[] xx = SideBarAPI.prefixSuffixSplit("��7" + Bukkit.getPlayer(ses).getName() + "��8: ��e" + Core.tickToTime(sc));
                    prefix = xx[0];
                    suffix = xx[1];
                }
            }
            final Team t = scoreboard.getTeam("��" + (j + 4) + "��f");
            t.setPrefix(prefix);
            t.setSuffix(suffix);
        }
        player.setScoreboard(scoreboard);
        final PlayerInventory inventory = player.getInventory();
        inventory.clear();
        final ItemStack stack = ItemStackList.Blocks[x[0]];
        inventory.setItem(0, stack);
        inventory.setItem(1, stack);
        inventory.setItem(2, ItemStackList.pickaxe);
        inventory.setItem(7, ItemStackList.Settings);
        inventory.setItem(8, ItemStackList.navigator);
        Short.Blocks.put(uuid, new HashSet<BlockPosition>());
        Short.session.add(uuid);
        Short.times.put(uuid, -1);
        Short.score.put(uuid, 0);
    }
    
    public static void Quit(final Player player) {
        final UUID uuid = player.getUniqueId();
        if (Short.MainTimer.containsKey(uuid)) {
            Bukkit.getScheduler().cancelTask((int)Short.MainTimer.get(uuid));
            Short.MainTimer.remove(uuid);
        }
        final World world = Bukkit.getWorld("Short");
        for (final BlockPosition b : Short.Blocks.get(uuid)) {
            world.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
        }
        Short.Blocks.get(uuid).clear();
        Short.finishedtask.remove(uuid);
        Short.times.put(uuid, -1);
        Core.waiting.remove(uuid);
        final int x = Short.session.indexOf(uuid);
        Short.session.remove(uuid);
        Short.score.remove(uuid);
        if (x != -1 && x < 3) {
            final ArrayList<String> prefix = new ArrayList<String>();
            final ArrayList<String> suffix = new ArrayList<String>();
            final int size = Short.session.size();
            for (int j = x; j < 3; ++j) {
                if (size <= j) {
                    prefix.add("��7-,---");
                    suffix.add("");
                }
                else {
                    final UUID ses = Short.session.get(j);
                    final int sc = Short.score.get(ses);
                    if (sc == 0) {
                        prefix.add("��7-,---");
                        suffix.add("");
                    }
                    else {
                        final String[] xx = SideBarAPI.prefixSuffixSplit("��7" + Bukkit.getPlayer(ses).getName() + "��8: ��e" + Core.tickToTime(sc));
                        prefix.add(xx[0]);
                        suffix.add(xx[1]);
                    }
                }
            }
            for (final Player p : world.getPlayers()) {
                if (p.getName().equals(player.getName())) {
                    continue;
                }
                final Scoreboard scoreboard = p.getScoreboard();
                for (int i = x; i < 3; ++i) {
                    final Team t = scoreboard.getTeam("��" + (i + 4) + "��f");
                    t.setPrefix((String)prefix.get(i - x));
                    t.setSuffix((String)suffix.get(i - x));
                }
            }
        }
        --Short.used;
        Short.rooms[coordToRoom(Short.PlayerCoord.get(uuid))] = true;
        Short.PlayerCoord.remove(uuid);
    }
    
    public static void validPlaceBlock(final Player player, final BlockPosition b) {
        final UUID uuid = player.getUniqueId();
        if (Short.times.get(uuid) == -1) {
            Short.times.put(uuid, 0);
            Short.MainTimer.put(uuid, Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)Main.getPlugin((Class)Main.class), () -> {
                final int up = Short.times.get(uuid2) + 1;
                Short.times.put(uuid2, up);
                player2.getScoreboard().getTeam(StringList.SideBar[1]).setSuffix(Core.tickToTime(up));
                if (up >= 2400) {
                    Fell(player2, false);
                    player2.sendMessage(StringList.cancelled);
                }
                return;
            }, 0L, 1L));
        }
        Short.Blocks.get(uuid).add(b);
        final PlayerInventory inventory = player.getInventory();
        final int empty = 1 - player.getInventory().getHeldItemSlot();
        if (inventory.getItem(empty) == null) {
            inventory.setItem(empty, ItemStackList.Blocks[Core.integerCache.get(uuid)[0]]);
        }
    }
    
    public static void Fell(final Player player, final boolean fell) {
        final UUID uuid = player.getUniqueId();
        if (Core.waiting.contains(uuid)) {
            Bukkit.getScheduler().cancelTask((int)Short.finishedtask.get(uuid));
            Short.finishedtask.remove(uuid);
            final World world = player.getWorld();
            for (final BlockPosition b : Short.Blocks.get(uuid)) {
                world.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
            }
            Short.Blocks.get(uuid).clear();
            Core.waiting.remove(uuid);
            final PlayerInventory inventory = player.getInventory();
            final ItemStack stack = ItemStackList.Blocks[Core.integerCache.get(uuid)[0]];
            inventory.setItem(0, stack);
            inventory.setItem(1, stack);
        }
        else {
            if (Short.times.get(uuid) != -1) {
                if (Short.MainTimer.containsKey(uuid)) {
                    Bukkit.getScheduler().cancelTask((int)Short.MainTimer.get(uuid));
                    Short.MainTimer.remove(uuid);
                }
                final World world = player.getWorld();
                for (final BlockPosition b : Short.Blocks.get(uuid)) {
                    world.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
                }
                Short.Blocks.get(uuid).clear();
                final PlayerInventory inventory = player.getInventory();
                final ItemStack stack = ItemStackList.Blocks[Core.integerCache.get(uuid)[0]];
                inventory.setItem(0, stack);
                inventory.setItem(1, stack);
                Short.times.put(uuid, -1);
            }
            else if (!fell) {
                final int coord = Short.PlayerCoord.get(uuid);
                final int oldroom = coordToRoom(coord);
                final int newroom = Core.cal(oldroom, player.getLocation().getZ() > coord, Short.rooms);
                if (newroom == -1) {
                    player.sendMessage(StringList.nomapsavaliable);
                }
                else {
                    Short.PlayerCoord.put(uuid, roomToZ(newroom));
                    Short.rooms[oldroom] = true;
                    Short.rooms[newroom] = false;
                }
            }
            player.closeInventory();
        }
        PlayerUtils.teleport(((CraftPlayer)player).getHandle().playerConnection, 0.5, 100.0, Short.PlayerCoord.get(uuid) + 0.5, 270.0f, 0.0f);
    }
    
    public static int roomToZ(final int room) {
        return 6 * (room * (int)Math.pow(-1.0, room + 1) + room % 2);
    }
    
    public static int coordToRoom(final int z) {
        return (z == 0) ? 0 : (Math.abs(z / 6) - (Math.abs(z / 12) + z / 12) / (z / 6));
    }
    
    private static /* synthetic */ void lambda$0(final UUID uuid, final PlayerConnection connection, final World world, final Player player) {
        if (Short.PlayerCoord.containsKey(uuid)) {
            PlayerUtils.teleport(connection, 0.5, 100.0, Short.PlayerCoord.get(uuid) + 0.5, 270.0f, 0.0f);
            for (final BlockPosition b : Short.Blocks.get(uuid)) {
                world.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
            }
            Short.Blocks.get(uuid).clear();
            Core.waiting.remove(player.getUniqueId());
            final PlayerInventory inventory = player.getInventory();
            final ItemStack stack = ItemStackList.Blocks[Core.integerCache.get(uuid)[0]];
            inventory.setItem(0, stack);
            inventory.setItem(1, stack);
        }
    }
    
    private static /* synthetic */ void lambda$1(final UUID uuid, final Player player) {
        final int up = Short.times.get(uuid) + 1;
        Short.times.put(uuid, up);
        player.getScoreboard().getTeam(StringList.SideBar[1]).setSuffix(Core.tickToTime(up));
        if (up >= 2400) {
            Fell(player, false);
            player.sendMessage(StringList.cancelled);
        }
    }
}
