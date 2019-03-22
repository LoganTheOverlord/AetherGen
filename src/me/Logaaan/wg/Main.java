package me.Logaaan.wg;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin implements Listener {
	
	public boolean aether;
	public boolean goodies;
	public int ah = 150;
	
	public void onEnable() {
		if (this.getConfig().isSet("aether") == false) {
			this.getConfig().set("aether", false);
			saveConfig();
		}
		if (this.getConfig().isSet("aether_goodies") == false) {
			this.getConfig().set("aether_goodies", false);
			saveConfig();
		}
		if (this.getConfig().isSet("aether_height") == false) {
			this.getConfig().set("aether_height", 150);
			saveConfig();
		}
		if (this.getConfig().getBoolean("aether") == true) {
			aether = true;
			ah = this.getConfig().getInt("aether_height");
			goodies = this.getConfig().getBoolean("aether_gooies");
		} else {
			aether = false;
		}
		this.getServer().getPluginManager().registerEvents(this, this);
	
		/*this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				for (Entity e : Items) {
					if (e.getLocation().getX() > 18) {
						Location tp = e.getLocation();
						tp.setX(tp.getX() - 0.4D);
						e.teleport(tp);
					}
					if (e.getLocation().getX() < 16) {
						Location tp = e.getLocation();
						tp.setX(tp.getX() + 0.4D);
						e.teleport(tp);
					}
				}
				
			}
			
			
		}, 1L, 1L);*/
	}
	
	public void onDisable() {
		
	}
	
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
	    return new CustomGen(this);
	}
	
	/*@EventHandler
	public void onPM(PlayerMoveEvent e) {
		float yaw = e.getPlayer().getLocation().getYaw();
		float pitch = e.getPlayer().getLocation().getPitch();
		if (e.getPlayer().getLocation().getX() > 18) {
			Location tp = e.getPlayer().getLocation();
			tp.setPitch(pitch);
			tp.setYaw(yaw);
			tp.setX(tp.getX() - 0.4D);
			e.getPlayer().teleport(tp);
		}
		if (e.getPlayer().getLocation().getX() < 16) {
			Location tp = e.getPlayer().getLocation();
			tp.setPitch(pitch);
			tp.setYaw(yaw);
			tp.setX(tp.getX() + 0.4D);
			e.getPlayer().teleport(tp);
		}
	}
	
	@EventHandler
	public void onDamaged(EntityDamageEvent e) {
		if (!e.isCancelled()) {
			for (int i = 0; i < 40; i++) {
				e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.DIG_STONE, 0.3F, 1);
				e.getEntity().getWorld().playEffect(e.getEntity().getLocation().add(new Vector(0,0.5D,0)), Effect.TILE_BREAK, 152);
			}
		}
	}
	
	@EventHandler
	public void onWater(BlockPhysicsEvent e) {
		if (e.getBlock().getX() > 18) {
			e.setCancelled(true);
		}
		if (e.getBlock().getX() < 16) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (e.getBlockPlaced().getX() > 18) {
			e.setCancelled(true);
		}
		if (e.getBlockPlaced().getX() < 16) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void EntitySpawnEvent(CreatureSpawnEvent e) {
		Items.add(e.getEntity());
	}
	
	@EventHandler
	public void onThrow(ItemSpawnEvent e) {
		Items.add(e.getEntity());
		if (e.getEntity().getLocation().getX() > 18) {
			Location tp = e.getEntity().getLocation();
			tp.setX(tp.getX() - 0.4D);
			e.getEntity().teleport(tp);
		}
		if (e.getEntity().getLocation().getX() < 16) {
			Location tp = e.getEntity().getLocation();
			tp.setX(tp.getX() + 0.4D);
			e.getEntity().teleport(tp);
		}
	}*/


}
