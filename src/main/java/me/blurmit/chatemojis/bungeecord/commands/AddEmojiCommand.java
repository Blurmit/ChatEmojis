package me.blurmit.chatemojis.bungeecord.commands;

import me.blurmit.chatemojis.bungeecord.ChatEmojis;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

import java.util.Arrays;

public class AddEmojiCommand extends Command {

    private final ChatEmojis plugin;

    public AddEmojiCommand(ChatEmojis plugin) {
        super("addemoji");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!sender.hasPermission("chatemojis.add")) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "You do not have permission to use this command."));
            return;
        }

        if (args.length < 2) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "Correct usage: /addemoji <emoji name> <emoji translation>"));
            return;
        }

        String emojiName = args[0];
        String emojiTranslation = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        if (plugin.getEmojiManager().getStorage().containsKey(emojiName.toLowerCase())) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "An emoji with that name already exists."));
            return;
        }

        plugin.getEmojiManager().addEmoji(emojiName, emojiTranslation);
        sender.sendMessage(new TextComponent(ChatColor.GREEN + "Added emoji successfully!"));

    }

}
