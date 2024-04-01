import java.awt.Color;
import java.awt.Graphics2D;

public class Triangle {

    int[] xPoints, yPoints;

    int r = 102;
    int g = 56;
    int b = 9;
    int lr = 161;
    int lg = 200;
    int lb = 64;
    Color color, leafColor;
    int radius = 10;

    public Triangle(int x1, int x2, int x3, int y1, int y2, int y3, int depth) {
        xPoints = new int[3];
        yPoints = new int[3];
        xPoints[0] = x1;
        xPoints[1] = x2;
        xPoints[2] = x3;
        yPoints[0] = y1;
        yPoints[1] = y2;
        yPoints[2] = y3;
        color = new Color(r * depth / Tree.depth, g * depth / Tree.depth, b * depth / Tree.depth);
        leafColor = new Color(lr * depth / Tree.depth, lg * depth / Tree.depth, lb * depth / Tree.depth);
    }

    public void drawTriangle(Graphics2D g2) {
        g2.setColor(color);
        g2.fillPolygon(xPoints, yPoints, 3);
        g2.setColor(leafColor);
        g2.fillOval(xPoints[2] - radius, yPoints[2] - radius, radius * 2, radius * 2);
        for (int i = 0; i < 3; i++) {
            // g2.fillRect(xPoints[i], yPoints[i], (i + 1) * 10, (i + 1) * 10);
        }
    }

}
