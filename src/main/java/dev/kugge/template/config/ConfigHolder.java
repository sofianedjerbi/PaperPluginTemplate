package dev.kugge.template.config;

import dev.kugge.template.Template;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class ConfigHolder {
    private static File configFile;
    public static FileConfiguration config;

    public static void loadConfig() {
        setupConfig();
        reloadConfig();
    }

    public static void setupConfig() {
        configFile = new File(Template.instance.getDataFolder(), "config.yml");
        if (!configFile.exists()) Template.instance.saveResource("config.yml", false);
    }

    public static void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public static Component getMiniMessage(@NotNull String path) {
        MiniMessage mm = MiniMessage.miniMessage();
        return mm.deserialize(config.getString(path));
    }
}
