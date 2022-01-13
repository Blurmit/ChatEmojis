package me.blurmit.chatemojis.utils;

import me.blurmit.chatemojis.ChatEmojisSpigot;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class messagesUtil
{

    public void sendColoredMessage(CommandSender sender, String message)
    {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

}
