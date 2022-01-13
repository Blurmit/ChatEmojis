package me.blurmit.chatemojis.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

public class messagesUtil
{

    public void sendColoredMessage(CommandSender sender, String message)
    {
        sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
    }

}
