import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

class Tree {

    public static final int depth = 10;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1200, 800));

        DrawPanel dp = new DrawPanel();
        dp.drawThread.start();
        dp.recurTree(600, 600, 50, 150, Math.toRadians(90), depth);
        dp.recurTree(300, 600, 60, 180, Math.toRadians(90), depth);
        dp.recurTree(700, 600, 60, 180, Math.toRadians(90), depth);

        frame.add(dp);
        frame.setVisible(true);
    }

}