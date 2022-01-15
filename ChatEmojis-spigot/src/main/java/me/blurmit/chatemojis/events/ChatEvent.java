package me.blurmit.chatemojis.events;

import me.blurmit.chatemojis.ChatEmojisSpigot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener
{

    ChatEmojisSpigot EmojisPlugin = ChatEmojisSpigot.getInstance();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event)
    {
        String message = event.getMessage();
        if (!message.startsWith("/emojis"))
        {
            String[] translateEmoji;
            String emoji;
            String emojiTranslation;

            for (String emojiList : EmojisPlugin.config.getStringList("Emojis"))
            {
                translateEmoji = emojiList.split(", ");
                emoji = translateEmoji[0];
                emojiTranslation = translateEmoji[1];
                message = message.replaceAll(emoji, emojiTranslation);
            }
            event.setMessage(message);
        }
    }
}
