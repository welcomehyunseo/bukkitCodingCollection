package me.welcomehyunseo.frozenzombie;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.type.Snow;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Stray;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandKit implements CommandExecutor {
    private Main main;

    CommandKit(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            Stray stray = (Stray) player.getWorld().spawnEntity(player.getLocation(), EntityType.STRAY);

            stray.getEquipment().setItemInMainHand(null);

            new BukkitRunnable() {
                @Override
                public void run() {
                    if(stray.isDead()) this.cancel();

                    makeParticle(Particle.SNOWFLAKE, stray.getLocation(), 100, 1, 1, 1);
                    makeSnow(stray.getLocation());
                    player.sendMessage("make snow block!");
                }
            }.runTaskTimerAsynchronously(main, 20, 20);
        }
        return false;
    }

    public void makeParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ) {
        for(Player player : main.getServer().getOnlinePlayers()) {
            player.spawnParticle(particle, location, count, offsetX, offsetY, offsetZ);
        }

    }

    public void makeSnow(Location location) {
    }
}

