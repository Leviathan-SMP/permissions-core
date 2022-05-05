package net.leviathan.permissioncore;

import org.bukkit.ChatColor;

public enum Rank {
    OWNER(ChatColor.DARK_RED + "" + ChatColor.BOLD + "OWNER" + ChatColor.DARK_RED + " ", new String[]{""}),
    ADMIN(ChatColor.RED + "" +ChatColor.BOLD + "ADMIN" + ChatColor.RED + " ", new String[]{""}),
    MODERATOR(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "MOD" + ChatColor.GREEN + " ", new String[]{""}),
    HELPER(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "HELPER" + ChatColor.BLUE + " ", new String[]{""}),

    PATREON(ChatColor.DARK_BLUE + "", new String[]{}),
    MEMBER(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "MEMBER" + ChatColor.GRAY + " ", new String[]{});

    private String display;
    private String[] permissions;

    Rank(String display, String[] permissions) {
        this.display = display;
        this.permissions = permissions;

    }

    public String getDisplay() { return display; }
    public String[] getPermissions() { return permissions; }
}
