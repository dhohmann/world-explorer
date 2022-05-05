package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import util.Location;
import world.Biome;
import world.CellData;
import world.World;

public class WorldMapRenderer extends JPanel implements Renderer {

    public final int cellPixels;

    public static final double MAX_ZOOM_LEVEL = 2;
    public static final double MIN_ZOOM_LEVEL = 0.5;
    public static final int VISUAL_TICK_RATE = 3;
    private World world;
    private Mover mover;

    public int marginX = 10;
    public int marginY = 10;
    public double zoomLevel = 1.0f;

    public WorldMapRenderer(int pixelsCell) {
        this.mover = new Mover(this);
        this.cellPixels = pixelsCell;

        setBackground(Color.WHITE);
    }

    public WorldMapRenderer() {
        this(32);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (world == null) {
            return;
        }

        for (CellData tile : world) {
            renderTile(tile, g);
        }
        waterState = ((waterState + 1) % 7);
    }

    // For simple water animation
    private int waterState = 0;

    private void renderTile(CellData tile, Graphics g) {
        if (tile != null) {
            int x = tile.getLocation().getX() + marginX;
            int y = tile.getLocation().getY() + marginY;
            g.setColor(tile.getBiome().getColor());
            g.fillRect((int) Math.ceil(x * cellPixels * zoomLevel), (int) Math.ceil(y * cellPixels * zoomLevel),
                (int) Math.ceil(cellPixels * zoomLevel), (int) Math.ceil(cellPixels * zoomLevel));

            renderWaterAnimation(tile, g);
            renderPlayer(tile, g);
        }
    }

    private Color waterAccent = new Color(52, 140, 235);
    private Color waterColor = Color.BLUE;

