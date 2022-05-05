package world;

import java.util.HashSet;
import java.util.Set;

import util.Location;

public class CellData {

	public boolean containsPlayer = false;
	private Biome biome = Biome.VOID;
	private Location location;

	public CellData(int x, int y) {
		this.location = new Location(x, y);
	}

	public CellData(int x, int y, World world) {
		this.location = new Location(x, y, world);
	}

	public void setBiom(Biome biom) {
		this.biome = biom;
	}

	public Biome getBiome() {
		return biome;
	}

	public Location getLocation() {
		return location;
	}

	/**
	 * Retrieves all cells that have a distance of one to this cell.
	 * 
	 * @return
	 */
	public Set<CellData> getNeighbors() {
		Set<CellData> neighbors = new HashSet<>();
		int x = location.getX(), y = location.getY();
		CellData c = location.getWorld().getCell(x + 1, y);
		if (c != null) {
			neighbors.add(c);
		}
		c = location.getWorld().getCell(x - 1, y);
		if (c != null) {
			neighbors.add(c);
		}
		c = location.getWorld().getCell(x, y + 1);
		if (c != null) {
			neighbors.add(c);
		}
		c = location.getWorld().getCell(x, y - 1);
		if (c != null) {
			neighbors.add(c);
		}
		return neighbors;
	}

}
