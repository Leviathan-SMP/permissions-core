package net.leviathan.permissioncore;

import net.leviathan.permissioncore.Commands.RankCommand;
import net.leviathan.permissioncore.listener.RankListener;

import net.leviathan.permissioncore.manager.NametagManager;
import net.leviathan.permissioncore.manager.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PermissionCore extends JavaPlugin {

    private RankManager rankManager;
    private static NametagManager nametagManager;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("rank").setExecutor(new RankCommand(this));

        rankManager = new RankManager(this);
        nametagManager = new NametagManager(this);

        Bukkit.getPluginManager().registerEvents(new RankListener(this), this);
    }
    public RankManager getRankManager() { return rankManager; }
    public NametagManager getNametagManager() { return nametagManager; }

    //public String getRank(Player player) { return Rank; }
}
