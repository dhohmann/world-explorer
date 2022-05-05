package world;

import java.util.Iterator;

import util.Location;

public class World implements Iterable<CellData> {

	private final CellData[] world;
	private final int dimensionX;
	private final int dimensionY;
	private Location spawn;

	public World(int size) {
		this(size, size);
	}

	public World(int sizeX, int sizeY) {
		world = new CellData[sizeX * sizeY];
		dimensionX = sizeX;
		dimensionY = sizeY;
		for (int i = 0; i < world.length; i++) {
			world[i] = new CellData(i / sizeX, i % sizeX, this);
		}
	}

	public int getSizeX() {
		return dimensionX;
	}

	public int getSizeY() {
		return dimensionY;
	}

	@Override
	public Iterator<CellData> iterator() {
		return new Iterator<CellData>() {

			int i = 0;

			@Override
			public boolean hasNext() {
				return i < world.length;
			}

			@Override
			public CellData next() {
				return world[i++];
			}
		};
	}

	public CellData getCell(int x, int y) {
		int index = x * dimensionX + y;
		if (index < 0 || index >= world.length) {
			return null;
		}
		return world[index];
	}

	public Location getSpawn() {
		if (spawn == null) {
			do {
				int x = (int) (Math.random() * dimensionX);
				int y = (int) (Math.random() * dimensionY);
				if (getCell(x, y).getBiome() == Biome.VOID) {
					return null;
				}
				spawn = new Location(x, y);
			} while (getCell(spawn.getX(), spawn.getY()).getBiome() != Biome.GRASSLAND);
		}
		return spawn;
	}

}
