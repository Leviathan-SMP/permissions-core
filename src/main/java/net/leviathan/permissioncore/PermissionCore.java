package net.leviathan.permissioncore;

import net.leviathan.permissioncore.Commands.RankCommand;
import net.leviathan.permissioncore.listener.RankListener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PermissionCore extends JavaPlugin {

    private RankManager rankManager;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("rank").setExecutor(new RankCommand());
        rankManager = new RankManager(this);
        Bukkit.getPluginManager().registerEvents(new RankListener(this), this);
    }
    public RankManager getRankManager() { return rankManager; }

}
