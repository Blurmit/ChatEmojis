package me.blurmit.chatemojis.spigot.commands;

import me.blurmit.chatemojis.spigot.ChatEmojis;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class AddEmojiCommand implements CommandExecutor {

    private final ChatEmojis plugin;

    public AddEmojiCommand(ChatEmojis plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("chatemojis.add")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Correct usage: /addemoji <emoji name> <emoji translation>");
            return true;
        }

        String emojiName = args[0];
        String emojiTranslation = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        if (plugin.getEmojiManager().getStorage().containsKey(emojiName.toLowerCase())) {
            sender.sendMessage(ChatColor.RED + "An emoji with that name already exists.");
            return true;
        }

        plugin.getEmojiManager().addEmoji(emojiName, emojiTranslation);
        sender.sendMessage(ChatColor.GREEN + "Added emoji successfully!");

        return true;
    }

}
