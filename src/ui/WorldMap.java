package ui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JViewport;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import util.Location;
import world.World;

public class WorldMap extends JViewport {

    private WorldMapRenderer renderer;
    private Location playerPosition;
    private World world;

    public WorldMap(WorldMapRenderer renderer) {
        super();
        add(renderer);
        this.renderer = renderer;
        this.renderer.addMouseWheelListener(new Zoom());
        this.renderer.addAncestorListener(new AncestorListener() {

            @Override
            public void ancestorAdded(AncestorEvent event) {
                if (renderer.isPaused()) {
                    renderer.resume();
                }

            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
                if (!renderer.isPaused()) {
                    renderer.pause();
                }
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }

        });
    }

    public void centerViewOnPoint(Location l) {
        centerViewOnPoint(l.getX(), l.getY());
    }

    public void centerViewOnPoint(int x, int y) {
        Point corner = renderer.getViewPoint(x, y);
        Dimension screen = getParent().getSize();
        Point centered = new Point(corner.x - screen.width / 2, corner.y - screen.height / 2);
        centerOnPoint(centered);
    }

    private void centerOnPoint(Point centered) {
        Rectangle r = new Rectangle(centered, getParent().getSize());
        renderer.revalidate();
        renderer.scrollRectToVisible(r);
    }

    public Location getPlayerPosition() {
        if (playerPosition == null) {
            playerPosition = world.getSpawn();
        }
        return playerPosition;
    }

    public void updatePlayerPosition(int move) {

    }

    public void setWorld(World world) {
        this.world = world;
        this.playerPosition = world.getSpawn();
        renderer.updateWorld(world);
    }

    private class Zoom implements MouseWheelListener {
        private final long MS_BETWEEN_LOCATION_UPDATE = 1000;
        private final double ZOOM_LEVEL_DELTA_PER_TICK = 0.1;
        private long lastUpdate = 0;
        private Location scrollLocation = new Location(0, 0);

        private void zoom(int direction) {
            double zoomLevel = renderer.getZoomLevel();
            if (zoomLevel > WorldMapRenderer.MIN_ZOOM_LEVEL || zoomLevel < WorldMapRenderer.MAX_ZOOM_LEVEL) {
                double z = direction < 0 ? zoomLevel + ZOOM_LEVEL_DELTA_PER_TICK
                    : zoomLevel - ZOOM_LEVEL_DELTA_PER_TICK;
                renderer.setZoomLevel(z);
                renderer.repaint();
                WorldMap.this.revalidate();
                // TODO Smooth the transition to scroll location by using the current center and
                // scroll in steps.
                centerViewOnPoint(scrollLocation);
            }
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (e.getWheelRotation() == 0) {
                return;
            }
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastUpdate > MS_BETWEEN_LOCATION_UPDATE) {
                scrollLocation = renderer.getMapLocation(e.getPoint());
            }
            lastUpdate = currentTime;
            zoom(e.getWheelRotation());
        }

    }

}
