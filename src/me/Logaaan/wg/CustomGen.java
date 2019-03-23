package me.Logaaan.wg;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class CustomGen extends ChunkGenerator {
	    int currentHeight = 50;
	    
    	SimplexOctaveGenerator generator_float;
    	
    	private boolean i = false;
	    
	    public Main p;
	    public String wn;
	    public CustomGen(String wn, Main m) {
	    	p = m;
	    	this.wn = wn;
	    }
	    
	    private void init(World world) {
	    	generator_float = new SimplexOctaveGenerator(new Random(world.getSeed()), 7);
	        generator_float.setScale(0.0032D);
	        i = true;
	    }
	    
	    @Override
	    public List<BlockPopulator> getDefaultPopulators(World world) { 
	        return Arrays.asList((BlockPopulator)new TreePopulator(p), (BlockPopulator) new PopulatorCaves2());
	    }

	    @Override
	    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
	    	if (!i) {
	    		init(world);
	    	}
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
		                	
		                	//if (generator_float.noise(chunkX*16+X, chunkZ*16+Z, 1.4D, 0.95D) > 0) {
		                	currentHeight = (int) (generator_float.noise(chunkX*16+X, chunkZ*16+Z, 1.4D, 0.95D)*7D+70D);
			                for (int y = currentHeight; y > 70D - (generator_float.noise(chunkX*16+X, chunkZ*16+Z, 1.4D, 0.95D)*7D-13D) ; y--) {
				                if (generator_float.noise(chunkX*16+X, chunkZ*16+Z, 1.4D, 0.95D) > 0.01D) {
				                	if (y == currentHeight) {
				                		if (world.getBiome(chunkX*16+X, chunkZ*16+Z).equals(Biome.DESERT) || world.getBiome(chunkX*16+X, chunkZ*16+Z).equals(Biome.DESERT_HILLS) || world.getBiome(chunkX*16+X, chunkZ*16+Z).equals(Biome.DESERT_MOUNTAINS)) {
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
	        //}
	        return chunk;
	    }
}
