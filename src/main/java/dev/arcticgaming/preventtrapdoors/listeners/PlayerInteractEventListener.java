package dev.arcticgaming.preventtrapdoors.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import static dev.arcticgaming.preventtrapdoors.PreventTrapdoors.PREVENT_TRAPDOORS;

public class PlayerInteractEventListener implements Listener {


    @EventHandler
    public static void preventTrapdoorUsage(PlayerInteractEvent event) {

        LocalPlayer player = WorldGuardPlugin.inst().wrapPlayer(event.getPlayer());
        Location loc = BukkitAdapter.adapt(event.getClickedBlock().getLocation());

        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();

        boolean hasBypass = player.hasPermission("pt.bypass");



        if (query.testState(loc, player, PREVENT_TRAPDOORS) && !hasBypass) {
            if (event.getClickedBlock().getType() == Material.OAK_TRAPDOOR ||
                    event.getClickedBlock().getType() == Material.DARK_OAK_TRAPDOOR ||
                    event.getClickedBlock().getType() == Material.BIRCH_TRAPDOOR ||
                    event.getClickedBlock().getType() == Material.SPRUCE_TRAPDOOR ||
                    event.getClickedBlock().getType() == Material.JUNGLE_TRAPDOOR ||
                    event.getClickedBlock().getType() == Material.ACACIA_TRAPDOOR ||
                    event.getClickedBlock().getType() == Material.MANGROVE_TRAPDOOR ||
                    event.getClickedBlock().getType() == Material.CRIMSON_TRAPDOOR ||
                    event.getClickedBlock().getType() == Material.WARPED_TRAPDOOR) {
                event.setCancelled(true);
            }
        }
    }
}
