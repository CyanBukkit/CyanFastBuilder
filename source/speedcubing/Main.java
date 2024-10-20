package speedcubing;

import org.bukkit.plugin.java.*;
import java.nio.charset.*;
import com.google.common.io.*;
import java.io.*;
import org.bukkit.configuration.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;
import speedcubing.BukkitEventListener.*;
import speedcubing.List.*;
import speedcubing.Core.*;
import org.bukkit.configuration.file.*;
import org.bukkit.*;

public class Main extends JavaPlugin
{
    private YamlConfiguration config;
    
    public Main() {
        super();
        this.config = new YamlConfiguration();
    }
    
    public void onEnable() {
        final File file = new File(this.getDataFolder() + File.separator + "config.yml");
        try {
            this.config.loadFromString(Files.toString(file, Charset.forName("UTF-8")));
        }
        catch (final IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        Bukkit.getPluginManager().registerEvents((Listener)new FoodLevelChange(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new BlockPlace(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new BlockBreak(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new PlayerChangedWorld(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new EntityDamage(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new PlayerJoin(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new PlayerQuit(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new PlayerMove(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new PlayerInteract(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new PlayerDropItem(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new InventoryClick(), (Plugin)this);
        this.getServer().createWorld(new WorldCreator("Long"));
        Bukkit.getWorld("Long").setDifficulty(Difficulty.PEACEFUL);
        Bukkit.getWorld("Long").setTime(6000L);
        this.getServer().createWorld(new WorldCreator("Inclined"));
        Bukkit.getWorld("Inclined").setDifficulty(Difficulty.PEACEFUL);
        Bukkit.getWorld("Inclined").setTime(6000L);
        this.getServer().createWorld(new WorldCreator("Short"));
        Bukkit.getWorld("Short").setDifficulty(Difficulty.PEACEFUL);
        Bukkit.getWorld("Short").setTime(6000L);
        this.getServer().createWorld(new WorldCreator("Onestack"));
        Bukkit.getWorld("Onestack").setDifficulty(Difficulty.PEACEFUL);
        Bukkit.getWorld("Onestack").setTime(6000L);
        this.getServer().createWorld(new WorldCreator("Inclinedshort"));
        Bukkit.getWorld("Inclinedshort").setDifficulty(Difficulty.PEACEFUL);
        Bukkit.getWorld("Inclinedshort").setTime(6000L);
        Bukkit.getWorld("world").setDifficulty(Difficulty.PEACEFUL);
        Bukkit.getWorld("world").setTime(6000L);
        new Long().Load();
        new Short().Load();
        new Inclined().Load();
        new OneStack().Load();
        new InclinedShort().Load();
        new ItemStackList().Load();
        new BlockEditor().Load();
    }
    
    public FileConfiguration getConfig() {
        return (FileConfiguration)this.config;
    }
    
    public void saveConfig() {
        try {
            this.config.save(new File(this.getDataFolder() + File.separator + "config.yml"));
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getStringFromConfig(final String str) {
        return ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(str));
    }
    
    public String[] getStringListFromConfig(final String str) {
        final String[] list = new String[this.getConfig().getStringList(str).size()];
        for (int i = 0; i < this.getConfig().getStringList(str).size(); ++i) {
            list[i] = ChatColor.translateAlternateColorCodes('&', (String)this.getConfig().getStringList(str).get(i));
        }
        return list;
    }
    
    public void onDisable() {
        final File index = new File(Bukkit.getWorlds().get(0).getWorldFolder() + "/playerdata");
        for (final String s : index.list()) {
            new File(index.getPath(), s).delete();
        }
        index.delete();
    }
}
