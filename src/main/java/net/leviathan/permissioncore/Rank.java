package net.leviathan.permissioncore;

import org.bukkit.ChatColor;

public enum Rank {
    OWNER(ChatColor.RED + "[OWNER]"),
    ADMIN(ChatColor.RED + "[ADMIN]"),
    MODERATOR(ChatColor.DARK_GREEN + "[MOD]"),
    HELPER(ChatColor.DARK_BLUE + "[HELPER]"),

    PATREON(ChatColor.DARK_BLUE + ""),
    MEMBER(ChatColor.GRAY + "");

    private String display;
    Rank(String display) {
        this.display = display;

    }

    public String getDisplay() {return display; }
}
