package me.blurmit.chatemojis.bungeecord.emojis;

import me.blurmit.chatemojis.bungeecord.ChatEmojis;
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
        plugin.getConfigManager().getConfig().set("Emojis." + name, translation);
        plugin.getConfigManager().saveConfig();
        reloadEmojis();
    }

    public void removeEmoji(@NotNull String name) {
        plugin.getConfigManager().getConfig().set("Emojis." + name, null);
        plugin.getConfigManager().saveConfig();
        reloadEmojis();
    }

    public void loadEmojis() {
        plugin.getConfigManager().getConfig().getSection("Emojis").getKeys().forEach(emoji -> {
            emojiStorage.put(emoji, plugin.getConfigManager().getConfig().getString("Emojis." + emoji));
        });
    }

    public void reloadEmojis() {
        getStorage().clear();
        loadEmojis();
    }

}
