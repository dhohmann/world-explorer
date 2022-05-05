package ui;
import javax.swing.JComponent;
import javax.swing.JViewport;
import javax.swing.event.MouseInputAdapter;
import java.awt.Point;
import java.awt.Cursor;

public class Mover extends MouseInputAdapter {
    private JComponent m_view            = null;
    private Point      m_holdPointOnView = null;
  
    public Mover(JComponent view) {
      m_view = view;
      m_view.addMouseListener(this);
      m_view.addMouseMotionListener(this);
    }
  
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
      m_view.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
      m_holdPointOnView = e.getPoint();
    }
  
    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
      m_view.setCursor(null);
    }
  
    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
      Point dragEventPoint = e.getPoint();
      JViewport viewport = (JViewport) m_view.getParent();
      Point viewPos = viewport.getViewPosition();
      int maxViewPosX = m_view.getWidth() - viewport.getWidth();
      int maxViewPosY = m_view.getHeight() - viewport.getHeight();
  
      if(m_view.getWidth() > viewport.getWidth()) {
        viewPos.x -= dragEventPoint.x - m_holdPointOnView.x;
  
        if(viewPos.x < 0) {
          viewPos.x = 0;
          m_holdPointOnView.x = dragEventPoint.x;
        }
  
        if(viewPos.x > maxViewPosX) {
          viewPos.x = maxViewPosX;
          m_holdPointOnView.x = dragEventPoint.x;
        }
      }
  
      if(m_view.getHeight() > viewport.getHeight()) {
        viewPos.y -= dragEventPoint.y - m_holdPointOnView.y;
  
        if(viewPos.y < 0) {
          viewPos.y = 0;
          m_holdPointOnView.y = dragEventPoint.y;
        }
  
        if(viewPos.y > maxViewPosY) {
          viewPos.y = maxViewPosY;
          m_holdPointOnView.y = dragEventPoint.y;
        }
      }
  
      viewport.setViewPosition(viewPos);
    }
  }