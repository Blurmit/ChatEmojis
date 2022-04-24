package me.blurmit.chatemojis.bungeecord.emojis;

import com.google.common.io.ByteStreams;
import me.blurmit.chatemojis.bungeecord.ChatEmojis;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;

public class ConfigManager {

    private final ChatEmojis plugin;
    private File configurationFile;
    private Configuration configuration;

    public ConfigManager(ChatEmojis plugin) {
        this.plugin = plugin;
        this.configurationFile = new File(plugin.getDataFolder(), "config.yml");
    }

    public Configuration getConfig() {
        return configuration;
    }

    public void loadConfig() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        configurationFile = new File(plugin.getDataFolder(), "config.yml");

        if (!configurationFile.exists()) {
            try {
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.yml");
                OutputStream outputStream = new FileOutputStream(configurationFile);

                ByteStreams.copy(inputStream, outputStream);
                configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configurationFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, configurationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configurationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
