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
	    
	    public Main p;
	    public CustomGen(Main m) {
	    	p = m;
	    }
	    
	    @Override
	    public List<BlockPopulator> getDefaultPopulators(World world) {
	        return Arrays.asList((BlockPopulator)new TreePopulator(p));
	    }

	    @Override
	    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
	    	SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 9);
	    	SimplexOctaveGenerator generator_plains = new SimplexOctaveGenerator(new Random(world.getSeed()), 6);
	    	SimplexOctaveGenerator generator_extreme = new SimplexOctaveGenerator(new Random(world.getSeed()), 10);
	    	SimplexOctaveGenerator generator_svn = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
	    	SimplexOctaveGenerator generator_jh = new SimplexOctaveGenerator(new Random(world.getSeed()), 13);
	    	SimplexOctaveGenerator generator_float = new SimplexOctaveGenerator(new Random(world.getSeed()), 7);
	        ChunkData chunk = createChunkData(world);
	        generator.setScale(0.0042D);
	        generator_plains.setScale(0.0042D);
	        generator_extreme.setScale(0.0042D);
	        generator_svn.setScale(0.0042D);
	        generator_jh.setScale(0.0042D);
	        generator_float.setScale(0.0042D);
	        

	        if (world.getEnvironment().equals(Environment.NORMAL)) {
	        if (chunkX % 1 == 0 && chunkZ % 1 == 0) {
	        //if (chunkX == 0) {
	        for (int X = 0; X < 16; X++) {
		        	for (int Z = 0; Z < 16; Z++) {
		        		int rx = chunkX * 16 + X;
		        		int rz = chunkZ * 16 + Z;
		        		int cr = 0;
		                
		                
		                if (p.aether == true) {
		                	if (generator_float.noise(chunkX*16+X, chunkZ*16+Z, 1.4D, 0.95D) > 0.53D) {
		                		if (world.getBiome(rx, rz).equals(Biome.PLAINS)) {
				        			currentHeight = (int) (generator_plains.noise(chunkX*16+X, chunkZ*16+Z, 1.4D, 0.95D)*7D+p.ah);
				        			cr = currentHeight;
				        		} else {
					        		if (world.getBiome(rx, rz).equals(Biome.EXTREME_HILLS) || world.getBiome(rx, rz).equals(Biome.EXTREME_HILLS_MOUNTAINS)) {
					        			currentHeight = (int) (generator_extreme.noise(chunkX*16+X, chunkZ*16+Z, 1.4D, 0.95D)*7.1D+p.ah+14D);
					        			cr = currentHeight;
					        		} else {
					        			if (world.getBiome(rx, rz).equals(Biome.JUNGLE_HILLS) || world.getBiome(rx, rz).equals(Biome.JUNGLE_MOUNTAINS)) {
					        				currentHeight = (int) (generator_jh.noise(chunkX*16+X, chunkZ*16+Z, 1.4D, 0.95D)*7.3D+p.ah+13D);
					        				cr = currentHeight;
						        		} else {
						        			if (world.getBiome(rx, rz).equals(Biome.SAVANNA_MOUNTAINS) || world.getBiome(rx, rz).equals(Biome.SAVANNA_PLATEAU_MOUNTAINS)) {
						        				currentHeight = (int) (generator_svn.noise(chunkX*16+X, chunkZ*16+Z, 1.4D, 0.95D)*7.24D+p.ah+12D);
						        				cr = currentHeight;
							        		} else {
						        			
							        			currentHeight = (int) (generator.noise(chunkX*16+X, chunkZ*16+Z, 1.4D, 0.95D)*7D+p.ah);
							        			cr = currentHeight;
						        			
							        		}
						        		}
					        		}
				        		
				        		
				                
				        		}

				                for (int y = currentHeight; y > p.ah - (generator_float.noise(chunkX*16+X, chunkZ*16+Z, 1.4D, 0.95D)*7D-15D) ; y--) {
					                
					                	if (y == currentHeight) {
					                		chunk.setBlock(X, y, Z, Material.GRASS);
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
	        
	        if (world.getEnvironment().equals(Environment.NETHER)) {
	        if (chunkX % 1 == 0 && chunkZ % 1 == 0) {
	        for (int X = 0; X < 16; X++) {
		        	for (int Z = 0; Z < 16; Z++) {
		                currentHeight = (int) (generator.noise(chunkX*16+X, chunkZ*16+Z, 1.4D, 0.95D)*7D+50D);
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
