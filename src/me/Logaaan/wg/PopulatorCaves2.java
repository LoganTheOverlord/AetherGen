package me.Logaaan.wg;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

/**
 * author: Angelic47
 * Nukkit Project
 */
public class PopulatorCaves2 extends BlockPopulator {

    protected int checkAreaSize = 8;

    private Random random;
    
    Main p;

    public static int caveRarity = 4;//7
    public static int caveFrequency = 40;//40
    public static int caveMinAltitude = 60;
    public static int caveMaxAltitude = 200;
    public static int individualCaveRarity = 25;//25
    public static int caveSystemFrequency = 1;
    public static int caveSystemPocketChance = 1;
    public static int caveSystemPocketMinSize = 1;
    public static int caveSystemPocketMaxSize = 4;
    public static boolean evenCaveDistribution = false;
    public static double radiusMultiplier = 1;
    
    public PopulatorCaves2(Main p) {
    	this.p = p;
    	this.caveRarity = p.cave_rarity;
    	this.caveFrequency = p.cave_frequency;
    	this.caveMinAltitude = p.cave_min;
    	this.caveMaxAltitude = p.cave_max;
    	this.caveSystemPocketChance = p.cave_pocket_chance;
    	this.individualCaveRarity = p.cave_individual_rarity;
    	this.radiusMultiplier = p.cave_radius;
    }

    public int worldHeightCap = 240;

    @Override
    public void populate(World level, Random random, Chunk chunk) {
        this.random = new Random();
        this.random.setSeed(level.getSeed());
        long worldLong1 = this.random.nextLong();
        long worldLong2 = this.random.nextLong();

        int size = this.checkAreaSize;
        int chunkX = chunk.getX();
        int chunkZ = chunk.getZ();
        for (int x = chunkX - size; x <= chunkX + size; x++)
            for (int z = chunkZ - size; z <= chunkZ + size; z++) {
                long randomX = x * worldLong1;
                long randomZ = z * worldLong2;
                this.random.setSeed(randomX ^ randomZ ^ level.getSeed());
                generateChunk(x, z, chunk);
            }
    }

    protected void generateLargeCaveNode(long seed, Chunk chunk, double x, double y, double z) {
        generateCaveNode(seed, chunk, x, y, z, 1.0F + this.random.nextFloat() * 6.0F * (float) radiusMultiplier, 0.0F, 0.0F, -1, -1, 0.5D);
    }

