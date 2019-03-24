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
	public boolean aether2;
	public boolean goodies;
	public int ao;
	public int ao2;
	public double sa;
	public double sa2;
	public int ah = 150;
	public int ah2 = 100;
	public double sp;
	public double sp2;
	public double f;
	public double f2;
	public double a;
	public double a2;
	public double cave_radius;
	public int cave_rarity;
	public int cave_frequency;
	public int cave_min;
	public int cave_max;
	public int cave_individual_rarity;
	public int cave_pocket_chance;
	public int dung;
	public int treechance;
	public int coalc;
	public int coals;
	public boolean coal;
	public int ironc;
	public int irons;
	public boolean iron;
	public int goldc;
	public int golds;
	public boolean gold;
	public int lapisc;
	public int lapiss;
	public boolean lapis;
	public int diac;
	public int dias;
	public boolean dia;
	public int dirtc;
	public int dirts;
	public boolean dirt;
	public int gravelc;
	public int gravels;
	public boolean gravel;
	public int redc;
	public int reds;
	public boolean red;
	public int emec;
	public int emes;
	public boolean eme;
	public boolean fastgen;
	public int bigtree;
	public int pinecount;
	public int normalcount;
	public int junglecount;
	public int birchcount;
	public int dungcount;
	public boolean features;
	
	public void onEnable() {
		if (this.getConfig().isSet("aether") == false) {
			this.getConfig().set("aether", true);
			saveConfig();
		}
		if (this.getConfig().isSet("prefab_tree_normal_count")) {
			normalcount = getConfig().getInt("prefab_tree_normal_count");
			if (normalcount <= 1) {
				normalcount = 2;
				getServer().getLogger().info("prefab_tree_normal_count had invalid value (lesser or equal to 1), setting to 2");
			}
		} else {
			getConfig().set("prefab_tree_normal_count",4);
			normalcount = 4;
			saveConfig();
		}
		if (this.getConfig().isSet("prefab_tree_pine_count")) {
			pinecount = getConfig().getInt("prefab_tree_pine_count");
			if (pinecount <= 1) {
				pinecount = 2;
				getServer().getLogger().info("prefab_tree_pine_count had invalid value (lesser or equal to 1), setting to 2");
			}
		} else {
			getConfig().set("prefab_tree_pine_count",4);
			pinecount = 4;
			saveConfig();
		}
		if (this.getConfig().isSet("prefab_tree_jungle_count")) {
			junglecount = getConfig().getInt("prefab_tree_jungle_count");
			if (junglecount <= 1) {
				junglecount = 2;
				getServer().getLogger().info("prefab_tree_jungle_count had invalid value (lesser or equal to 1), setting to 2");
			}
		} else {
			getConfig().set("prefab_tree_jungle_count",3);
			junglecount = 3;
			saveConfig();
		}
		if (this.getConfig().isSet("prefab_tree_birch_count")) {
			birchcount = getConfig().getInt("prefab_tree_birch_count");
			if (birchcount <= 1) {
				birchcount = 2;
				getServer().getLogger().info("prefab_tree_birch_count had invalid value (lesser or equal to 1), setting to 2");
			}
		} else {
			getConfig().set("prefab_tree_birch_count",4);
			birchcount = 4;
			saveConfig();
		}
		if (this.getConfig().isSet("prefab_dungeon_count")) {
			dungcount = getConfig().getInt("prefab_dungeon_count");
			if (dungcount <= 1) {
				dungcount = 2;
				getServer().getLogger().info("prefab_dungeon_count had invalid value (lesser or equal to 1), setting to 2");
			}
		} else {
			getConfig().set("prefab_dungeon_count",2);
			dungcount = 2;
			saveConfig();
		}
		if (this.getConfig().isSet("generate_additional_features")) {
			coal = getConfig().getBoolean("generate_additional_features");
		} else {
			getConfig().set("generate_additional_features",true);
			features = true;
			saveConfig();
		}
		if (this.getConfig().isSet("generate_coal")) {
			coal = getConfig().getBoolean("generate_coal");
		} else {
			getConfig().set("generate_coal",true);
			coal = true;
			saveConfig();
		}
		if (this.getConfig().isSet("coal_chance")) {
			coalc = getConfig().getInt("coal_chance");
		} else {
			getConfig().set("coal_chance",500);
			coalc = 500;
			saveConfig();
		}
		if (this.getConfig().isSet("coal_amount")) {
			coals = getConfig().getInt("coal_amount");
		} else {
			getConfig().set("coal_amount",7);
			coals = 7;
			saveConfig();
		}

		if (this.getConfig().isSet("bigtree_chance")) {
			bigtree = getConfig().getInt("bigtree_chance");
		} else {
			getConfig().set("bigtree_chance",1000);
			bigtree = 1000;
			saveConfig();
		}

		
		
		if (this.getConfig().isSet("generate_iron")) {
			iron = getConfig().getBoolean("generate_iron");
		} else {
			getConfig().set("generate_iron",true);
			iron = true;
			saveConfig();
		}
		if (this.getConfig().isSet("iron_chance")) {
			ironc = getConfig().getInt("iron_chance");
		} else {
			getConfig().set("iron_chance",2000);
			ironc = 2000;
			saveConfig();
		}
		if (this.getConfig().isSet("iron_amount")) {
			irons = getConfig().getInt("iron_amount");
		} else {
			getConfig().set("iron_amount",5);
			irons = 5;
			saveConfig();
		}

		
		if (this.getConfig().isSet("generate_lapis")) {
			lapis = getConfig().getBoolean("generate_lapis");
		} else {
			getConfig().set("generate_lapis",true);
			lapis = true;
			saveConfig();
		}
		if (this.getConfig().isSet("lapis_chance")) {
			lapisc = getConfig().getInt("lapis_chance");
		} else {
			getConfig().set("lapis_chance",4000);
			lapisc = 4000;
			saveConfig();
		}
		if (this.getConfig().isSet("lapis_amount")) {
			lapiss = getConfig().getInt("lapis_amount");
		} else {
			getConfig().set("lapis_amount",2);
			lapiss = 2;
			saveConfig();
		}

		
		
		if (this.getConfig().isSet("generate_redstone")) {
			red = getConfig().getBoolean("generate_redstone");
		} else {
			getConfig().set("generate_redstone",true);
			red = true;
			saveConfig();
		}
		if (this.getConfig().isSet("redstone_chance")) {
			redc = getConfig().getInt("redstone_chance");
		} else {
			getConfig().set("redstone_chance",4000);
			redc = 4000;
			saveConfig();
		}
		if (this.getConfig().isSet("redstone_amount")) {
			reds = getConfig().getInt("redstone_amount");
		} else {
			getConfig().set("redstone_amount",4);
			reds = 4;
			saveConfig();
		}

		
		
		if (this.getConfig().isSet("generate_diamonds")) {
			dia = getConfig().getBoolean("generate_diamonds");
		} else {
			getConfig().set("generate_diamonds",true);
			dia = true;
			saveConfig();
		}
		if (this.getConfig().isSet("diamond_chance")) {
			diac = getConfig().getInt("diamond_chance");
		} else {
			getConfig().set("diamond_chance",10000);
			diac = 10000;
			saveConfig();
		}
		if (this.getConfig().isSet("diamond_amount")) {
			dias = getConfig().getInt("diamond_amount");
		} else {
			getConfig().set("diamond_amount",3);
			dias = 3;
			saveConfig();
		}

		
		
		if (this.getConfig().isSet("generate_emeralds")) {
			eme = getConfig().getBoolean("generate_emeralds");
		} else {
			getConfig().set("generate_emeralds",true);
			eme = true;
			saveConfig();
		}
		if (this.getConfig().isSet("emerald_chance")) {
			emec = getConfig().getInt("emerald_chance");
		} else {
			getConfig().set("emerald_chance",6000);
			emec = 6000;
			saveConfig();
		}
		if (this.getConfig().isSet("emerald_amount")) {
			emes = getConfig().getInt("emerald_amount");
		} else {
			getConfig().set("emerald_amount",1);
			emes = 1;
			saveConfig();
		}

		
		
		if (this.getConfig().isSet("generate_gold")) {
			gold = getConfig().getBoolean("generate_gold");
		} else {
			getConfig().set("generate_gold",true);
			gold = true;
			saveConfig();
		}
		if (this.getConfig().isSet("gold_chance")) {
			goldc = getConfig().getInt("gold_chance");
		} else {
			getConfig().set("gold_chance",4000);
			goldc = 4000;
			saveConfig();
		}
		if (this.getConfig().isSet("gold_amount")) {
			golds = getConfig().getInt("gold_amount");
		} else {
			getConfig().set("gold_amount",4);
			golds = 4;
			saveConfig();
		}

		
		
		if (this.getConfig().isSet("generate_dirt")) {
			dirt = getConfig().getBoolean("generate_dirt");
		} else {
			getConfig().set("generate_dirt",true);
			dirt = true;
			saveConfig();
		}
		if (this.getConfig().isSet("dirt_chance")) {
			dirtc = getConfig().getInt("dirt_chance");
		} else {
			getConfig().set("dirt_chance",900); dirtc = 900;
			saveConfig();
		}
		if (this.getConfig().isSet("dirt_amount")) {
			dirts = getConfig().getInt("dirt_amount");
		} else {
			getConfig().set("dirt_amount",15);
			dirts = 15;
			saveConfig();
		}

		
		
		
		if (this.getConfig().isSet("generate_gravel")) {
			gravel = getConfig().getBoolean("generate_gravel");
		} else {
			getConfig().set("generate_gravel",true);
			gravel = true;
			saveConfig();
		}
		if (this.getConfig().isSet("gravel_chance")) {
			gravelc = getConfig().getInt("gravel_chance");
		} else {
			getConfig().set("gravel_chance",1900);
			gravelc = 1900;
			saveConfig();
		}
		if (this.getConfig().isSet("gravel_amount")) {
			gravels = getConfig().getInt("gravel_amount");
		} else {
			getConfig().set("gravel_amount",15);
			gravels = 15;
			saveConfig();
		}

		if (this.getConfig().isSet("cave_rarity") == false) {
			this.getConfig().set("cave_rarity", 3);
			saveConfig();
		}
		if (this.getConfig().isSet("dungeon_chance") == false) {
			this.getConfig().set("dungeon_chance", 10);
			saveConfig();
		}

		if (this.getConfig().isSet("cave_frequency") == false) {
			this.getConfig().set("cave_frequency", 35);
			saveConfig();
		}
		if (this.getConfig().isSet("cave_min_y") == false) {
			this.getConfig().set("cave_min_y", 1);
			saveConfig();
		}
		if (this.getConfig().isSet("cave_max_y") == false) {
			this.getConfig().set("cave_max_y", 200);
			saveConfig();
		}
		if (this.getConfig().isSet("cave_individual_rarity") == false) {
			this.getConfig().set("cave_individual_rarity", 20);
			saveConfig();
		}
		if (this.getConfig().isSet("cave_pocket_chance") == false) {
			this.getConfig().set("cave_pocket_chance", 3);
			saveConfig();
		}
		if (this.getConfig().isSet("cave_radius_multiplier") == false) {
			this.getConfig().set("cave_radius_multiplier", 1.1);
			saveConfig();
		}
		if (this.getConfig().isSet("aether_2") == false) {
			this.getConfig().set("aether_2", false);
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
		if (this.getConfig().isSet("aether_spawnrate") == false) {
			this.getConfig().set("aether_spawnrate", 0.1);
			saveConfig();
		}
		if (this.getConfig().isSet("aether_2_spawnrate") == false) {
			this.getConfig().set("aether_2_spawnrate", 0.3);
			saveConfig();
		}
		if (this.getConfig().isSet("aether_frequency") == false) {
			this.getConfig().set("aether_frequency", 1.45D);
			saveConfig();
		}

		if (this.getConfig().isSet("aether_2_frequency") == false) {
			this.getConfig().set("aether_2_frequency", 1.45D);
			saveConfig();
		}
		if (this.getConfig().isSet("aether_amplitude") == false) {
			this.getConfig().set("aether_amplitude", 0.95D);
			saveConfig();
		}
		if (this.getConfig().isSet("aether_2_amplitude") == false) {
			this.getConfig().set("aether_2_amplitude", 0.95D);
			saveConfig();
		}

		if (this.getConfig().isSet("aether_octaves") == false) {
			this.getConfig().set("aether_octaves", 7);
			saveConfig();
		}
		if (this.getConfig().isSet("aether_2_octaves") == false) {
			this.getConfig().set("aether_2_octaves", 8);
			saveConfig();
		}
		if (this.getConfig().isSet("aether_scale") == false) {
			this.getConfig().set("aether_scale", 0.0337);
			saveConfig();
		}
		if (this.getConfig().isSet("aether_2_scale") == false) {
			this.getConfig().set("aether_2_scale", 0.0367);
			saveConfig();
		}
		if (this.getConfig().isSet("aether_2_height") == false) {
			this.getConfig().set("aether_2_height", 100);
			saveConfig();
		}
		if (this.getConfig().isSet("general_tree_chance") == false) {
			this.getConfig().set("general_tree_chance", 50);
			saveConfig();
		} else {
			this.treechance = this.getConfig().getInt("general_tree_chance");
		}
		if (this.getConfig().getBoolean("aether") == true) {
			aether = true;
			ah = this.getConfig().getInt("aether_height");
			goodies = this.getConfig().getBoolean("aether_gooies");
			ao = this.getConfig().getInt("aether_octaves");
			sa = this.getConfig().getDouble("aether_scale");
			sp = this.getConfig().getDouble("aether_spawnrate");
			f = this.getConfig().getDouble("aether_frequency");
			a = this.getConfig().getDouble("aether_amplitude");
		} else {
			aether = false;
		}
		if (this.getConfig().getBoolean("aether_2") == true) {
			aether2 = true;
			ah2 = this.getConfig().getInt("aether_2_height");
			ao2 = this.getConfig().getInt("aether_2_octaves");
			sa2 = this.getConfig().getDouble("aether_2_scale");
			sp2 = this.getConfig().getDouble("aether_2_spawnrate");
			f2 = this.getConfig().getDouble("aether_2_frequency");
			a2 = this.getConfig().getDouble("aether_2_amplitude");
		} else {
			aether2 = false;
		}
		if (this.getConfig().isSet("cave_rarity")) {
			cave_rarity = getConfig().getInt("cave_rarity");
			cave_frequency = getConfig().getInt("cave_frequency");
			cave_min = getConfig().getInt("cave_min_y");
			cave_max = getConfig().getInt("cave_max_y");
			cave_individual_rarity = getConfig().getInt("cave_individual_rarity");
			cave_pocket_chance = getConfig().getInt("cave_individual_chance");
			cave_radius = getConfig().getDouble("cave_radius");
		}
		if (this.getConfig().isSet("dungeon_chance")) {
			dung = getConfig().getInt("dungeon_chance");
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
	    return new CustomGen(worldName, this);
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
