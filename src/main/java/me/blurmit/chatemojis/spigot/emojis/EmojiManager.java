package me.blurmit.chatemojis.spigot.emojis;

import me.blurmit.chatemojis.spigot.ChatEmojis;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class EmojiManager {

    private final ChatEmojis plugin;
    private final Map<String, String> emojiStorage;

    public EmojiManager(ChatEmojis plugin) {
        this.plugin = plugin;
        this.emojiStorage = new HashMap<>();
    }

    public Map<String, String> getStorage() {
        return emojiStorage;
    }

    public void addEmoji(@NotNull String name, @NotNull String translation) {
        plugin.getConfig().set("Emojis." + name, translation);
        plugin.saveConfig();
        reloadEmojis();
    }

    public void removeEmoji(@NotNull String name) {
        plugin.getConfig().set("Emojis." + name, null);
        plugin.saveConfig();
        reloadEmojis();
    }

    public void loadEmojis() {
        plugin.getConfig().getConfigurationSection("Emojis").getKeys(false).forEach(emoji -> {
            emojiStorage.put(emoji, plugin.getConfig().getString("Emojis." + emoji));
        });
    }

    public void reloadEmojis() {
        getStorage().clear();
        loadEmojis();
    }

}
