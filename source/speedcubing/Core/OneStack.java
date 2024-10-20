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

public class OneStack
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
        OneStack.used = 0;
        OneStack.rooms = new boolean[31];
        OneStack.times = new HashMap<UUID, Integer>();
        OneStack.MainTimer = new HashMap<UUID, Integer>();
        OneStack.session = new ArrayList<UUID>();
        OneStack.finishedtask = new HashMap<UUID, Integer>();
        OneStack.Blocks = new HashMap<UUID, Set<BlockPosition>>();
        OneStack.score = new HashMap<UUID, Integer>();
        PlayerCoord = new HashMap<UUID, Integer>();
    }
    
    public OneStack() {
        super();
    }
    
    public void Load() {
        for (int i = 0; i < 31; ++i) {
            OneStack.rooms[i] = true;
        }
    }
    
    public static void Finish(final Player player) {
        final World world = Bukkit.getWorld("Onestack");
        final UUID uuid = player.getUniqueId();
        final int time = OneStack.times.get(uuid);
        if (time < 220 || !OneStack.MainTimer.containsKey(uuid)) {
            BungeePluginMessage.switchServer(player, "Lobby");
            return;
        }
        Core.waiting.add(uuid);
        Bukkit.getScheduler().cancelTask((int)OneStack.MainTimer.get(uuid));
        OneStack.MainTimer.remove(uuid);
        final int msrecord = time * 50;
        final String display = Core.tickToTime(time);
        final String[] sidebar = StringList.SideBar;
        final int pb = Core.integerCache.get(uuid)[5];
        if (pb == 0 || pb > msrecord) {
            player.getScoreboard().getTeam(sidebar[9]).setSuffix(display);
            Core.integerCache.get(uuid)[5] = msrecord;
        }
        OneStack.times.put(uuid, -1);
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
        final PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        connection.sendPacket((Packet)new PacketPlayOutTitle(0, 60, 5));
        connection.sendPacket((Packet)new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + "��aTime: ��e%time%".replace("%time%", display) + "\"}")));
        if (OneStack.score.get(uuid) > time || OneStack.score.get(uuid) == 0) {
            int i;
            for (i = 0; i < OneStack.session.size(); ++i) {
                if (OneStack.score.get(OneStack.session.get(i)) > time || OneStack.score.get(OneStack.session.get(i)) == 0) {
                    OneStack.session.remove(uuid);
                    OneStack.session.add(i, uuid);
                    break;
                }
            }
            OneStack.score.put(uuid, time);
            if (i < 4) {
                final ArrayList<String> prefix = new ArrayList<String>();
                final ArrayList<String> suffix = new ArrayList<String>();
                final int size = OneStack.session.size();
                for (int j = i; j < 3; ++j) {
                    if (size <= j) {
                        prefix.add("��7-,---");
                        suffix.add("");
                    }
                    else {
                        final UUID ses = OneStack.session.get(j);
                        final int sc = OneStack.score.get(ses);
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
        OneStack.finishedtask.put(uuid, Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getPlugin((Class)Main.class), () -> {
            if (OneStack.PlayerCoord.containsKey(uuid2)) {
                PlayerUtils.teleport(connection2, 0.5, 100.0, OneStack.PlayerCoord.get(uuid2) + 0.5, 270.0f, 0.0f);
                OneStack.Blocks.get(uuid2).iterator();
                final Iterator iterator2;
                while (iterator2.hasNext()) {
                    final BlockPosition b = iterator2.next();
                    world2.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
                }
                OneStack.Blocks.get(uuid2).clear();
                Core.waiting.remove(player2.getUniqueId());
                final PlayerInventory inventory = player2.getInventory();
                final ItemStack stack = ItemStackList.Blocks[Core.integerCache.get(uuid2)[0]];
                inventory.setItem(0, stack);
                inventory.setItem(1, stack);
            }
        }, 40L));
    }
    
    public static void Join(final Player player) {
        if (OneStack.used == 31) {
            player.sendMessage("There is no enough servers. please report this to speedcubing.");
            player.closeInventory();
            return;
        }
        int room = -1;
        for (int h = 0; h < 31; ++h) {
            if (OneStack.rooms[h]) {
                room = h;
                break;
            }
        }
        OneStack.rooms[room] = false;
        ++OneStack.used;
        final UUID uuid = player.getUniqueId();
        final int coord = roomToZ(room);
        player.teleport(new Location(Bukkit.getWorld("Onestack"), 0.5, 100.0, coord + 0.5, 270.0f, 0.0f));
        OneStack.PlayerCoord.put(uuid, coord);
        final Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        final Objective objective = scoreboard.registerNewObjective("sidebar", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        final String[] sidebar = StringList.SideBar;
        objective.setDisplayName(sidebar[13]);
        final Integer[] x = Core.integerCache.get(uuid);
        SideBarAPI.sidebarTeams(scoreboard, new String[][] { { sidebar[12].replace("%date%", new SimpleDateFormat("yyyy/MM/dd").format(new Date())) }, { sidebar[11] }, { sidebar[10] }, { "", sidebar[9], (x[5] == 0) ? "��7-,---" : String.format("%.3f", x[5] * 0.001) }, { sidebar[8] }, { sidebar[7] }, { "", sidebar[6], "" }, { "", sidebar[5], "" }, { "", sidebar[4], "" }, { sidebar[3] }, { sidebar[2] }, { "", sidebar[1], "��7-,---" }, { sidebar[0] } }, new int[] { 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 });
        final int size = OneStack.session.size();
        for (int j = 0; j < 3; ++j) {
            String prefix;
            String suffix;
            if (size <= j) {
                prefix = "��7-,---";
                suffix = "";
            }
            else {
                final UUID ses = OneStack.session.get(j);
                final int sc = OneStack.score.get(ses);
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
        OneStack.Blocks.put(uuid, new HashSet<BlockPosition>());
        OneStack.session.add(uuid);
        OneStack.times.put(uuid, -1);
        OneStack.score.put(uuid, 0);
    }
    
    public static void Quit(final Player player) {
        final World world = Bukkit.getWorld("Onestack");
        final UUID uuid = player.getUniqueId();
        if (OneStack.MainTimer.containsKey(uuid)) {
            Bukkit.getScheduler().cancelTask((int)OneStack.MainTimer.get(uuid));
            OneStack.MainTimer.remove(uuid);
        }
        for (final BlockPosition b : OneStack.Blocks.get(uuid)) {
            world.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
        }
        OneStack.Blocks.get(uuid).clear();
        OneStack.finishedtask.remove(uuid);
        OneStack.times.put(uuid, -1);
        Core.waiting.remove(uuid);
        final int x = OneStack.session.indexOf(uuid);
        OneStack.session.remove(uuid);
        OneStack.score.remove(uuid);
        if (x != -1 && x < 3) {
            final ArrayList<String> prefix = new ArrayList<String>();
            final ArrayList<String> suffix = new ArrayList<String>();
            final int size = OneStack.session.size();
            for (int j = x; j < 3; ++j) {
                if (size <= j) {
                    prefix.add("��7-,---");
                    suffix.add("");
                }
                else {
                    final UUID ses = OneStack.session.get(j);
                    final int sc = OneStack.score.get(ses);
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
        --OneStack.used;
        OneStack.rooms[coordToRoom(OneStack.PlayerCoord.get(uuid))] = true;
        OneStack.PlayerCoord.remove(uuid);
    }
    
    public static void validPlaceBlock(final Player player, final BlockPosition b) {
        final UUID uuid = player.getUniqueId();
        if (OneStack.times.get(uuid) == -1) {
            OneStack.times.put(uuid, 0);
            OneStack.MainTimer.put(uuid, Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)Main.getPlugin((Class)Main.class), () -> {
                final int up = OneStack.times.get(uuid2) + 1;
                OneStack.times.put(uuid2, up);
                player2.getScoreboard().getTeam(StringList.SideBar[1]).setSuffix(Core.tickToTime(up));
                if (up >= 2400) {
                    Fell(player2, false);
                    player2.sendMessage(StringList.cancelled);
                }
                return;
            }, 0L, 1L));
        }
        OneStack.Blocks.get(uuid).add(b);
        final PlayerInventory inventory = player.getInventory();
        final int empty = 1 - player.getInventory().getHeldItemSlot();
        if (inventory.getItem(empty) == null) {
            inventory.setItem(empty, ItemStackList.Blocks[Core.integerCache.get(uuid)[0]]);
        }
    }
    
    public static void Fell(final Player player, final boolean fell) {
        final UUID uuid = player.getUniqueId();
        if (Core.waiting.contains(uuid)) {
            Bukkit.getScheduler().cancelTask((int)OneStack.finishedtask.get(uuid));
            OneStack.finishedtask.remove(uuid);
            final World world = player.getWorld();
            for (final BlockPosition b : OneStack.Blocks.get(uuid)) {
                world.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
            }
            OneStack.Blocks.get(uuid).clear();
            Core.waiting.remove(uuid);
            final PlayerInventory inventory = player.getInventory();
            final ItemStack stack = ItemStackList.Blocks[Core.integerCache.get(uuid)[0]];
            inventory.setItem(0, stack);
            inventory.setItem(1, stack);
        }
        else {
            if (OneStack.times.get(uuid) != -1) {
                if (OneStack.MainTimer.containsKey(uuid)) {
                    Bukkit.getScheduler().cancelTask((int)OneStack.MainTimer.get(uuid));
                    OneStack.MainTimer.remove(uuid);
                }
                final World world = player.getWorld();
                for (final BlockPosition b : OneStack.Blocks.get(uuid)) {
                    world.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
                }
                OneStack.Blocks.get(uuid).clear();
                final PlayerInventory inventory = player.getInventory();
                final ItemStack stack = ItemStackList.Blocks[Core.integerCache.get(uuid)[0]];
                inventory.setItem(0, stack);
                inventory.setItem(1, stack);
                OneStack.times.put(uuid, -1);
            }
            else if (!fell) {
                final int coord = OneStack.PlayerCoord.get(uuid);
                final int oldroom = coordToRoom(coord);
                final int newroom = Core.cal(oldroom, player.getLocation().getZ() > coord, OneStack.rooms);
                if (newroom == -1) {
                    player.sendMessage(StringList.nomapsavaliable);
                }
                else {
                    OneStack.PlayerCoord.put(uuid, roomToZ(newroom));
                    OneStack.rooms[oldroom] = true;
                    OneStack.rooms[newroom] = false;
                }
            }
            player.closeInventory();
        }
        PlayerUtils.teleport(((CraftPlayer)player).getHandle().playerConnection, 0.5, 100.0, OneStack.PlayerCoord.get(uuid) + 0.5, 270.0f, 0.0f);
    }
    
    public static int roomToZ(final int room) {
        return 6 * (room * (int)Math.pow(-1.0, room + 1) + room % 2);
    }
    
    public static int coordToRoom(final int z) {
        return (z == 0) ? 0 : (Math.abs(z / 6) - (Math.abs(z / 12) + z / 12) / (z / 6));
    }
    
    private static /* synthetic */ void lambda$0(final UUID uuid, final PlayerConnection connection, final World world, final Player player) {
        if (OneStack.PlayerCoord.containsKey(uuid)) {
            PlayerUtils.teleport(connection, 0.5, 100.0, OneStack.PlayerCoord.get(uuid) + 0.5, 270.0f, 0.0f);
            for (final BlockPosition b : OneStack.Blocks.get(uuid)) {
                world.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
            }
            OneStack.Blocks.get(uuid).clear();
            Core.waiting.remove(player.getUniqueId());
            final PlayerInventory inventory = player.getInventory();
            final ItemStack stack = ItemStackList.Blocks[Core.integerCache.get(uuid)[0]];
            inventory.setItem(0, stack);
            inventory.setItem(1, stack);
        }
    }
    
    private static /* synthetic */ void lambda$1(final UUID uuid, final Player player) {
        final int up = OneStack.times.get(uuid) + 1;
        OneStack.times.put(uuid, up);
        player.getScoreboard().getTeam(StringList.SideBar[1]).setSuffix(Core.tickToTime(up));
        if (up >= 2400) {
            Fell(player, false);
            player.sendMessage(StringList.cancelled);
        }
    }
}
