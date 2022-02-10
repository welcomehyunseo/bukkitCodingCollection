package me.welcomehyunseo.waterdemon;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public final class WaterDemon extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onSpawnDrowned(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if(entity.getType() != EntityType.DROWNED) return;
        Drowned demon = (Drowned) entity;

        // set the properties
        demon.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 60));
//        demon.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 50));
        demon.setInvisible(true);
        demon.setSilent(true);

        new BukkitRunnable() {
            @Override
            public void run() {
//                print("running");
                LivingEntity livingEntity = demon.getTarget();


                if(livingEntity instanceof Player) {
                    randomEffect(demon.getLocation().add(0, 2.5, 0));
//                    print(livingEntity.getType().toString() + " " + Math.random());
                    if(livingEntity.getLocation().add(0, -0.5, 0).getBlock().getType() != Material.WATER) {
                        demon.setTarget(null);
//                        print("Target is on ground!");
                    }

                }

                if(demon.isDead()) {
                    this.cancel();
                }
            }

        }.runTaskTimerAsynchronously(this, 10, 10);
    }

    @EventHandler
    public void onDrownedDamage(EntityDamageByEntityEvent event) {
//        event.setDamage(Integer.MAX_VALUE);
    }

    public void randomEffect(Location location) {
        int num = (int) (Math.random() * 10);
//        print(String.valueOf(num));
        if(0 <= num && num <= 3) {
            makeParticle(Particle.WATER_SPLASH, location, 50, 0.2, 0.2, 0.2);
            playSound(location, Sound.BLOCK_STONE_BREAK, 1, 1);
        }
    }

    public void makeParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ) {
        for(Player player : getServer().getOnlinePlayers()) {
            player.spawnParticle(particle, location, count, offsetX, offsetY, offsetZ);
        }
    }

    public void playSound(Location location, Sound sound, float volume, float pitch) {
        for(Player player : getServer().getOnlinePlayers()) {
            player.playSound(location, sound, volume, pitch);
        }
    }


    public void print(String message) {
        for(Player player : getServer().getOnlinePlayers()) {
            player.sendMessage(message);
        }
    }

}
