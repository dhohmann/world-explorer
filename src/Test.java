import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Test {

    public static void main(String[] args) {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.add(new JLabel("text"), 1);
        layeredPane.setPreferredSize(new Dimension(300, 310));

        JFrame frame = new JFrame();
        frame.setSize(new Dimension(500, 500));
        frame.add(layeredPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Object p = frame.getContentPane();
        if (p instanceof JLayeredPane) {
            System.out.println("layered");
        }
    }
}
