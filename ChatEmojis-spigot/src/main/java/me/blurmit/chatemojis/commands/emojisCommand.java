package me.blurmit.chatemojis.commands;

import me.blurmit.chatemojis.ChatEmojisSpigot;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class emojisCommand implements CommandExecutor
{

    ChatEmojisSpigot EmojisPlugin = ChatEmojisSpigot.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {

        if (args.length == 0)
        {
            int emojisAmount = EmojisPlugin.config.getStringList("Emojis").size();
            StringBuilder emojisLister = new StringBuilder();
            String emojis;
            String[] translateEmoji;
            String emojiTranslation;

            for(int i = 0; i < emojisAmount; ++i)
            {
                emojis = EmojisPlugin.config.getStringList("Emojis").get(i);
                translateEmoji = emojis.split(", ");
                emojiTranslation = translateEmoji[1];

                emojisLister.append(emojiTranslation);
                emojisLister.append("&e, &d");
            }

            for (String emojisList : EmojisPlugin.messages.getStringList("emojis-message"))
            {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', emojisList
                        .replaceAll("%emojis_amount%", String.valueOf(emojisAmount)))
                        .replaceAll("%emojis%", ChatColor.translateAlternateColorCodes('&', emojisLister.toString())));
            }
        }

        if (args.length == 1)
        {

            if (args[0].equalsIgnoreCase("reload")) {
                EmojisPlugin.reloadConfig();
                EmojisPlugin.messagesUtil.sendColoredMessage(sender, EmojisPlugin.messages.getString("reload-message"));
            }
            else
            {
                int emojisAmount = EmojisPlugin.config.getStringList("Emojis").size();
                StringBuilder emojisLister = new StringBuilder();
                String emojis;
                String[] translateEmoji;
                String emojiTranslation;

                for(int i = 0; i < emojisAmount; ++i)
                {
                    emojis = EmojisPlugin.config.getStringList("Emojis").get(i);
                    translateEmoji = emojis.split(", ");
                    emojiTranslation = translateEmoji[1];

                    emojisLister.append(emojiTranslation);
                    emojisLister.append("&e, &d");
                }

                for (String emojisList : EmojisPlugin.messages.getStringList("emojis-message"))
                {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', emojisList
                                    .replaceAll("%emojis_amount%", String.valueOf(emojisAmount)))
                            .replaceAll("%emojis%", ChatColor.translateAlternateColorCodes('&', emojisLister.toString())));
                }
            }

        }
        else if (args.length > 1)
        {
            int emojisAmount = EmojisPlugin.config.getStringList("Emojis").size();
            StringBuilder emojisLister = new StringBuilder();
            String emojis;
            String[] translateEmoji;
            String emojiTranslation;

            for(int i = 0; i < emojisAmount; ++i)
            {
                emojis = EmojisPlugin.config.getStringList("Emojis").get(i);
                translateEmoji = emojis.split(", ");
                emojiTranslation = translateEmoji[1];

                emojisLister.append(emojiTranslation);
                emojisLister.append("&e, &d");
            }

            for (String emojisList : EmojisPlugin.messages.getStringList("emojis-message"))
            {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', emojisList
                                .replaceAll("%emojis_amount%", String.valueOf(emojisAmount)))
                        .replaceAll("%emojis%", ChatColor.translateAlternateColorCodes('&', emojisLister.toString())));
            }
        }

        return false;
    }
}
