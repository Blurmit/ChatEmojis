package me.blurmit.chatemojis;

import com.google.common.io.ByteStreams;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import me.blurmit.chatemojis.commands.emojisCommand;
import me.blurmit.chatemojis.events.ChatEventBungee;
import me.blurmit.chatemojis.utils.messagesUtil;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public final class ChatEmojisBungeeCord extends Plugin {

    public Configuration config;
    public Configuration messages;
    public File configFile = new File(getDataFolder(), "config.yml");
    public File messagesFile = new File(getDataFolder(), "messages.yml");
    private static ChatEmojisBungeeCord instance;
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
        getProxy().getPluginManager().registerCommand(this, new emojisCommand());
        getProxy().getPluginManager().registerListener(this, new ChatEventBungee());
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

    public void saveConfig()
    {
        try {

            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, configFile);
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(messages, messagesFile);

        } catch (IOException e) {
            getLogger().severe("Failed to save the configuration!");
            e.printStackTrace();
        }
    }

    public static ChatEmojisBungeeCord getInstance()
    {
        return instance;
    }

    private static void setInstance(ChatEmojisBungeeCord instance)
    {
        ChatEmojisBungeeCord.instance = instance;
    }

}
