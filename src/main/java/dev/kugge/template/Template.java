package dev.kugge.template;

import dev.kugge.template.command.PluginCommand;
import dev.kugge.template.config.ConfigHolder;
import dev.kugge.template.watcher.LoginWatcher;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Template extends JavaPlugin {

    public static Logger logger;
    public static Template instance;

    @Override
    public void onEnable() {
        instance = this;
        logger = getLogger();
        ConfigHolder.loadConfig();
        registerEvents();
        registerCommands();
        registerTasks();
    }

    private void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new LoginWatcher(), this);
    }

    private void registerCommands(){
        getCommand("template").setExecutor(new PluginCommand());
        getCommand("template").setTabCompleter(PluginCommand.tabCompleter);
    }

    private void registerTasks(){
        // Anything scheduler related
    }
}
