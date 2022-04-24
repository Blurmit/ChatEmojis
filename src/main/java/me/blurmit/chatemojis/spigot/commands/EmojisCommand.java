package me.blurmit.chatemojis.spigot.commands;

import me.blurmit.chatemojis.spigot.ChatEmojis;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

public class EmojisCommand implements CommandExecutor {

    private final ChatEmojis plugin;

    public EmojisCommand(ChatEmojis plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("chatemojis.list")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        int emojisAmount = plugin.getEmojiManager().getStorage().size();
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&lEmojis &7(&e&l" + emojisAmount + "&7)"));
        sender.sendMessage(plugin.getEmojiManager().getStorage().keySet().stream()
                .collect(Collectors.joining(ChatColor.GRAY + ", " + ChatColor.YELLOW, ChatColor.YELLOW.toString(), ChatColor.GRAY.toString())));

        return true;
    }

}
