package dev.arcticgaming.preventtrapdoors;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import dev.arcticgaming.preventtrapdoors.listeners.PlayerInteractEventListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PreventTrapdoors extends JavaPlugin implements Listener {

    public static StateFlag PREVENT_TRAPDOORS;

    @Override
    public void onLoad() {
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        try {
            StateFlag flag = new StateFlag("prevent-trapdoors", false);
            registry.register(flag);
            PREVENT_TRAPDOORS = flag;
        } catch (FlagConflictException e) {
            Flag<?> existing = registry.get("prevent-trapdoors");
            if (existing instanceof StateFlag) {
                PREVENT_TRAPDOORS = (StateFlag) existing;
            } else {
                getLogger().severe(ChatColor.DARK_RED + "Another Flag conflicts with Prevent Trapdoors!");
            }
        }
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info(ChatColor.YELLOW + "Plugin Loaded!");

        Flags.registerAll();

        Bukkit.getPluginManager().registerEvents(this, this);
        final PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerInteractEventListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info(ChatColor.RED + "Plugin Disabled");
    }

    public static Plugin getPlugin(){
        return getPlugin(PreventTrapdoors.class);
    }
}
