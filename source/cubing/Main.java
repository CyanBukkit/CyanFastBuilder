package cubing;

import cubing.api.MysqlAPI;
import cubing.bukkit.Glow;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.logging.Level;

public class Main extends JavaPlugin {
    public Main() {
        super();
    }

    public void onEnable() {
        (speedcubingLib.logger = Bukkit.getLogger()).log(Level.INFO, "[speedcubingLib] Bukkit Detected.");
        this.saveDefaultConfig();
        try {
            final FileConfiguration config = this.getConfig();
            if (config.getBoolean("Mysql.enabled")) {
                new MysqlAPI().Load(config.getString("Mysql.url"), config.getString("Mysql.user"), config.getString("Mysql.password"));
            }
        } catch (final Exception e) {
            speedcubingLib.logger.log(Level.INFO, "[speedcubingLib] broken config.yml");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        try {
            final Field field = Enchantment.class.getDeclaredField("acceptingNew");
            field.setAccessible(true);
            field.set(null, true);
            final Glow glow = new Glow(70);
            Enchantment.registerEnchantment(glow);
        } catch (final Exception ignored) {
        }
    }
}
