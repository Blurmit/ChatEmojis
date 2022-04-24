package me.blurmit.chatemojis.spigot.commands;

import me.blurmit.chatemojis.spigot.ChatEmojis;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RemoveEmojiCommand implements CommandExecutor {

    private final ChatEmojis plugin;

    public RemoveEmojiCommand(ChatEmojis plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("chatemojis.remove")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Correct usage: /removeemoji <emoji name>");
            return true;
        }

        String emojiName = args[0];

        if (!plugin.getEmojiManager().getStorage().containsKey(emojiName.toLowerCase())) {
            sender.sendMessage(ChatColor.RED + "The emoji you specified does not exist.");
            return true;
        }

        plugin.getEmojiManager().removeEmoji(emojiName);
        sender.sendMessage(ChatColor.GREEN + "Removed emoji successfully!");

        return true;
    }

}
