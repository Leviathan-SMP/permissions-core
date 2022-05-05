package net.leviathan.permissioncore.listener;

import net.leviathan.permissioncore.PermissionCore;

import net.leviathan.permissioncore.Rank;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class RankListener implements Listener {

    private PermissionCore main;
    public RankListener(PermissionCore main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        if (!player.hasPlayedBefore()) {
            main.getRankManager().setRank(player.getUniqueId(), Rank.MEMBER);
        }
    }
}
