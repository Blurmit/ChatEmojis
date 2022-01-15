package me.blurmit.chatemojis.commands;

import me.blurmit.chatemojis.ChatEmojisSpigot;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

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
                EmojisPlugin.messagesUtil.sendColoredMessage(sender, emojisList
                        .replace("{emojis_amount}", String.valueOf(emojisAmount))
                        .replace("{emojis}", ChatColor.translateAlternateColorCodes('&', emojisLister.toString())));
            }
        }

        if (args.length >= 1)
        {

            if (args[0].equalsIgnoreCase("reload"))
            {
                EmojisPlugin.reloadConfig();
                EmojisPlugin.messagesUtil.sendColoredMessage(sender, EmojisPlugin.messages.getString("reload-message"));
            }
            else if (args[0].equalsIgnoreCase("add"))
            {

                if (args.length == 3)
                {
                    List<String> emojis = EmojisPlugin.config.getStringList("Emojis");
                    emojis.add(args[1] + ", " + args[2]);
                    EmojisPlugin.config.set("Emojis", emojis);
                    EmojisPlugin.saveConfig();
                    EmojisPlugin.messagesUtil.sendColoredMessage(sender, EmojisPlugin.messages.getString("add-message")
                            .replace("{emoji}", args[1]));
                }
                else
                {
                    EmojisPlugin.messagesUtil.sendColoredMessage(sender, "&cInvalid usage! Usage: /emojis add <emoji> <emojiTranslation>");
                }

            }
            else if (args[0].equalsIgnoreCase("remove"))
            {

                if (args.length >= 2)
                {

                    List<String> emojiList = EmojisPlugin.config.getStringList("Emojis");
                    String[] translateEmoji;
                    String emoji;
                    String emojis;

                    for (int i = 0; i < emojiList.size(); ++i)
                    {
                        emojis = emojiList.get(i);
                        translateEmoji = emojis.split(", ");
                        emoji = translateEmoji[0];

                        if (emoji.equalsIgnoreCase(args[1]))
                        {
                            emojiList.remove(i);
                            EmojisPlugin.config.set("Emojis", emojiList);
                            EmojisPlugin.saveConfig();
                            EmojisPlugin.messagesUtil.sendColoredMessage(sender, EmojisPlugin.messages.getString("remove-message")
                                    .replace("{emoji}", args[1]));
                        }
                        else
                        {

                            if (i  == emojiList.size()-1)
                            {
                                EmojisPlugin.messagesUtil.sendColoredMessage(sender, EmojisPlugin.messages.getString("remove-error")
                                        .replace("{emoji}", args[1]));
                            }

                        }
                    }
                }
                else
                {
                    EmojisPlugin.messagesUtil.sendColoredMessage(sender, "&cInvalid usage! Usage: /emojis remove <emoji>");
                }

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
                    EmojisPlugin.messagesUtil.sendColoredMessage(sender, emojisList
                            .replace("{emojis_amount}", String.valueOf(emojisAmount))
                            .replace("{emojis}", ChatColor.translateAlternateColorCodes('&', emojisLister.toString())));
                }
            }
        }
        return false;
    }
}
