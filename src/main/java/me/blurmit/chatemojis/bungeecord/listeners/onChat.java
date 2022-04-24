package me.blurmit.chatemojis.bungeecord.listeners;

import me.blurmit.chatemojis.bungeecord.ChatEmojis;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class onChat implements Listener {

    private ChatEmojis plugin;

    public onChat(ChatEmojis plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(ChatEvent event) {

        final String message = event.getMessage();

        plugin.getEmojiManager().getStorage().keySet().forEach(emoji -> {
            if (message.contains(":" + emoji + ":")) {
                event.setMessage(message.replace(":" + emoji + ":", plugin.getEmojiManager().getStorage().get(emoji)));
            }
        });


    }

}
