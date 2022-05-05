package net.leviathan.permissioncore.manager;

import net.leviathan.permissioncore.PermissionCore;
import net.leviathan.permissioncore.Rank;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;


public class RankManager {

    private PermissionCore main;
    private File file;
    private YamlConfiguration config;
    private HashMap<UUID, PermissionAttachment> permissions = new HashMap<>();
    public RankManager(PermissionCore main) {
        this.main = main;

        if (!main.getDataFolder().exists()) {
            main.getDataFolder().mkdir();
        }

        file = new File(main.getDataFolder(), "ranks.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        config = YamlConfiguration.loadConfiguration(file);


    }
    public void setRank(UUID uuid, Rank rank, Boolean firstJoin) {
        if (Bukkit.getOfflinePlayer(uuid).isOnline() && !firstJoin) {
            Player player = Bukkit.getPlayer(uuid);
            PermissionAttachment attachment;
            if (permissions.containsKey(uuid)) {
                attachment = permissions.get(uuid);
            } else {
                attachment = player.addAttachment(main);
                permissions.put(uuid, attachment);
            }

            for (String permission : getRank(uuid).getPermissions()) {
                if (player.hasPermission(permission)) {
                    attachment.unsetPermission(permission);
                }
            }
            for (String permission : rank.getPermissions()) {
                attachment.setPermission(permission, true);
            }
        }
        config.set(uuid.toString(), rank.name());
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (Bukkit.getOfflinePlayer(uuid).isOnline()) {
            Player player = Bukkit.getPlayer(uuid);
            main.getNametagManager().removeTag(player);
            main.getNametagManager().newTag(player);
        }

    }
    public Rank getRank(UUID uuid) {
        return Rank.valueOf(config.getString(uuid.toString()));
    }
    public HashMap<UUID, PermissionAttachment> getPermissions() {
        return permissions;
    }
}
