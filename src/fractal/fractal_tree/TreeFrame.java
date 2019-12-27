package fractal.fractal_tree;

import gui_frame.AlgorithmVisualizationHelper;

import javax.swing.*;
import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-27.
 */
public class TreeFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public TreeFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);

        if (canvasWidth <= 0 || canvasHeight <= 0)
            throw new IllegalArgumentException("Width or height cannot be negative.");

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        TreeFrame.AlgorithmCanvas canvas = new TreeFrame.AlgorithmCanvas();
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

    private TreeData data;

    public void render(TreeData data) {
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

            // paint
            drawTree(g2d, canvasWidth / 2, canvasHeight, canvasHeight, 0, 0);
        }

        private void drawTree(Graphics2D g2d, double x1, double y1, double side, double angle, int depth) {
            double side_2 = side / 2;

            if (side_2 <= 0)
                return;

            double x2 = x1 - side_2 * Math.sin(angle * Math.PI / 180);
            double y2 = y1 - side_2 * Math.cos(angle * Math.PI / 180);
            AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.Indigo);
            AlgorithmVisualizationHelper.drawLine(g2d, x1, y1, x2, y2);

            if (depth == data.getDepth())
                return;

            drawTree(g2d, x2, y2, side_2, angle + data.getSplitAngle() / 2, depth + 1);
            drawTree(g2d, x2, y2, side_2, angle - data.getSplitAngle() / 2, depth + 1);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}
