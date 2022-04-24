package me.blurmit.chatemojis.spigot;

import me.blurmit.chatemojis.spigot.commands.AddEmojiCommand;
import me.blurmit.chatemojis.spigot.commands.EmojisCommand;
import me.blurmit.chatemojis.spigot.commands.RemoveEmojiCommand;
import me.blurmit.chatemojis.spigot.emojis.EmojiManager;
import me.blurmit.chatemojis.spigot.listeners.onChat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class ChatEmojis extends JavaPlugin {

    private EmojiManager emojiManager;

    @Override
    public void onEnable() {
        Bukkit.getLogger().log(Level.INFO, "The plugin have been successfully enabled!");
        Bukkit.getPluginManager().registerEvents(new onChat(this), this);

        getCommand("addemoji").setExecutor(new AddEmojiCommand(this));
        getCommand("removeemoji").setExecutor(new RemoveEmojiCommand(this));
        getCommand("emojis").setExecutor(new EmojisCommand(this));

        this.emojiManager = new EmojiManager(this);

        saveDefaultConfig();
        getEmojiManager().loadEmojis();
    }

    public EmojiManager getEmojiManager() {
        return emojiManager;
    }

}
