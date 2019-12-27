package fractal.koch;

import gui_frame.AlgorithmVisualizationHelper;

import javax.swing.*;
import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-27.
 */
public class KnohSnowflakeFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public KnohSnowflakeFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);

        if (canvasWidth <= 0 || canvasHeight <= 0)
            throw new IllegalArgumentException("Width or height cannot be negative.");

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        KnohSnowflakeFrame.AlgorithmCanvas canvas = new KnohSnowflakeFrame.AlgorithmCanvas();
        // window size can be different with canvas size;
        this.setContentPane(canvas);
        pack(); // use this canvas to fill the window.

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    private KnohSnowflakeData data;

    public void render(KnohSnowflakeData data) {
        this.data = data;
        this.repaint();
    }

    /**
     * internal canvas class
     */
    private class AlgorithmCanvas extends JPanel {

        public AlgorithmCanvas() {
            // double cached
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            // anti-aliased
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            AlgorithmVisualizationHelper.setStrokeWidth(g2d, 2);
            // paint
            drawKnohSnowflake(g2d, 0, canvasHeight - 10, canvasWidth, 0, 0);
        }

        private void drawKnohSnowflake(Graphics2D g2d, double x1, double y1, double side, double angle, int depth) {
            if (side <= 0)
                return;

            if (depth == data.getDepth()) {
                double x2 = x1 + side * Math.cos(angle * Math.PI / 180);
                double y2 = y1 - side * Math.sin(angle * Math.PI / 180);
                AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.Indigo);
                AlgorithmVisualizationHelper.drawLine(g2d, x1, y1, x2, y2);
                return;
            }

            double side_3 = side / 3;

            drawKnohSnowflake(g2d, x1, y1, side_3, angle, depth + 1);

            double x2 = x1 + side_3 * Math.cos(angle * Math.PI / 180);
            double y2 = y1 - side_3 * Math.sin(angle * Math.PI / 180);
            drawKnohSnowflake(g2d, x2, y2, side_3, angle + 60, depth + 1);

            double x3 = x2 + side_3 * Math.cos((angle + 60) * Math.PI / 180);
            double y3 = y2 - side_3 * Math.sin((angle + 60) * Math.PI / 180);
            drawKnohSnowflake(g2d, x3, y3, side_3, angle - 60, depth + 1);

            double x4 = x3 + side_3 * Math.cos((angle - 60) * Math.PI / 180);
            double y4 = y3 - side_3 * Math.sin((angle - 60) * Math.PI / 180);
            drawKnohSnowflake(g2d, x4, y4, side_3, angle, depth + 1);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}
