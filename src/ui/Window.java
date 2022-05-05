package ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import util.Location;
import world.Generators;
import world.World;

public class Window {

	private static WorldMap map;

	public static Location playerPosition = null;

	private static void generateWorld(int sizeX, int sizeY) {
		World w = new World(sizeX, sizeY);
		Generators.natural(w);

		map.setWorld(w);
		map.revalidate();
		SwingUtilities.invokeLater(() -> {
			map.centerViewOnPoint(w.getSpawn());
		});
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		map = new WorldMap(new WorldMapRenderer());

		JButton randomMap = new JButton("Random Map");
		JButton centerOnPlayer = new JButton("Center on player");
		randomMap.addActionListener((e) -> generateWorld(100, 100));
		centerOnPlayer.addActionListener((e) -> map.centerViewOnPoint(map.getPlayerPosition()));

		JPanel actions = new JPanel();
		actions.add(randomMap);
		actions.add(centerOnPlayer);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLayout(new BorderLayout());

		JLayeredPane main = new JLayeredPane();
		frame.add(map, BorderLayout.CENTER);
		frame.add(actions, BorderLayout.SOUTH);

		frame.setVisible(true);

		generateWorld(100, 100);
	}

}
