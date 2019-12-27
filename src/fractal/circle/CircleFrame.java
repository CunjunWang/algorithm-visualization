package fractal.circle;

import gui_frame.AlgorithmVisualizationHelper;

import javax.swing.*;
import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-27.
 */
public class CircleFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public CircleFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);

        if (canvasWidth <= 0 || canvasHeight <= 0)
            throw new IllegalArgumentException("Width or height cannot be negative.");

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        CircleFrame.AlgorithmCanvas canvas = new CircleFrame.AlgorithmCanvas();
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

    private CircleData data;

    public void render(CircleData data) {
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
            g2d.addRenderingHints(hints);

            // paint
            drawCircle(g2d, data.getStartX(), data.getStartY(),
                    data.getStartR(), 0);
        }

        private void drawCircle(Graphics2D g2d, int x, int y, int r, int d) {
            if (d == data.getDepth() || r < 1)
                return;

            if (d % 2 == 0)
                AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.Red);
            else
                AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.LightBlue);

            AlgorithmVisualizationHelper.fillCircle(g2d, x, y, r);
            drawCircle(g2d, x, y, r - data.getStep(), d + 1);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}
