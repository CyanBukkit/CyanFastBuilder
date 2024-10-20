package speedcubing.Core;

import org.bukkit.entity.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.*;
import speedcubing.*;
import speedcubing.List.*;
import org.bukkit.plugin.*;
import net.minecraft.server.v1_8_R3.*;

import java.text.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.scoreboard.*;

public class Long
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
        Long.used = 0;
        Long.rooms = new boolean[31];
        Long.times = new HashMap<UUID, Integer>();
        Long.MainTimer = new HashMap<UUID, Integer>();
        Long.session = new ArrayList<UUID>();
        Long.finishedtask = new HashMap<UUID, Integer>();
        Long.Blocks = new HashMap<UUID, Set<BlockPosition>>();
        Long.score = new HashMap<UUID, Integer>();
        PlayerCoord = new HashMap<UUID, Integer>();
    }
    
    public Long() {
        super();
    }
    
    public void Load() {
        for (int i = 0; i < 31; ++i) {
            Long.rooms[i] = true;
        }
    }
    
    public static void Finish(final Player player) {
        final World world = Bukkit.getWorld("Long");
        final UUID uuid = player.getUniqueId();
        final int time = Long.times.get(uuid);
        if (time < 140 || !Long.MainTimer.containsKey(uuid)) {
            BungeePluginMessage.switchServer(player, "Lobby");
            return;
        }
        Core.waiting.add(uuid);
        Bukkit.getScheduler().cancelTask((int)Long.MainTimer.get(uuid));
        Long.MainTimer.remove(uuid);
        final int msrecord = time * 50;
        final String display = Core.tickToTime(time);
        final String[] sidebar = StringList.SideBar;
        final int pb = Core.integerCache.get(uuid)[2];
        if (pb == 0 || pb > msrecord) {
            player.getScoreboard().getTeam(sidebar[9]).setSuffix(display);
            Core.integerCache.get(uuid)[2] = msrecord;
        }
        Long.times.put(uuid, -1);
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
        final PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        connection.sendPacket((Packet)new PacketPlayOutTitle(0, 60, 5));
        connection.sendPacket((Packet)new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + "��aTime: ��e%time%".replace("%time%", display) + "\"}")));
        if (Long.score.get(uuid) > time || Long.score.get(uuid) == 0) {
            int i;
            for (i = 0; i < Long.session.size(); ++i) {
                if (Long.score.get(Long.session.get(i)) > time || Long.score.get(Long.session.get(i)) == 0) {
                    Long.session.remove(uuid);
                    Long.session.add(i, uuid);
                    break;
                }
            }
            Long.score.put(uuid, time);
            if (i < 4) {
                final ArrayList<String> prefix = new ArrayList<String>();
                final ArrayList<String> suffix = new ArrayList<String>();
                final int size = Long.session.size();
                for (int j = i; j < 3; ++j) {
                    if (size <= j) {
                        prefix.add("��7-,---");
                        suffix.add("");
                    }
                    else {
                        final UUID ses = Long.session.get(j);
                        final int sc = Long.score.get(ses);
                        if (sc == 0) {
                            prefix.add("��7-,---");
                            suffix.add("");
                        }
                        else {
                            final String[] x = SideBarAPI.prefixSuffixSplit("��7" + Bukkit.getPlayer(ses).getName() + "��8: ��e" + Core.tickToTime(sc));
                            prefix.add(x[0]);
                            suffix.add(x[1]);
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
        Long.finishedtask.put(uuid, Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getPlugin((Class)Main.class), () -> {
            if (Long.PlayerCoord.containsKey(uuid2)) {
                PlayerUtils.teleport(connection2, 0.5, 100.0, Long.PlayerCoord.get(uuid2) + 0.5, 270.0f, 0.0f);
                Long.Blocks.get(uuid2).iterator();
                final Iterator iterator2;
                while (iterator2.hasNext()) {
                    final BlockPosition b = iterator2.next();
                    world2.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
                }
                Long.Blocks.get(uuid2).clear();
                Core.waiting.remove(uuid2);
                final PlayerInventory inventory = player2.getInventory();
                final ItemStack stack = ItemStackList.Blocks[Core.integerCache.get(uuid2)[0]];
                inventory.setItem(0, stack);
                inventory.setItem(1, stack);
            }
        }, 40L));
    }
    
    public static void Join(final Player player, final boolean kick) {
        if (Long.used == 31) {
            player.sendMessage("There is no enough servers. please report this to speedcubing.");
            if (kick) {
                new Timer().schedule(new TimerTask() {
                    private final /* synthetic */ Player val$player;
                    
                    Long$1() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        BungeePluginMessage.switchServer(player, "lobby");
                    }
                }, 500L);
            }
            return;
        }
        int room = -1;
        for (int h = 0; h < 31; ++h) {
            if (Long.rooms[h]) {
                room = h;
                break;
            }
        }
        Long.rooms[room] = false;
        ++Long.used;
        final UUID uuid = player.getUniqueId();
        final int coord = roomToZ(room);
        Long.PlayerCoord.put(uuid, coord);
        final Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        final Objective objective = scoreboard.registerNewObjective("sidebar", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        final String[] sidebar = StringList.SideBar;
        objective.setDisplayName(sidebar[13]);
        new Thread(() -> {
            if (((Main)Main.getPlugin((Class)Main.class)).getConfig().get("stats." + obj) == null) {
                ((Main)Main.getPlugin((Class)Main.class)).getConfig().set("stats." + obj, (Object)"0/5/0/0/0/0/0");
                ((Main)Main.getPlugin((Class)Main.class)).saveConfig();
            }
            final String[] prof = ((Main)Main.getPlugin((Class)Main.class)).getConfig().getString("stats." + obj).split("/");
            final Integer[] x = { Integer.parseInt(prof[0]), Integer.parseInt(prof[1]), Integer.parseInt(prof[2]), Integer.parseInt(prof[3]), Integer.parseInt(prof[4]), Integer.parseInt(prof[5]), Integer.parseInt(prof[6]) };
            Core.integerCache.put(obj, x);
            SideBarAPI.sidebarTeams(scoreboard2, new String[][] { { array[12].replace("%date%", new SimpleDateFormat("yyyy/MM/dd").format(new Date())) }, { array[11] }, { array[10] }, { "", array[9], (x[2] == 0) ? "��7-,---" : Core.msToTime(x[2]) }, { array[8] }, { array[7] }, { "", array[6], "" }, { "", array[5], "" }, { "", array[4], "" }, { array[3] }, { array[2] }, { "", array[1], "��7-,---" }, { array[0] } }, new int[] { 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 });
            final int size = Long.session.size();
            for (int j = 0; j < 3; ++j) {
                String prefix;
                String suffix;
                if (size <= j) {
                    prefix = "��7-,---";
                    suffix = "";
                }
                else {
                    final UUID ses = Long.session.get(j);
                    final int sc = Long.score.get(ses);
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
                final Team t = scoreboard2.getTeam("��" + (j + 4) + "��f");
                t.setPrefix(prefix);
                t.setSuffix(suffix);
            }
            player2.setScoreboard(scoreboard2);
            final PlayerInventory inventory = player2.getInventory();
            inventory.clear();
            final ItemStack stack = ItemStackList.Blocks[x[0]];
            inventory.setItem(0, stack);
            inventory.setItem(1, stack);
            inventory.setItem(2, ItemStackList.pickaxe);
            inventory.setItem(7, ItemStackList.Settings);
            inventory.setItem(8, ItemStackList.navigator);
            return;
        }).start();
        player.teleport(new Location(Bukkit.getWorld("Long"), 0.5, 100.0, coord + 0.5, 270.0f, 0.0f));
        Long.Blocks.put(uuid, new HashSet<BlockPosition>());
        Long.session.add(uuid);
        Long.times.put(uuid, -1);
        Long.score.put(uuid, 0);
    }
    
    public static void Quit(final Player player) {
        final UUID uuid = player.getUniqueId();
        if (Long.MainTimer.containsKey(uuid)) {
            Bukkit.getScheduler().cancelTask((int)Long.MainTimer.get(uuid));
            Long.MainTimer.remove(uuid);
        }
        final World world = Bukkit.getWorld("Long");
        for (final BlockPosition b : Long.Blocks.get(uuid)) {
            world.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
        }
        Long.Blocks.get(uuid).clear();
        Long.finishedtask.remove(uuid);
        Long.times.put(uuid, -1);
        Core.waiting.remove(uuid);
        final int x = Long.session.indexOf(uuid);
        Long.session.remove(uuid);
        Long.score.remove(uuid);
        if (x != -1 && x < 3) {
            final ArrayList<String> prefix = new ArrayList<String>();
            final ArrayList<String> suffix = new ArrayList<String>();
            final int size = Long.session.size();
            for (int j = x; j < 3; ++j) {
                if (size <= j) {
                    prefix.add("��7-,---");
                    suffix.add("");
                }
                else {
                    final UUID ses = Long.session.get(j);
                    final int sc = Long.score.get(ses);
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
        --Long.used;
        Long.rooms[coordToRoom(Long.PlayerCoord.get(uuid))] = true;
        Long.PlayerCoord.remove(uuid);
    }
    
    public static void validPlaceBlock(final Player player, final BlockPosition b) {
        final UUID uuid = player.getUniqueId();
        if (Long.times.get(uuid) == -1) {
            Long.times.put(uuid, 0);
            Long.MainTimer.put(uuid, Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)Main.getPlugin((Class)Main.class), () -> {
                final int up = Long.times.get(uuid2) + 1;
                Long.times.put(uuid2, up);
                player2.getScoreboard().getTeam(StringList.SideBar[1]).setSuffix(Core.tickToTime(up));
                if (up >= 2400) {
                    Fell(player2, false);
                    player2.sendMessage(StringList.cancelled);
                }
                return;
            }, 0L, 1L));
        }
        Long.Blocks.get(uuid).add(b);
        final PlayerInventory inventory = player.getInventory();
        final int empty = 1 - player.getInventory().getHeldItemSlot();
        if (inventory.getItem(empty) == null) {
            inventory.setItem(empty, ItemStackList.Blocks[Core.integerCache.get(uuid)[0]]);
        }
    }
    
    public static void Fell(final Player player, final boolean fell) {
        final UUID uuid = player.getUniqueId();
        if (Core.waiting.contains(uuid)) {
            Bukkit.getScheduler().cancelTask((int)Long.finishedtask.get(uuid));
            Long.finishedtask.remove(uuid);
            final World world = player.getWorld();
            for (final BlockPosition b : Long.Blocks.get(uuid)) {
                world.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
            }
            Long.Blocks.get(uuid).clear();
            Core.waiting.remove(uuid);
            final PlayerInventory inventory = player.getInventory();
            final ItemStack stack = ItemStackList.Blocks[Core.integerCache.get(uuid)[0]];
            inventory.setItem(0, stack);
            inventory.setItem(1, stack);
        }
        else {
            if (Long.times.get(uuid) != -1) {
                if (Long.MainTimer.containsKey(uuid)) {
                    Bukkit.getScheduler().cancelTask((int)Long.MainTimer.get(uuid));
                    Long.MainTimer.remove(uuid);
                }
                final World world = player.getWorld();
                for (final BlockPosition b : Long.Blocks.get(uuid)) {
                    world.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
                }
                Long.Blocks.get(uuid).clear();
                final PlayerInventory inventory = player.getInventory();
                final ItemStack stack = ItemStackList.Blocks[Core.integerCache.get(uuid)[0]];
                inventory.setItem(0, stack);
                inventory.setItem(1, stack);
                Long.times.put(uuid, -1);
            }
            else if (!fell) {
                final int coord = Long.PlayerCoord.get(uuid);
                final int oldroom = coordToRoom(coord);
                final int newroom = Core.cal(oldroom, player.getLocation().getZ() > coord, Long.rooms);
                if (newroom == -1) {
                    player.sendMessage(StringList.nomapsavaliable);
                }
                else {
                    Long.PlayerCoord.put(uuid, roomToZ(newroom));
                    Long.rooms[oldroom] = true;
                    Long.rooms[newroom] = false;
                }
            }
            player.closeInventory();
        }
        PlayerUtils.teleport(((CraftPlayer)player).getHandle().playerConnection, 0.5, 100.0, Long.PlayerCoord.get(uuid) + 0.5, 270.0f, 0.0f);
    }
    
    public static int roomToZ(final int room) {
        return 6 * (room * (int)Math.pow(-1.0, room + 1) + room % 2);
    }
    
    public static int coordToRoom(final int z) {
        return (z == 0) ? 0 : (Math.abs(z / 6) - (Math.abs(z / 12) + z / 12) / (z / 6));
    }
    
    private static /* synthetic */ void lambda$0(final UUID uuid, final PlayerConnection connection, final World world, final Player player) {
        if (Long.PlayerCoord.containsKey(uuid)) {
            PlayerUtils.teleport(connection, 0.5, 100.0, Long.PlayerCoord.get(uuid) + 0.5, 270.0f, 0.0f);
            for (final BlockPosition b : Long.Blocks.get(uuid)) {
                world.getBlockAt(b.getX(), b.getY(), b.getZ()).setType(Material.AIR);
            }
            Long.Blocks.get(uuid).clear();
            Core.waiting.remove(uuid);
            final PlayerInventory inventory = player.getInventory();
            final ItemStack stack = ItemStackList.Blocks[Core.integerCache.get(uuid)[0]];
            inventory.setItem(0, stack);
            inventory.setItem(1, stack);
        }
    }
    
    private static /* synthetic */ void lambda$1(final UUID obj, final Scoreboard scoreboard, final String[] array, final Player player) {
        if (((Main)Main.getPlugin((Class)Main.class)).getConfig().get("stats." + obj) == null) {
            ((Main)Main.getPlugin((Class)Main.class)).getConfig().set("stats." + obj, (Object)"0/5/0/0/0/0/0");
            ((Main)Main.getPlugin((Class)Main.class)).saveConfig();
        }
        final String[] prof = ((Main)Main.getPlugin((Class)Main.class)).getConfig().getString("stats." + obj).split("/");
        final Integer[] x = { Integer.parseInt(prof[0]), Integer.parseInt(prof[1]), Integer.parseInt(prof[2]), Integer.parseInt(prof[3]), Integer.parseInt(prof[4]), Integer.parseInt(prof[5]), Integer.parseInt(prof[6]) };
        Core.integerCache.put(obj, x);
        SideBarAPI.sidebarTeams(scoreboard, new String[][] { { array[12].replace("%date%", new SimpleDateFormat("yyyy/MM/dd").format(new Date())) }, { array[11] }, { array[10] }, { "", array[9], (x[2] == 0) ? "��7-,---" : Core.msToTime(x[2]) }, { array[8] }, { array[7] }, { "", array[6], "" }, { "", array[5], "" }, { "", array[4], "" }, { array[3] }, { array[2] }, { "", array[1], "��7-,---" }, { array[0] } }, new int[] { 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 });
        final int size = Long.session.size();
        for (int j = 0; j < 3; ++j) {
            String prefix;
            String suffix;
            if (size <= j) {
                prefix = "��7-,---";
                suffix = "";
            }
            else {
                final UUID ses = Long.session.get(j);
                final int sc = Long.score.get(ses);
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
    }
    
    private static /* synthetic */ void lambda$2(final UUID uuid, final Player player) {
        final int up = Long.times.get(uuid) + 1;
        Long.times.put(uuid, up);
        player.getScoreboard().getTeam(StringList.SideBar[1]).setSuffix(Core.tickToTime(up));
        if (up >= 2400) {
            Fell(player, false);
            player.sendMessage(StringList.cancelled);
        }
    }
}
