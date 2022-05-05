package util;

import world.World;

public class Location {

	private int x;
	private int y;
	private World world;

	public Location(int x, int y) {
		this(x, y, null);
	}

	public Location(int x, int y, World world) {
		this.x = x;
		this.y = y;
		this.world = world;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public World getWorld() {
		return world;
	}

	@Override
	public String toString() {
		return "Location (x=" + x + ", y=" + y + ")";
	}
}
