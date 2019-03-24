package me.Logaaan.wg;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class PopulatorOre extends BlockPopulator {
	
	Main p;
	
	boolean coal;
	boolean iron;
	boolean gold;
	boolean dia;
	boolean red;
	boolean lapis;
	boolean dirt;
	boolean gravel;
	boolean emerald;
	
	
	int coalchance;
	int coalsize;
	int ironchance;
	int ironsize;
	int goldchance;
	int goldsize;
	int emeraldchance;
	int emeraldsize;
	int lapischance;
	int lapissize;
	int diachance;
	int diasize;
	int redstonechance;
	int redsize;

	int dirtchance;
	int dirtsize;
	int gravelchance;
	int gravelsize;
	
	public PopulatorOre(Main p) {
		this.p = p;
		coal = p.coal;
		iron = p.iron;
		gold = p.gold;
		dia = p.dia;
		red = p.red;
		lapis = p.lapis;
		dirt = p.dirt;
		gravel = p.gravel;
		emerald = p.eme;
		
		
		coalchance = p.coalc;
		coalsize = p.coals;
		ironchance = p.ironc;
		ironsize = p.irons;
		goldchance = p.goldc;
		goldsize = p.golds;
		emeraldchance = p.emec;
		emeraldsize = p.emes;
		lapischance = p.lapisc;
		lapissize = p.lapiss;
		diachance = p.diac;
		diasize = p.dias;
		redstonechance = p.redc;
		redsize = p.reds;

		dirtchance = p.dirtc;
		dirtsize = p.dirts;
		gravelchance = p.gravelc;
		gravelsize = p.gravels;
	}

	@Override
	public void populate(World arg0, Random arg1, Chunk arg2) {
		int x,z;
		x = arg2.getX();
		z = arg2.getZ();
		Chunk c = arg2;
		World w = arg0;
		for (int xx = 0; xx < 16; xx++) {
			for (int zz = 0; zz < 16; zz++) {
				for (int yy = w.getHighestBlockYAt(xx+x*16,zz+z*16); yy > 3; yy--) {
					if (c.getBlock(xx, yy, zz).getType().equals(Material.STONE) || c.getBlock(xx, yy, zz).getType().equals(Material.COBBLESTONE)) {
						if (coal == true) {
						if (new Random().nextInt(coalchance) <= 2) {
							c.getBlock(xx, yy, zz).setType(Material.COAL_ORE);
							for (int i = coalsize; i > 0; i--) {
								int rx = new Random().nextInt(2)-1;
								int rz = new Random().nextInt(2) - 1;
								int ry = new Random().nextInt(2)-1;
								if (c.getBlock(xx+rx, yy+ry, zz+rz).getType().equals(Material.STONE) || c.getBlock(xx+rx, yy+ry, zz+rz).getType().equals(Material.COBBLESTONE)) {
									c.getBlock(xx+rx, yy+ry, zz+rz).setType(Material.COAL_ORE);
								}
							}
						}
						}
						if (iron) {
						if (new Random().nextInt(ironchance) <= 1) {
							c.getBlock(xx, yy, zz).setType(Material.IRON_ORE);
							for (int i = ironsize; i > 0; i--) {
								int rx = new Random().nextInt(2)-1;
								int rz = new Random().nextInt(2) - 1;
								int ry = new Random().nextInt(2)-1;
								if (c.getBlock(xx+rx, yy+ry, zz+rz).getType().equals(Material.STONE) || c.getBlock(xx+rx, yy+ry, zz+rz).getType().equals(Material.COBBLESTONE)) {
									c.getBlock(xx+rx, yy+ry, zz+rz).setType(Material.IRON_ORE);
								}

							}
						}
						}
						if (gold) {
						if (new Random().nextInt(goldchance) <= 1) {
							c.getBlock(xx, yy, zz).setType(Material.GOLD_ORE);
							for (int i = goldsize; i > 0; i--) {
								int rx = new Random().nextInt(2)-1;
								int rz = new Random().nextInt(2) - 1;
								int ry = new Random().nextInt(2)-1;
								if (c.getBlock(xx+rx, yy+ry, zz+rz).getType().equals(Material.STONE) || c.getBlock(xx+rx, yy+ry, zz+rz).getType().equals(Material.COBBLESTONE)) {
									c.getBlock(xx+rx, yy+ry, zz+rz).setType(Material.GOLD_ORE);
								}
							}
						}
						}
						if (emerald) {
						if (new Random().nextInt(emeraldchance) <= 1) {
							c.getBlock(xx, yy, zz).setType(Material.EMERALD_ORE);
							for (int i = emeraldsize; i > 0; i--) {
								int rx = new Random().nextInt(2)-1;
								int rz = new Random().nextInt(2) - 1;
								int ry = new Random().nextInt(2)-1;
								if (c.getBlock(xx+rx, yy+ry, zz+rz).getType().equals(Material.STONE) || c.getBlock(xx+rx, yy+ry, zz+rz).getType().equals(Material.COBBLESTONE)) {
									c.getBlock(xx+rx, yy+ry, zz+rz).setType(Material.EMERALD_ORE);
								}

							}
						}
						}
						
						if (red) {
							if (new Random().nextInt(redstonechance) <= 1) {
								c.getBlock(xx, yy, zz).setType(Material.REDSTONE_ORE);
								for (int i = redsize; i > 0; i--) {
									int rx = new Random().nextInt(2)-1;
									int rz = new Random().nextInt(2) - 1;
									int ry = new Random().nextInt(2)-1;
									if (c.getBlock(xx+rx, yy+ry, zz+rz).getType().equals(Material.STONE) || c.getBlock(xx+rx, yy+ry, zz+rz).getType().equals(Material.COBBLESTONE)) {
										c.getBlock(xx+rx, yy+ry, zz+rz).setType(Material.REDSTONE_ORE);
									}

								}
							}
						}
						if (dia) {
							if (new Random().nextInt(diachance) <= 1) {
								c.getBlock(xx, yy, zz).setType(Material.DIAMOND_ORE);
								for (int i = diasize; i > 0; i--) {
									int rx = new Random().nextInt(2)-1;
									int rz = new Random().nextInt(2) - 1;
									int ry = new Random().nextInt(2)-1;
									if (c.getBlock(xx+rx, yy+ry, zz+rz).getType().equals(Material.STONE) || c.getBlock(xx+rx, yy+ry, zz+rz).getType().equals(Material.COBBLESTONE)) {
										c.getBlock(xx+rx, yy+ry, zz+rz).setType(Material.DIAMOND_ORE);
									}
								}
							}
						}

						if (lapis) {
							if (new Random().nextInt(lapischance) <= 1) {
								c.getBlock(xx, yy, zz).setType(Material.LAPIS_ORE);
								for (int i = lapissize; i > 0; i--) {
									int rx = new Random().nextInt(2)-1;
									int rz = new Random().nextInt(2) - 1;
									int ry = new Random().nextInt(2)-1;
									if (c.getBlock(xx+rx, yy+ry, zz+rz).getType().equals(Material.STONE) || c.getBlock(xx+rx, yy+ry, zz+rz).getType().equals(Material.COBBLESTONE)) {
										c.getBlock(xx+rx, yy+ry, zz+rz).setType(Material.LAPIS_ORE);
									}

								}
							}
						}
						if (dirt) {
							if (new Random().nextInt(dirtchance) <= 1) {
								c.getBlock(xx, yy, zz).setType(Material.DIRT);
								for (int i = dirtsize; i > 0; i--) {
									int rx = new Random().nextInt(2)-1;
									int rz = new Random().nextInt(2) - 1;
									int ry = new Random().nextInt(2)-1;
									if (c.getBlock(xx+rx, yy+ry, zz+rz).getType().equals(Material.STONE) || c.getBlock(xx+rx, yy+ry, zz+rz).getType().equals(Material.COBBLESTONE)) {
										c.getBlock(xx+rx, yy+ry, zz+rz).setType(Material.DIRT);
									}

								}
							}
						}
						if (gravel) {
							if (new Random().nextInt(gravelchance) <= 1) {
								c.getBlock(xx, yy, zz).setType(Material.GRAVEL);
								for (int i = gravelsize; i > 0; i--) {
									int rx = new Random().nextInt(2)-1;
									int rz = new Random().nextInt(2) - 1;
									int ry = new Random().nextInt(2)-1;
									if (c.getBlock(xx+rx, yy+ry, zz+rz).getType().equals(Material.STONE) || c.getBlock(xx+rx, yy+ry, zz+rz).getType().equals(Material.COBBLESTONE)) {
										c.getBlock(xx+rx, yy+ry, zz+rz).setType(Material.GRAVEL);
									}

								}
							}
						}

					}
				}
			}
		}
		
	}
    
}
