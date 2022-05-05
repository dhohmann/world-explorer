package world;
import java.awt.Color;

public enum Biome {

	/**
	 * 
	 */
	OCEAN(Color.BLUE, 80, Integer.MAX_VALUE),
	/**
	 * 
	 */
	WOODS(new Color(0x1e5e2f), 10, 15),
	/**
	 * 
	 */
	GRASSLAND(Color.GREEN, 50, 55),
	/**
	 * 
	 */
	MOUNTAINS(Color.GRAY, 10, 20),

	/**
	 * 
	 */
	DESERT(Color.YELLOW, 10, 20),
	/**
	 * 
	 */
	VOID(Color.BLACK);

	private Color color;
	private int minSize;
	private int maxSize;

	Biome(Color color) {
		this.color = color;
		this.minSize = 5;
		this.maxSize = 8;
	}

	Biome(Color color, int minSize, int maxSize) {
		this.color = color;
		this.minSize = minSize;
		this.maxSize = maxSize;
	}

	public Color getColor() {
		return color;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public int getMinSize() {
		return minSize;
	}

}
