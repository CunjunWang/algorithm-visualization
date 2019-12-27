package fractal.vicsek;

import gui_frame.AlgorithmVisualizationHelper;

import javax.swing.*;
import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-27.
 */
public class VicsekFractalFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public VicsekFractalFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);

        if (canvasWidth <= 0 || canvasHeight <= 0)
            throw new IllegalArgumentException("Width or height cannot be negative.");

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        VicsekFractalFrame.AlgorithmCanvas canvas = new VicsekFractalFrame.AlgorithmCanvas();
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

    private VicsekFractalData data;

    public void render(VicsekFractalData data) {
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
            drawVicsek(g2d, 0, 0, canvasWidth, canvasHeight, 0);
        }

        private void drawVicsek(Graphics2D g2d, int x, int y, int w, int h, int d) {
            if (d == data.getDepth()) {
                AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.Indigo);
                AlgorithmVisualizationHelper.fillRectangle(g2d, x, y, w, h);
                return;
            }

            if (w <= 1 || h <= 1) {
                AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.Indigo);
                AlgorithmVisualizationHelper.fillRectangle(g2d, x, y, Math.max(w, 1), Math.max(h, 1));
                return;
            }

            int w_3 = w / 3;
            int h_3 = h / 3;

            drawVicsek(g2d, x, y, w_3, h_3, d + 1);
            drawVicsek(g2d, x + 2 * w_3, y, w_3, h_3, d + 1);
            drawVicsek(g2d, x + w_3, y + h_3, w_3, h_3, d + 1);
            drawVicsek(g2d, x, y + 2 * h_3, w_3, h_3, d + 1);
            drawVicsek(g2d, x + 2 * w_3, y + 2 * h_3, w_3, h_3, d + 1);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}
