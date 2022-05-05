package net.leviathan.permissioncore;

import org.bukkit.ChatColor;

public enum Rank {
    OWNER(ChatColor.RED + "[OWNER] "),
    ADMIN(ChatColor.RED + "[ADMIN] "),
    MODERATOR(ChatColor.DARK_GREEN + "[MOD] "),
    HELPER(ChatColor.DARK_BLUE + "[HELPER] "),

    PATREON("[]"),
    MEMBER("[]");

    private String display;
    Rank(String display) {
        this.display = display;

    }

    public String getDisplay() {return display; }
}
