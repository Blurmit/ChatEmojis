package me.blurmit.chatemojis.events;

import me.blurmit.chatemojis.ChatEmojisBungeeCord;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChatEventBungee implements Listener
{

    ChatEmojisBungeeCord EmojisPlugin = ChatEmojisBungeeCord.getInstance();

    @EventHandler
    public void onChat(ChatEvent event)
    {
        String message = event.getMessage();
        int emojisAmount = EmojisPlugin.config.getStringList("Emojis").size();
        String emojis;
        String[] translateEmoji;
        String emoji;
        String emojiTranslation;

        for(int i = 0; i < emojisAmount; ++i)
        {
            emojis = EmojisPlugin.config.getStringList("Emojis").get(i);
            translateEmoji = emojis.split(", ");
            emoji = translateEmoji[0];
            emojiTranslation = translateEmoji[1];
            message = message.replaceAll(emoji, emojiTranslation);
        }
        event.setMessage(message);
    }
}
