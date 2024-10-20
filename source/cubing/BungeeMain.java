package cubing;

import cubing.api.MysqlAPI;
import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.config.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.util.logging.Level;

public class BungeeMain extends Plugin {
    public static Configuration config;

    public BungeeMain() {
        super();
    }

    public void onEnable() {
        (speedcubingLib.logger = BungeeCord.getInstance().getLogger()).log(Level.INFO, "[speedcubingLib] BungeeCord Detected.");
        final File folder = new File(this.getDataFolder(), "");
        if (!folder.exists()) {
            folder.mkdir();
        }
        final File file = new File(this.getDataFolder() + "/config.yml");
        try {
            if (!file.exists()) {
                Files.copy(this.getResourceAsStream("config.yml"), file.toPath(), new CopyOption[0]);
            }
            BungeeMain.config = ConfigurationProvider.getProvider((Class) YamlConfiguration.class).load(file);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        try {
            if (BungeeMain.config.getBoolean("Mysql.enabled")) {
                new MysqlAPI().Load(BungeeMain.config.getString("Mysql.url"), BungeeMain.config.getString("Mysql.user"), BungeeMain.config.getString("Mysql.password"));
            }
        } catch (final Exception e2) {
            speedcubingLib.logger.log(Level.INFO, "[speedcubingLib] broken config.yml");
        }
    }
}
