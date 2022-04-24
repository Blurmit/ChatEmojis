package me.blurmit.chatemojis.bungeecord;

import me.blurmit.chatemojis.bungeecord.commands.AddEmojiCommand;
import me.blurmit.chatemojis.bungeecord.commands.EmojisCommand;
import me.blurmit.chatemojis.bungeecord.commands.RemoveEmojiCommand;
import me.blurmit.chatemojis.bungeecord.emojis.ConfigManager;
import me.blurmit.chatemojis.bungeecord.emojis.EmojiManager;
import me.blurmit.chatemojis.bungeecord.listeners.onChat;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class ChatEmojis extends Plugin {

    private EmojiManager emojiManager;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        ProxyServer.getInstance().getLogger().info("The plugin has been successfully enabled!");
        ProxyServer.getInstance().getPluginManager().registerListener(this, new onChat(this));

        ProxyServer.getInstance().getPluginManager().registerCommand(this, new AddEmojiCommand(this));
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new RemoveEmojiCommand(this));
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new EmojisCommand(this));

        this.emojiManager = new EmojiManager(this);
        this.configManager = new ConfigManager(this);

        getConfigManager().loadConfig();
        getEmojiManager().loadEmojis();
    }

    public EmojiManager getEmojiManager() {
        return emojiManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

}
