package gui_frame;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Render helper tools.
 * <p>
 * Created by CunjunWang on 2019-12-15.
 */
public class AlgorithmVisualizationHelper {

    private AlgorithmVisualizationHelper() {
    }

    public static void setStrokeWidth(Graphics2D g2d, int w) {
        g2d.setStroke(new BasicStroke(w,
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); // 平滑过渡
    }

    public static void setColor(Graphics2D g2d, Color color) {
        g2d.setColor(color);
    }

    public static void strokeCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.draw(circle);
    }

    public static void fillCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.fill(circle);
    }

    public static void pause(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

}
