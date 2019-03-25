package me.Logaaan.wg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.Vector;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class CustomGen extends ChunkGenerator {
	    int currentHeight = 50;
	    
    	SimplexOctaveGenerator generator_float;
    	SimplexOctaveGenerator generator_float_2;
    	private boolean i = false;
	    public Main p;
	    public String wn;
	    public CustomGen(String wn, Main m) {
	    	p = m;
	    	this.wn = wn;
	    }
	    
	    private void init(World world) {
	    	generator_float = new SimplexOctaveGenerator(new Random(world.getSeed()), p.ao);
	        generator_float.setScale(p.sa);
	        
	    	generator_float_2 = new SimplexOctaveGenerator(new Random(world.getSeed()), p.ao2);
	        generator_float_2.setScale(p.sa2);
	        i = true;
	    }
	    
	    @Override
	    public List<BlockPopulator> getDefaultPopulators(World world) { 
	        return Arrays.asList((BlockPopulator)new TreePopulator(p), (BlockPopulator) new PopulatorCaves2(p), new PopulatorOre(p));
	    }

	    @Override
	    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
	    	if (!i) {
	    		init(world);
	    	}
	    	
	    	int baseX = chunkX >> 4;
	    	int baseZ = chunkZ >> 4;
	        ChunkData chunk = createChunkData(world);
	        double bm = 1;
	        Material coverBlock = Material.GRASS;
	        if (world.getEnvironment().equals(Environment.NORMAL)) {
	        //if (chunkX == 0) {
	        for (int X = 0; X < 16; X++) {
		        	for (int Z = 0; Z < 16; Z++) {
		        		int rx = chunkX * 16 + X;
		        		int rz = chunkZ * 16 + Z;
		        		int cr = 0;
		                
		                Biome b = world.getBiome(rx,rz);
		                	
		                	final int xl = X;
		                	final int xz = Z;
		                	//if (generator_float.noise(chunkX*16+X, chunkZ*16+Z, 1.4D, 0.95D) > 0) {
		                
				                	currentHeight = (int) (generator_float.noise(chunkX*16+xl | chunkZ*8+chunkX*8+X | baseX + X, chunkZ*16+xz | chunkZ*8+chunkX*8+Z | baseZ + Z, p.f, p.a)*7D+p.ah);
					                for (int y = currentHeight; y > p.ah - (generator_float.noise(chunkX*16+xl | chunkZ*8+chunkX*8+X | baseX + X, chunkZ*16+xz | chunkZ*8+chunkX*8+Z | baseZ + Z, p.f, p.a)*7D-13D) ; y--) {
						                if (generator_float.noise(chunkX*16+xl | chunkZ*8+chunkX*8+X | baseX + X, chunkZ*16+xz | chunkZ*8+chunkX*8+Z | baseZ + Z, p.f, p.a) > p.sp) {
						                		if (y == currentHeight) {
							                		if (world.getBiome(chunkX*16+xl, chunkZ*16+xz).equals(Biome.DESERT) || world.getBiome(chunkX*16+xl, chunkZ*16+xz).equals(Biome.DESERT_HILLS)) {
							                			chunk.setBlock(X, y, Z, Material.SAND);
							                		} else {
							                			chunk.setBlock(X, y, Z, Material.GRASS);

							                		}
							                	} else {
							                		if (y == currentHeight - 1) {
							                			chunk.setBlock(X, y, Z, Material.DIRT);

							                			
							                		} else {
							                			chunk.setBlock(X, y, Z, new Random().nextBoolean() ? Material.COBBLESTONE : Material.STONE);

							                		}
							                	}

						                	
						                }
					                }
		                		
		                	
			                	
		       
			                
		        			if (p.aether2 == true) {
		        				currentHeight = (int) (generator_float_2.noise(chunkX*16+X, chunkZ*16+Z, p.f2, p.a2)*7D+p.ah2);
				                for (int y = currentHeight; y > p.ah2 - (generator_float_2.noise(chunkX*16+X, chunkZ*16+Z, p.f2, p.a2)*7D-13D) ; y--) {
					                if (generator_float_2.noise(chunkX*16+X, chunkZ*16+Z, p.f2, p.a2) > p.sp2) {
					                	if (y == currentHeight) {
					                		if (world.getBiome(chunkX*16+X, chunkZ*16+Z).equals(Biome.DESERT) || world.getBiome(chunkX*16+X, chunkZ*16+Z).equals(Biome.DESERT_HILLS)) {
					                			chunk.setBlock(X, y, Z, Material.SAND);
					                		} else {
					                			chunk.setBlock(X, y, Z, Material.GRASS);
					                		}
					                	} else {
					                		if (y == currentHeight - 1) {
					                			chunk.setBlock(X, y, Z, Material.DIRT);
					                			
					                		} else {
					                			chunk.setBlock(X,y,Z, new Random().nextBoolean() ? Material.COBBLESTONE : Material.STONE);
					                		}
					                	}
					                }
				                }		
		        			}
				        }
				        }
	        }
		            
		      //  }
	        
	        
	        if (world.getEnvironment().equals(Environment.NETHER)) {
	        if (chunkX % 1 == 0 && chunkZ % 1 == 0) {
	        for (int X = 0; X < 16; X++) {
		        	for (int Z = 0; Z < 16; Z++) {
		                currentHeight = (int) (generator_float.noise(chunkX*16+X, chunkZ*16+Z, 1.4D, 0.95D)*7D+50D);
		                chunk.setBlock(X, currentHeight, Z, Material.NETHERRACK);
		                chunk.setBlock(X, currentHeight-1, Z, Material.NETHERRACK);
		                for (int i = currentHeight-2; i > 0; i--)
		                	chunk.setBlock(X, i, Z, new Random().nextBoolean() ? Material.NETHERRACK : new Random().nextBoolean() ? Material.SOUL_SAND : Material.GRAVEL);
		                chunk.setBlock(X, 0, Z, Material.BEDROCK);
		            }
		        }
	        }
	        
  	        }
	        return chunk;
	    }
}
