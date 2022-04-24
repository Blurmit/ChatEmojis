package me.blurmit.chatemojis.bungeecord.commands;

import me.blurmit.chatemojis.bungeecord.ChatEmojis;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class RemoveEmojiCommand extends Command {

    private final ChatEmojis plugin;

    public RemoveEmojiCommand(ChatEmojis plugin) {
        super("removeemoji");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("chatemojis.remove")) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "You do not have permission to use this command."));
            return;
        }

        if (args.length != 1) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "Correct usage: /removeemoji <emoji name>"));
            return;
        }

        String emojiName = args[0];

        if (!plugin.getEmojiManager().getStorage().containsKey(emojiName.toLowerCase())) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "The emoji you specified does not exist."));
            return;
        }

        plugin.getEmojiManager().removeEmoji(emojiName);
        sender.sendMessage(new TextComponent(ChatColor.GREEN + "Removed emoji successfully!"));
    }

}
