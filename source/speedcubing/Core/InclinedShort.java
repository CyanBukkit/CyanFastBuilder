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

public class InclinedShort
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
        InclinedShort.used = 0;
        InclinedShort.rooms = new boolean[31];
        InclinedShort.times = new HashMap<UUID, Integer>();
        InclinedShort.MainTimer = new HashMap<UUID, Integer>();
        InclinedShort.session = new ArrayList<UUID>();
        InclinedShort.finishedtask = new HashMap<UUID, Integer>();
        InclinedShort.Blocks = new HashMap<UUID, Set<BlockPosition>>();
        InclinedShort.score = new HashMap<UUID, Integer>();
        PlayerCoord = new HashMap<UUID, Integer>();
    }
    
    public InclinedShort() {
        super();
    }
    
    public void Load() {
        for (int i = 0; i < 31; ++i) {
            InclinedShort.rooms[i] = true;
        }
    }
    
    public static void Finish(final Player player) {
        final World world = Bukkit.getWorld("Inclinedshort");
        final UUID uuid = player.getUniqueId();
        final int time = InclinedShort.times.get(uuid);
        if (time < 40 || !InclinedShort.MainTimer.containsKey(uuid)) {
            BungeePluginMessage.switchServer(player, "Lobby");
            return;
        }
        Core.waiting.add(uuid);
        Bukkit.getScheduler().cancelTask((int)InclinedShort.MainTimer.get(uuid));
        InclinedShort.MainTimer.remove(uuid);
        final int msrecord = time * 50;
        final String display = Core.tickToTime(time);
        final String[] sidebar = StringList.SideBar;
        final int pb = Core.integerCache.get(uuid)[6];
        if (pb == 0 || pb > msrecord) {
            player.getScoreboard().getTeam(sidebar[9]).setSuffix(display);
            Core.integerCache.get(uuid)[6] = msrecord;
        }
        InclinedShort.times.put(uuid, -1);
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
        final PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        connection.sendPacket((Packet)new PacketPlayOutTitle(0, 60, 5));
        connection.sendPacket((Packet)new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + "��aTime: ��e%time%".replace("%time%", display) + "\"}")));
        if (InclinedShort.score.get(uuid) > time || InclinedShort.score.get(uuid) == 0) {
            int i;
            for (i = 0; i < InclinedShort.session.size(); ++i) {
                if (InclinedShort.score.get(InclinedShort.session.get(i)) > time || InclinedShort.score.get(InclinedShort.session.get(i)) == 0) {
                    InclinedShort.session.remove(uuid);
                    InclinedShort.session.add(i, uuid);
                    break;
                }
            }
            InclinedShort.score.put(uuid, time);
            if (i < 4) {
                final ArrayList<String> prefix = new ArrayList<String>();
                final ArrayList<String> suffix = new ArrayList<String>();
                final int size = InclinedShort.session.size();
                for (int j = i; j < 3; ++j) {
                    if (size <= j) {
                        prefix.add("��7-,---");
                        suffix.add("");
                    }
                    else {
                        final UUID ses = InclinedShort.session.get(j);
                        final int sc = InclinedShort.score.get(ses);
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
                        final Scoreboard scoreboard = p.getScoreboard();
                        final Team t = scoreboard.getTeam("��" + (j + 4) + "��f");
                        t.setPrefix((String)prefix.get(j - i));
                        t.setSuffix((String)suffix.get(j - i));
                    }
                }
            }
        }
        InclinedShort.finishedtask.put(uuid, Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getPlugin((Class)Main.class), () -> {
            if (InclinedShort.PlayerCoord.containsKey(uuid2)) {
                final int fixed = InclinedShort.PlayerCoord.get(uuid2);
                PlayerUtils.teleport(connection2, fixed + 0.5, 100.0, fixed + 0.5, 225.0f, 0.0f);
                InclinedShort.Blocks.get(uuid2).iterator();
                final Iterator iterator2;
                while (iterator2.hasNext()) {
                    final BlockPosition b = iterator2.next();
                    world2.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
                }
                InclinedShort.Blocks.get(uuid2).clear();
                Core.waiting.remove(uuid2);
                final PlayerInventory inventory = player2.getInventory();
                final ItemStack stack = ItemStackList.Blocks[Core.integerCache.get(uuid2)[0]];
                inventory.setItem(0, stack);
                inventory.setItem(1, stack);
            }
        }, 40L));
    }
    
    public static void Join(final Player player) {
        if (InclinedShort.used == 31) {
            player.sendMessage("There is no enough servers. please report this to speedcubing.");
            player.closeInventory();
            return;
        }
        int room = -1;
        for (int h = 0; h < 31; ++h) {
            if (InclinedShort.rooms[h]) {
                room = h;
                break;
            }
        }
        InclinedShort.rooms[room] = false;
        ++InclinedShort.used;
        final UUID uuid = player.getUniqueId();
        final int coord = roomToCoord(room);
        player.teleport(new Location(Bukkit.getWorld("Inclinedshort"), coord + 0.5, 100.0, coord + 0.5, 225.0f, 0.0f));
        InclinedShort.PlayerCoord.put(uuid, coord);
        final Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        final Objective objective = scoreboard.registerNewObjective("sidebar", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        final String[] sidebar = StringList.SideBar;
        objective.setDisplayName(sidebar[13]);
        final Integer[] x = Core.integerCache.get(uuid);
        SideBarAPI.sidebarTeams(scoreboard, new String[][] { { sidebar[12].replace("%date%", new SimpleDateFormat("yyyy/MM/dd").format(new Date())) }, { sidebar[11] }, { sidebar[10] }, { "", sidebar[9], (x[6] == 0) ? "��7-,---" : Core.msToTime(x[6]) }, { sidebar[8] }, { sidebar[7] }, { "", sidebar[6], "" }, { "", sidebar[5], "" }, { "", sidebar[4], "" }, { sidebar[3] }, { sidebar[2] }, { "", sidebar[1], "��7-,---" }, { sidebar[0] } }, new int[] { 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 });
        final int size = InclinedShort.session.size();
        for (int j = 0; j < 3; ++j) {
            String prefix;
            String suffix;
            if (size <= j) {
                prefix = "��7-,---";
                suffix = "";
            }
            else {
                final UUID ses = InclinedShort.session.get(j);
                final int sc = InclinedShort.score.get(ses);
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
        final ItemStack stack = ItemStackList.Blocks[x[0]];
        inventory.clear();
        inventory.setItem(0, stack);
        inventory.setItem(1, stack);
        inventory.setItem(2, ItemStackList.pickaxe);
        inventory.setItem(7, ItemStackList.Settings);
        inventory.setItem(8, ItemStackList.navigator);
        InclinedShort.Blocks.put(uuid, new HashSet<BlockPosition>());
        InclinedShort.session.add(uuid);
        InclinedShort.times.put(uuid, -1);
        InclinedShort.score.put(uuid, 0);
    }
    
    public static void Quit(final Player player) {
        final UUID uuid = player.getUniqueId();
        if (InclinedShort.MainTimer.containsKey(uuid)) {
            Bukkit.getScheduler().cancelTask((int)InclinedShort.MainTimer.get(uuid));
            InclinedShort.MainTimer.remove(uuid);
        }
        final World world = Bukkit.getWorld("Inclinedshort");
        for (final BlockPosition b : InclinedShort.Blocks.get(uuid)) {
            world.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
        }
        InclinedShort.Blocks.get(uuid).clear();
        InclinedShort.finishedtask.remove(uuid);
        InclinedShort.times.put(uuid, -1);
        Core.waiting.remove(uuid);
        final int x = InclinedShort.session.indexOf(uuid);
        InclinedShort.session.remove(uuid);
        InclinedShort.score.remove(uuid);
        if (x != -1 && x < 3) {
            final ArrayList<String> prefix = new ArrayList<String>();
            final ArrayList<String> suffix = new ArrayList<String>();
            final int size = InclinedShort.session.size();
            for (int j = x; j < 3; ++j) {
                if (size <= j) {
                    prefix.add("��7-,---");
                    suffix.add("");
                }
                else {
                    final UUID ses = InclinedShort.session.get(j);
                    final int sc = InclinedShort.score.get(ses);
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
        --InclinedShort.used;
        InclinedShort.rooms[coordToRoom(InclinedShort.PlayerCoord.get(uuid))] = true;
        InclinedShort.PlayerCoord.remove(uuid);
    }
    
    public static void validPlaceBlock(final Player player, final BlockPosition b) {
        final UUID uuid = player.getUniqueId();
        if (InclinedShort.times.get(uuid) == -1) {
            InclinedShort.times.put(uuid, 0);
            InclinedShort.MainTimer.put(uuid, Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)Main.getPlugin((Class)Main.class), () -> {
                final int up = InclinedShort.times.get(uuid2) + 1;
                InclinedShort.times.put(uuid2, up);
                player2.getScoreboard().getTeam(StringList.SideBar[1]).setSuffix(Core.tickToTime(up));
                if (up >= 2400) {
                    Fell(player2, false);
                    player2.sendMessage(StringList.cancelled);
                }
                return;
            }, 0L, 1L));
        }
        InclinedShort.Blocks.get(uuid).add(b);
        final PlayerInventory inventory = player.getInventory();
        final int empty = 1 - player.getInventory().getHeldItemSlot();
        if (inventory.getItem(empty) == null) {
            inventory.setItem(empty, ItemStackList.Blocks[Core.integerCache.get(uuid)[0]]);
        }
    }
    
    public static void Fell(final Player player, final boolean fell) {
        final UUID uuid = player.getUniqueId();
        if (Core.waiting.contains(uuid)) {
            Bukkit.getScheduler().cancelTask((int)InclinedShort.finishedtask.get(uuid));
            InclinedShort.finishedtask.remove(uuid);
            final World world = player.getWorld();
            for (final BlockPosition b : InclinedShort.Blocks.get(uuid)) {
                world.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
            }
            InclinedShort.Blocks.get(uuid).clear();
            Core.waiting.remove(uuid);
            final PlayerInventory inventory = player.getInventory();
            final ItemStack stack = ItemStackList.Blocks[Core.integerCache.get(uuid)[0]];
            inventory.setItem(0, stack);
            inventory.setItem(1, stack);
        }
        else {
            if (InclinedShort.times.get(uuid) != -1) {
                if (InclinedShort.MainTimer.containsKey(uuid)) {
                    Bukkit.getScheduler().cancelTask((int)InclinedShort.MainTimer.get(uuid));
                    InclinedShort.MainTimer.remove(uuid);
                }
                final World world = player.getWorld();
                for (final BlockPosition b : InclinedShort.Blocks.get(uuid)) {
                    world.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
                }
                InclinedShort.Blocks.get(uuid).clear();
                final PlayerInventory inventory = player.getInventory();
                final ItemStack stack = ItemStackList.Blocks[Core.integerCache.get(uuid)[0]];
                inventory.setItem(0, stack);
                inventory.setItem(1, stack);
                InclinedShort.times.put(uuid, -1);
            }
            else if (!fell) {
                final Location location = player.getLocation();
                final int coord = InclinedShort.PlayerCoord.get(uuid);
                final int oldroom = coordToRoom(coord);
                final int newroom = Core.cal(oldroom, location.getX() + location.getZ() > coord * 2, InclinedShort.rooms);
                if (newroom == -1) {
                    player.sendMessage(StringList.nomapsavaliable);
                }
                else {
                    InclinedShort.PlayerCoord.put(uuid, roomToCoord(newroom));
                    InclinedShort.rooms[oldroom] = true;
                    InclinedShort.rooms[newroom] = false;
                }
            }
            player.closeInventory();
        }
        final int fixed = InclinedShort.PlayerCoord.get(uuid);
        PlayerUtils.teleport(((CraftPlayer)player).getHandle().playerConnection, fixed + 0.5, 100.0, fixed + 0.5, 225.0f, 0.0f);
    }
    
    public static int roomToCoord(final int room) {
        return 4 * (room * (int)Math.pow(-1.0, room + 1) + room % 2);
    }
    
    public static int coordToRoom(final int z) {
        return (z == 0) ? 0 : (Math.abs(z / 4) - (Math.abs(z / 8) + z / 8) / (z / 4));
    }
    
    private static /* synthetic */ void lambda$0(final UUID uuid, final PlayerConnection connection, final World world, final Player player) {
        if (InclinedShort.PlayerCoord.containsKey(uuid)) {
            final int fixed = InclinedShort.PlayerCoord.get(uuid);
            PlayerUtils.teleport(connection, fixed + 0.5, 100.0, fixed + 0.5, 225.0f, 0.0f);
            for (final BlockPosition b : InclinedShort.Blocks.get(uuid)) {
                world.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
            }
            InclinedShort.Blocks.get(uuid).clear();
            Core.waiting.remove(uuid);
            final PlayerInventory inventory = player.getInventory();
            final ItemStack stack = ItemStackList.Blocks[Core.integerCache.get(uuid)[0]];
            inventory.setItem(0, stack);
            inventory.setItem(1, stack);
        }
    }
    
    private static /* synthetic */ void lambda$1(final UUID uuid, final Player player) {
        final int up = InclinedShort.times.get(uuid) + 1;
        InclinedShort.times.put(uuid, up);
        player.getScoreboard().getTeam(StringList.SideBar[1]).setSuffix(Core.tickToTime(up));
        if (up >= 2400) {
            Fell(player, false);
            player.sendMessage(StringList.cancelled);
        }
    }
}