    private void renderWaterAnimation(CellData tile, Graphics g) {
        if (tile.getBiome() != Biome.OCEAN) {
            return;
        }
        int x = tile.getLocation().getX() + marginX;
        int y = tile.getLocation().getY() + marginY;
        int px, py, radius;

        switch (waterState) {
        case 6:
            radius = (int) (cellPixels * zoomLevel / 8);
            px = (int) Math.round(x * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            py = (int) Math.round(y * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            g.setColor(waterAccent);
            g.fillOval(px, py, radius * 2, radius * 2);

            radius = (int) (cellPixels * zoomLevel / 8.5);
            px = (int) Math.round(x * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            py = (int) Math.round(y * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            g.setColor(waterColor);
            g.fillOval(px, py, radius * 2, radius * 2);
            break;
        case 5:
            radius = (int) (cellPixels * zoomLevel / 3);
            px = (int) Math.round(x * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            py = (int) Math.round(y * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            g.setColor(waterAccent);
            g.fillOval(px, py, radius * 2, radius * 2);

            radius = (int) (cellPixels * zoomLevel / 3.5);
            px = (int) Math.round(x * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            py = (int) Math.round(y * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            g.setColor(waterColor);
            g.fillOval(px, py, radius * 2, radius * 2);
            break;
        case 4:
            radius = (int) (cellPixels * zoomLevel / 3);
            px = (int) Math.round(x * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            py = (int) Math.round(y * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            g.setColor(waterAccent);
            g.fillOval(px, py, radius * 2, radius * 2);

            radius = (int) (cellPixels * zoomLevel / 3.5);
            px = (int) Math.round(x * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            py = (int) Math.round(y * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            g.setColor(waterColor);
            g.fillOval(px, py, radius * 2, radius * 2);
            break;
        case 3:
            radius = (int) (cellPixels * zoomLevel / 2.25);
            px = (int) Math.round(x * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            py = (int) Math.round(y * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            g.setColor(waterAccent);
            g.fillOval(px, py, radius * 2, radius * 2);

            radius = (int) (cellPixels * zoomLevel / 2.3);
            px = (int) Math.round(x * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            py = (int) Math.round(y * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            g.setColor(waterColor);
            g.fillOval(px, py, radius * 2, radius * 2);
            break;
        case 2:
            radius = (int) (cellPixels * zoomLevel / 3);
            px = (int) Math.round(x * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            py = (int) Math.round(y * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            g.setColor(waterAccent);
            g.fillOval(px, py, radius * 2, radius * 2);

            radius = (int) (cellPixels * zoomLevel / 3.5);
            px = (int) Math.round(x * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            py = (int) Math.round(y * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            g.setColor(waterColor);
            g.fillOval(px, py, radius * 2, radius * 2);
            break;
        case 1:
            radius = (int) (cellPixels * zoomLevel / 3);
            px = (int) Math.round(x * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            py = (int) Math.round(y * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            g.setColor(waterAccent);
            g.fillOval(px, py, radius * 2, radius * 2);

            radius = (int) (cellPixels * zoomLevel / 3.5);
            px = (int) Math.round(x * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            py = (int) Math.round(y * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            g.setColor(waterColor);
            g.fillOval(px, py, radius * 2, radius * 2);

            break;
        case 0:
            radius = (int) (cellPixels * zoomLevel / 5);
            px = (int) Math.round(x * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            py = (int) Math.round(y * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            g.setColor(waterAccent);
            g.fillOval(px, py, radius * 2, radius * 2);

            radius = (int) (cellPixels * zoomLevel / 5.5);
            px = (int) Math.round(x * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            py = (int) Math.round(y * cellPixels * zoomLevel + (cellPixels * zoomLevel) /
                2 - radius);
            g.setColor(waterColor);
            g.fillOval(px, py, radius * 2, radius * 2);
        }
    }

    private void renderPlayer(CellData tile, Graphics g) {
        if (tile != null && tile.containsPlayer) {
            int x = tile.getLocation().getX() + marginX;
            int y = tile.getLocation().getY() + marginY;
            g.setColor(Color.RED);
            g.fillOval((int) Math.ceil(x * cellPixels * zoomLevel + 2), (int) Math.ceil(y * cellPixels * zoomLevel + 2),
                (int) Math.ceil(cellPixels * zoomLevel) - 4, (int) Math.ceil(cellPixels * zoomLevel) - 4);
        }
    }

    public void updateWorld(World world) {
        this.world = world;
        //this.zoomLevel = 1.0;
        updateDimension();
        this.repaint();
    }

    public void updateDimension() {
        int x = (int) Math.ceil((world.getSizeX() + (2 * marginX)) * cellPixels * zoomLevel);
        int y = (int) Math.ceil((world.getSizeY() + (2 * marginY)) * cellPixels * zoomLevel);
        this.setPreferredSize(new Dimension(x, y));
        this.getParent().setPreferredSize(new Dimension(x, y));
    }

    public Mover getMover() {
        return mover;
    }

    public Location getMapLocation(Point p) {
        int x = (int) Math.floor(p.getX() / cellPixels / zoomLevel) - marginX;
        int y = (int) Math.floor(p.getY() / cellPixels / zoomLevel) - marginY;
        return new Location(x, y);
    }

    public Point getViewPoint(int x, int y) {
        int pX = (int) Math.floor((x + marginX) * cellPixels * zoomLevel);
        int pY = (int) Math.floor((y + marginY) * cellPixels * zoomLevel);
        return new Point(pX, pY);
    }

    public void setZoomLevel(double level) {
        if (level >= MIN_ZOOM_LEVEL && level <= MAX_ZOOM_LEVEL) {
            zoomLevel = level;
            updateDimension();
        }
    }

    public double getZoomLevel() {
        return zoomLevel;
    }

    private Timer timer = null;

    @Override
    public boolean isRendering() {
        return timer != null && timer.isRunning();
    }

    @Override
    public boolean isPaused() {
        return timer == null || !timer.isRunning();
    }

    @Override
    public void resume() {
        if (timer == null) {
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    WorldMapRenderer.this.repaint();
                }
            };
            timer = new Timer(100, taskPerformer);
            timer.setRepeats(true);
        }
        timer.start();

    }

    @Override
    public void pause() {
        if (timer != null) {
            timer.stop();
        }
    }

}
