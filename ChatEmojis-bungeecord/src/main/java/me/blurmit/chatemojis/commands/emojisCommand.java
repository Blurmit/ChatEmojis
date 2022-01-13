package me.blurmit.chatemojis.commands;

import me.blurmit.chatemojis.ChatEmojisBungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class emojisCommand extends Command
{

    ChatEmojisBungeeCord EmojisPlugin = ChatEmojisBungeeCord.getInstance();

    public emojisCommand() {
        super("emojis");
    }

    @Override
    public void execute(CommandSender sender, String[] args)
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
                        .replaceAll("%emojis_amount%", String.valueOf(emojisAmount))
                        .replaceAll("%emojis%", ChatColor.translateAlternateColorCodes('&', emojisLister.toString())));
            }
        }

        if (args.length == 1)
        {

            if (args[0].equalsIgnoreCase("reload"))
            {
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
                    EmojisPlugin.messagesUtil.sendColoredMessage(sender, emojisList
                                    .replaceAll("%emojis_amount%", String.valueOf(emojisAmount))
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
                EmojisPlugin.messagesUtil.sendColoredMessage(sender, emojisList
                        .replaceAll("%emojis_amount%", String.valueOf(emojisAmount))
                        .replaceAll("%emojis%", ChatColor.translateAlternateColorCodes('&', emojisLister.toString())));
            }
        }

    }
}
