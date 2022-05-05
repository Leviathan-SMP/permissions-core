package net.leviathan.permissioncore.Commands;

import net.leviathan.permissioncore.PermissionCore;
import net.leviathan.permissioncore.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {

    private PermissionCore main;
    public RankCommand(PermissionCore main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.isOp()) {
                if (args.length == 2) {
                    if (Bukkit.getOfflinePlayer(args[0]) != null) {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

                        for (Rank rank : Rank.values()) {
                            if (rank.name().equalsIgnoreCase(args[1])) {
                                main.getRankManager().setRank(target.getUniqueId(), rank);
                                player.sendMessage(ChatColor.GREEN + "You successfully set " + ChatColor.YELLOW + target.getName() + ChatColor.GREEN + " to " + ChatColor.YELLOW + rank.getDisplay());
                                if (target.isOnline()) {
                                    target.getPlayer().sendMessage(ChatColor.GREEN + "Your group has been set to " + ChatColor.YELLOW + rank.getDisplay());
                                }
                                return false ;
                            }

                        }
                        player.sendMessage(ChatColor.RED + "That is not a valid group name! " + ChatColor.DARK_GRAY + "(ERR_BAD_ARGS)");
                    } else {
                        player.sendMessage(ChatColor.RED + "This user has never joined Opus before! " + ChatColor.DARK_GRAY + "(ERR_PLAYER_NOT_FOUND)");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "This command format is incorrect! " + ChatColor.DARK_GRAY + "(ERR_BAD_ARGS)");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You cannot use this! " + ChatColor.DARK_GRAY + "(ERR_INSUFFICIENT_PERMISSION)");
            }
        }
        return false;
    }

}
