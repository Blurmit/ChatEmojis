package me.blurmit.chatemojis.spigot.listeners;

import me.blurmit.chatemojis.spigot.ChatEmojis;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onChat implements Listener {

    private final ChatEmojis plugin;

    public onChat(ChatEmojis plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        final String message = event.getMessage();

        plugin.getEmojiManager().getStorage().keySet().forEach(emoji -> {
            if (message.contains(":" + emoji + ":")) {
                event.setMessage(message.replace(":" + emoji + ":", plugin.getEmojiManager().getStorage().get(emoji)));
            }
        });

    }

}
