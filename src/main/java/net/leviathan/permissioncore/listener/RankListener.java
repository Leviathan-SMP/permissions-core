package net.leviathan.permissioncore.listener;

import net.leviathan.permissioncore.PermissionCore;
import net.leviathan.permissioncore.Rank;
import net.leviathan.permissioncore.manager.NametagManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

public class RankListener implements Listener {

    private PermissionCore main;
    public RankListener(PermissionCore main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        if (!player.hasPlayedBefore()) {
            main.getRankManager().setRank(player.getUniqueId(), Rank.MEMBER, true);
        }
        main.getNametagManager().setNametags(player);
        main.getNametagManager().newTag(player);

        PermissionAttachment attachment;
        if (main.getRankManager().getPermissions().containsKey(player.getUniqueId())) {
            attachment = main.getRankManager().getPermissions().get(player.getUniqueId());
        } else {
            attachment = player.addAttachment(main);
            main.getRankManager().getPermissions().put(player.getUniqueId(), attachment);
        }
        for (String permission : main.getRankManager().getRank(player.getUniqueId()).getPermissions()) {
            attachment.setPermission(permission, true);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        main.getNametagManager().removeTag(e.getPlayer());
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        e.setCancelled(true);
        Player player = e.getPlayer();
        Bukkit.broadcastMessage(main.getRankManager().getRank(player.getUniqueId()).getDisplay() + " " + player.getName() + ChatColor.DARK_GRAY + ChatColor.BOLD + " » " + ChatColor.WHITE + e.getMessage());
    }
}
