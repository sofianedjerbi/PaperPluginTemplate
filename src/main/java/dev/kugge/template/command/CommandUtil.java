package dev.kugge.template.command;

import dev.kugge.template.config.ConfigHolder;
import org.bukkit.command.CommandSender;

public class CommandUtil {
    public static boolean hasNoPermission(CommandSender sender, Boolean sendMessage, String... permissions) {
        for (String p : permissions) if (sender.hasPermission(p)) return false;
        if (sendMessage) handleNoPermission(sender);
        return true;
    }

    public static boolean handleNoPermission(CommandSender sender) {
        sender.sendMessage(ConfigHolder.getMiniMessage("language.permission"));
        return true;
    }

    public static boolean handleBadUsage(CommandSender sender) {
        sender.sendMessage(ConfigHolder.getMiniMessage("language.usage"));
        return true;
    }
}
