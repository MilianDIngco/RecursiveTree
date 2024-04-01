import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements Runnable {

    static final int numBranches = 5;
    int rc = 100;
    int gc = 100;
    int bc = 100;
    int inc = 50;
    double widthMod = 2;
    double angleAllowance = Math.PI;
    double heightRatio = .7;
    int uglyWidth = 2;

    public Thread drawThread;
    ArrayList<Triangle> triangles;
    static final int FPS = 60;

    public DrawPanel() {
        drawThread = new Thread(this);
        this.setLayout(null);
        this.setDoubleBuffered(true);
        this.setBackground(Color.WHITE);
        triangles = new ArrayList<>();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (drawThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                update();

                repaint();

                delta--;
            }

        }
    }

    public void update() {

    }

    public void recurTree(int x, int y, int w, int h, double t, int depth) {
        if (depth == 0 || w < uglyWidth) {
            return;
        }
        drawTri(x, y, w, h, t, depth);
        for (int i = 0; i < numBranches; i++) {
            int wi = (int) (w * widthMod / numBranches);
            int hi = (int) (h * 0.5);
            double ti = angleAllowance * Math.random();
            int xi = (int) (-(h * heightRatio) * Math.cos(t) + x);
            int yi = (int) (-(h * heightRatio) * Math.sin(t) + y);
            recurTree(xi, yi, wi, hi, ti, depth - 1);
        }
    }

    public void drawTri(int x, int y, int w, int h, double t, int depth) {
        int x1 = (int) (w / 2 * Math.cos(t + Math.PI / 2) + x);
        int x2 = (int) (w / 2 * Math.cos(t - Math.PI / 2) + x);
        int x3 = (int) (-h * Math.cos(t) + x);

        int y1 = (int) ((w / 2) * Math.sin(t + Math.PI / 2) + y);
        int y2 = (int) ((w / 2) * Math.sin(t - Math.PI / 2) + y);
        int y3 = (int) (-h * Math.sin(t) + y);

        triangles.add(new Triangle(x1, x2, x3, y1, y2, y3, depth));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < triangles.size(); i++) {
            triangles.get(i).drawTriangle(g2);
        }

        g2.dispose();
    }

}
