package me.blurmit.chatemojis.bungeecord.commands;

import me.blurmit.chatemojis.bungeecord.ChatEmojis;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

import java.util.stream.Collectors;

public class EmojisCommand extends Command {

    private final ChatEmojis plugin;

    public EmojisCommand(ChatEmojis plugin) {
        super("emojis");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("chatemojis.list")) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "You do not have permission to use this command."));
            return;
        }

        int emojisAmount = plugin.getEmojiManager().getStorage().size();
        sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&e&lEmojis &7(&e&l" + emojisAmount + "&7)")));
        sender.sendMessage(new TextComponent(plugin.getEmojiManager().getStorage().keySet().stream()
                .collect(Collectors.joining(ChatColor.GRAY + ", " + ChatColor.YELLOW, ChatColor.YELLOW.toString(), ChatColor.GRAY.toString()))));
    }

}