    protected void generateCaveNode(long seed, Chunk chunk, double x, double y, double z, float radius, float angelOffset, float angel, int angle, int maxAngle, double scale) {
        int chunkX = chunk.getX();
        int chunkZ = chunk.getZ();
        
        radius = (float) (radius * radiusMultiplier);

        double realX = chunkX * 16 + 8;
        double realZ = chunkZ * 16 + 8;

        float f1 = 0.0F;
        float f2 = 0.0F;

        Random localRandom = new Random(seed);

        if (maxAngle <= 0) {
            int checkAreaSize = this.checkAreaSize * 16 - 16;
            maxAngle = checkAreaSize - localRandom.nextInt(checkAreaSize / 4);
        }
        boolean isLargeCave = false;

        if (angle == -1) {
            angle = maxAngle / 2;
            isLargeCave = true;
        }

        int randomAngel = localRandom.nextInt(maxAngle / 2) + maxAngle / 4;
        boolean bigAngel = localRandom.nextInt(6) == 0;

        for (; angle < maxAngle; angle++) {
            double offsetXZ = 1.5D + Math.sin(angle * 3.141593F / maxAngle) * radius * 1.0F;
            double offsetY = offsetXZ * scale;

            float cos = (float) Math.cos(angel);
            float sin = (float) Math.sin(angel);
            x += Math.cos(angelOffset) * cos;
            y += sin;
            z += Math.sin(angelOffset) * cos;

            if (bigAngel)
                angel *= 0.92F;
            else {
                angel *= 0.7F;
            }
            angel += f2 * 0.1F;
            angelOffset += f1 * 0.1F;

            f2 *= 0.9F;
            f1 *= 0.75F;
            f2 += (localRandom.nextFloat() - localRandom.nextFloat()) * localRandom.nextFloat() * 2.0F;
            f1 += (localRandom.nextFloat() - localRandom.nextFloat()) * localRandom.nextFloat() * 4.0F;

            if ((!isLargeCave) && (angle == randomAngel) && (radius > 1.0F) && (maxAngle > 0)) {
                generateCaveNode(localRandom.nextLong(), chunk, x, y, z, localRandom.nextFloat() * 0.5F + 0.5F, angelOffset - 1.570796F, angel / 3.0F, angle, maxAngle, 1.0D);
                generateCaveNode(localRandom.nextLong(), chunk, x, y, z, localRandom.nextFloat() * 0.5F + 0.5F, angelOffset + 1.570796F, angel / 3.0F, angle, maxAngle, 1.0D);
                return;
            }
            if ((!isLargeCave) && (localRandom.nextInt(4) == 0)) {
                continue;
            }

            // Check if distance to working point (x and z) too larger than working radius (maybe ??)
            double distanceX = x - realX;
            double distanceZ = z - realZ;
            double angelDiff = maxAngle - angle;
            double newRadius = radius + 2.0F + 16.0F;
            if (distanceX * distanceX + distanceZ * distanceZ - angelDiff * angelDiff > newRadius * newRadius) {
                return;
            }

            //Boundaries check.
            if ((x < realX - 16.0D - offsetXZ * 2.0D) || (z < realZ - 16.0D - offsetXZ * 2.0D) || (x > realX + 16.0D + offsetXZ * 2.0D) || (z > realZ + 16.0D + offsetXZ * 2.0D))
                continue;


            int xFrom = (int) (Math.floor(x - offsetXZ) - chunkX * 16 - 1);
            int xTo = (int) (Math.floor(x + offsetXZ) - chunkX * 16 + 1);

            int yFrom = (int) (Math.floor(y - offsetY) - 1);
            int yTo = (int) (Math.floor(y + offsetY) + 1);

            int zFrom = (int) (Math.floor(z - offsetXZ) - chunkZ * 16 - 1);
            int zTo = (int) (Math.floor(z + offsetXZ) - chunkZ * 16 + 1);

            if (xFrom < 0)
                xFrom = 0;
            if (xTo > 16)
                xTo = 16;

            if (yFrom < 1)
                yFrom = 1;
            if (yTo > this.worldHeightCap - 8) {
                yTo = this.worldHeightCap - 8;
            }
            if (zFrom < 0)
                zFrom = 0;
            if (zTo > 16)
                zTo = 16;

            // Search for water
            boolean waterFound = false;
            for (int xx = xFrom; (!waterFound) && (xx < xTo); xx++) {
                for (int zz = zFrom; (!waterFound) && (zz < zTo); zz++) {
                    for (int yy = yTo + 1; (!waterFound) && (yy >= yFrom - 1); yy--) {
                        if (yy >= 0 && yy < this.worldHeightCap) {
                            int block = chunk.getBlock(xx, yy, zz).getTypeId();
                            if (block == Material.WATER.getId() || block == Material.STATIONARY_WATER.getId()) {
                                waterFound = true;
                            }
                            if ((yy != yFrom - 1) && (xx != xFrom) && (xx != xTo - 1) && (zz != zFrom) && (zz != zTo - 1))
                                yy = yFrom;
                        }
                    }
                }
            }

            if (waterFound) {
                continue;
            }

            // Generate cave
            for (int xx = xFrom; xx < xTo; xx++) {
                double modX = (xx + chunkX * 16 + 0.5D - x) / offsetXZ;
                for (int zz = zFrom; zz < zTo; zz++) {
                    double modZ = (zz + chunkZ * 16 + 0.5D - z) / offsetXZ;

                    boolean grassFound = false;
                    if (modX * modX + modZ * modZ < 1.0D) {
                        for (int yy = yTo; yy > yFrom; yy--) {
                            double modY = ((yy - 1) + 0.5D - y) / offsetY;
                            if ((modY > -0.7D) && (modX * modX + modY * modY + modZ * modZ < 1.0D)) {

                                int material = chunk.getBlock(xx, yy, zz).getTypeId();;
                                int materialAbove = chunk.getBlock(xx, yy + 1, zz).getTypeId();
                                if (material == Material.GRASS.getId() || material == Material.MYCEL.getId()) {
                                    grassFound = true;
                                }
                                //TODO: check this
//								if (this.isSuitableBlock(material, materialAbove, biome))
                                {
                                    if (yy - 1 < 10) {
                                    	chunk.getBlock(xx, yy , zz).setType(Material.AIR);
                                    } else {
                                    	chunk.getBlock(xx, yy, zz).setType(Material.AIR);

                                        // If grass was just deleted, try to
                                        // move it down
                                        if (grassFound && (chunk.getBlock(xx, yy - 1, zz).getType() == Material.DIRT)) {
                                            chunk.getBlock(xx, yy - 1, zz).setType(Material.GRASS);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (isLargeCave) {
                break;
            }
        }
    }

    protected void generateChunk(int chunkX, int chunkZ, Chunk generatingChunkBuffer) {
        int i = this.random.nextInt(this.random.nextInt(this.random.nextInt(caveFrequency) + 1) + 1);
        if (evenCaveDistribution)
            i = caveFrequency;
        if (this.random.nextInt(100) >= caveRarity)
            i = 0;

        for (int j = 0; j < i; j++) {
            double x = chunkX * 16 + this.random.nextInt(16);

            double y;

            if (evenCaveDistribution)
                y = numberInRange(random, caveMinAltitude, caveMaxAltitude);
            else
                y = this.random.nextInt(this.random.nextInt(caveMaxAltitude - caveMinAltitude + 1) + 1) + caveMinAltitude;

            double z = chunkZ * 16 + this.random.nextInt(16);

            int count = caveSystemFrequency;
            boolean largeCaveSpawned = false;
            if (this.random.nextInt(100) <= individualCaveRarity) {
                generateLargeCaveNode(this.random.nextLong(), generatingChunkBuffer, x, y, z);
                largeCaveSpawned = true;
            }

            if ((largeCaveSpawned) || (this.random.nextInt(100) <= caveSystemPocketChance - 1)) {
                count += numberInRange(random, caveSystemPocketMinSize, caveSystemPocketMaxSize);
            }
            while (count > 0) {
                count--;
                float f1 = this.random.nextFloat() * 3.141593F * 2.0F;
                float f2 = (this.random.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float f3 = this.random.nextFloat() * 2.0F + this.random.nextFloat();

                generateCaveNode(this.random.nextLong(), generatingChunkBuffer, x, y, z, f3, f1, f2, 0, 0, 1.0D);
            }
        }
    }

    public static int numberInRange(Random random, int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
}

