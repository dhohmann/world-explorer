package world;
import java.util.HashSet;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import util.Location;

public class Generators {

	private static Biome[] bioms = Biome.values();

	public static void natural(World world) {
		int x = (int) (Math.random() * world.getSizeX());
		int y = (int) (Math.random() * world.getSizeY());

		boolean tilesAreVoid = true;
		Queue<Location> queue = new LinkedBlockingQueue<>();
		queue.add(new Location(x, y));
		do {
			if (queue.isEmpty()) {
				for (CellData cellData : world) {
					if(cellData.getBiome() == Biome.VOID){
						queue.add(cellData.getLocation());
					}
				}

				if (queue.isEmpty()) {
					tilesAreVoid = false;
				}
			}
			if (tilesAreVoid) {
				if (queue.size() > 1) {
					Location l = queue.poll();
					queue.clear();
					queue.add(l);
				}
				Biome biome = bioms[(int) (Math.random() * (bioms.length - 1))];
				buildBiome(biome, world, queue, new HashSet<>());
			}
		} while (tilesAreVoid);

		Location spawn = world.getSpawn();
		if(spawn != null){
			world.getCell(spawn.getX(), spawn.getY()).containsPlayer = true;
		}
	}

	private static void buildBiome(Biome biome, World world, Queue<Location> toFill, Set<Location> biomeTiles) {
		if (biomeTiles.size() >= biome.getMinSize() && Math.random() > 0.3) {
			return;
		}
		if (biomeTiles.size() >= biome.getMaxSize()) {
			return;
		}
		if (toFill.isEmpty()) {
			return;
		}
		Location location = toFill.poll();
		CellData tile = world.getCell(location.getX(), location.getY());
		if (tile.getBiome() == Biome.VOID) {
			Set<Location> remainingNeighbors = new HashSet<>();
			tile.getNeighbors().stream().filter(c -> c.getBiome() == Biome.VOID).forEach(c -> remainingNeighbors.add(c
				.getLocation()));

			if (remainingNeighbors.size() > 1) {
				tile.setBiom(biome);
				biomeTiles.add(location);
			} else {
				Set<CellData> neighbors = tile.getNeighbors();
				CellData neighborToAppendTo = neighbors.stream().skip(new Random().nextInt(neighbors.size()))
					.findFirst().orElse(null);
				if (neighborToAppendTo == null) {
					neighborToAppendTo = neighbors.iterator().next();
				}
				tile.setBiom(neighborToAppendTo.getBiome());
			}
			toFill.addAll(remainingNeighbors);
		}
		buildBiome(biome, world, toFill, biomeTiles);
	}
}
