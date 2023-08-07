package dev.kugge.template.command;

import dev.kugge.template.Template;
import dev.kugge.template.config.ConfigHolder;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PluginCommand implements CommandExecutor {

    public static final String PERMISSION_RELOAD = "template.reload";
    public static final String PERMISSION_VERSION = "template.version";

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String cl,
                             String[] args) {
        if (CommandUtil.hasNoPermission(sender, true, PERMISSION_RELOAD, PERMISSION_VERSION)) return true;
        if (args.length == 0) return CommandUtil.handleBadUsage(sender);
        if (args[0].equalsIgnoreCase("version")) return versionSubCommand(sender);
        if (args[0].equalsIgnoreCase("reload")) return reloadSubCommand(sender);
        return CommandUtil.handleBadUsage(sender);
    }

    private boolean versionSubCommand(CommandSender sender) {
        if (CommandUtil.hasNoPermission(sender, true, PERMISSION_VERSION)) return true;
        sender.sendMessage(
            ConfigHolder.getMiniMessage("language.version").append(
                PlainTextComponentSerializer.plainText().deserialize(Template.instance.getPluginMeta().getVersion())
            )
        );
        return true;
    }

    private boolean reloadSubCommand(CommandSender sender) {
        if (CommandUtil.hasNoPermission(sender, true, PERMISSION_RELOAD)) return true;
        ConfigHolder.reloadConfig();
        sender.sendMessage(ConfigHolder.getMiniMessage("language.reload"));
        return true;
    }

    public static final TabCompleter tabCompleter = (sender,command,alias,args) -> {
        if (args.length != 1) return null;
        if (CommandUtil.hasNoPermission(sender, false, PERMISSION_RELOAD, PERMISSION_VERSION)) return null;
        List<String> possibleCompletions = Arrays.asList("version", "reload");
        return StringUtil.copyPartialMatches(args[0], possibleCompletions, new ArrayList<>());
    };
}