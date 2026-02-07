package org.septystudios.simplejoinmessage;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleJoinMessage extends JavaPlugin implements Listener {

    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("SimpleJoinMessage by Septy Studios has been initialized.");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        if (!getConfig().getBoolean("join.enabled", true)) {
            event.joinMessage(null);
            return;
        }

        Player player = event.getPlayer();
        String path = "join.worlds." + player.getWorld().getName();
        String raw = getConfig().getString(path);

        if (raw == null) {
            raw = getConfig().getString("join.default-message", "");
        }

        event.joinMessage(format(raw, player));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent event) {
        if (!getConfig().getBoolean("quit.enabled", true)) {
            event.quitMessage(null);
            return;
        }

        String raw = getConfig().getString("quit.default-message", "");
        event.quitMessage(format(raw, event.getPlayer()));
    }

    private Component format(String input, Player player) {
        if (input == null || input.isEmpty()) return null;

        String online = getConfig().getString("placeholders.online", "%online%");
        String max = getConfig().getString("placeholders.max", "%max%");

        String msg = input.replace("<player>", player.getName())
                .replace(online, String.valueOf(Bukkit.getOnlinePlayers().size()))
                .replace(max, String.valueOf(Bukkit.getMaxPlayers()));

        if (getConfig().getBoolean("settings.use-minimessage", true)) {
            return miniMessage.deserialize(msg);
        }
        return LegacyComponentSerializer.legacyAmpersand().deserialize(msg);
    }
}