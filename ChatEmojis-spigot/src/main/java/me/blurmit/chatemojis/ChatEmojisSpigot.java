package me.blurmit.chatemojis;

import com.google.common.io.ByteStreams;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import me.blurmit.chatemojis.commands.emojisCommand;
import me.blurmit.chatemojis.events.ChatEvent;
import me.blurmit.chatemojis.utils.messagesUtil;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatEmojisSpigot extends JavaPlugin
{

    public Configuration config;
    public Configuration messages;
    public File configFile = new File(getDataFolder(), "config.yml");
    public File messagesFile = new File(getDataFolder(), "messages.yml");
    private static ChatEmojisSpigot instance;
    public messagesUtil messagesUtil = new messagesUtil();

    public void onEnable()
    {
        setInstance(this);
        getLogger().info("Attempting to load configurations...");
        try {
            loadConfig();
        } catch (IOException e) {
            getLogger().severe("Failed to load configuration!");
        }
        getLogger().info("The plugin has been successfully loaded and enabled!");
        Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);
        getCommand("emojis").setExecutor(new emojisCommand());
    }

    public void onDisable()
    {
        getLogger().info("The plugin has been successfully unloaded and disabled!");
    }

    public void loadConfig() throws IOException
    {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        if (!this.configFile.exists()) {
            getLogger().warning("Configuration not found, creating a config new file...");
            try {
                InputStream is = getClass().getClassLoader().getResourceAsStream("config.yml");
                OutputStream os = new FileOutputStream(this.configFile);
                ByteStreams.copy(is, os);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create a new configuration file!", e);
            }
        }
        this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.configFile);
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(this.config, this.configFile);
        if (!this.messagesFile.exists())
            try {
                InputStream is = getClass().getClassLoader().getResourceAsStream("messages.yml");
                OutputStream os = new FileOutputStream(this.messagesFile);
                ByteStreams.copy(is, os);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create the messages file!", e);
            }
        this.messages = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.messagesFile);
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(this.messages, this.messagesFile);
    }

    public void reloadConfig()
    {
        try {

            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.configFile);
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(this.config, this.configFile);

            messages = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.messagesFile);
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(this.messages, this.messagesFile);

        } catch (IOException e) {
            getLogger().severe("Failed to reload the configuration!");
            e.printStackTrace();
        }
    }

    public static ChatEmojisSpigot getInstance()
    {
        return instance;
    }

    private static void setInstance(ChatEmojisSpigot instance)
    {
        ChatEmojisSpigot.instance = instance;
    }

}
