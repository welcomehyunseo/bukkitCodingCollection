package me.welcomehyunseo.frozenzombie;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Stray;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Main extends JavaPlugin  implements Listener  {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("fz").setExecutor(new CommandKit(this));
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity e1 = event.getEntity();
        Entity e2 = event.getDamager();
        if(e1 instanceof Player && e2 instanceof Stray) {
            Player player = (Player) e1;
            Stray stray = (Stray) e2;

//            player.sendMessage("damaged!");
            player.getLocation().getBlock().setType(Material.POWDER_SNOW);

            new BukkitRunnable() {
                Location loc = player.getLocation();
                @Override
                public void run() {
                    loc.getBlock().setType(Material.AIR);
                }
            }.runTaskLater(this, 20 * 3);
        }
    }
}
