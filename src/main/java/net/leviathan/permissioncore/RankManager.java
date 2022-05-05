package net.leviathan.permissioncore;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


public class RankManager {
    private File file;
    private YamlConfiguration config;
    public RankManager(PermissionCore main) {
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
    public void setRank(UUID uuid, Rank rank) {
        config.set(uuid.toString(), rank.name());
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public Rank getRank(UUID uuid) {
        return Rank.valueOf(config.getString(uuid.toString()));
    }
}
