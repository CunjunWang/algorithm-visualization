package fractal.sierpinski.triangle;

import gui_frame.AlgorithmVisualizationHelper;

import javax.swing.*;
import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-27.
 */
public class SierpinskiTriangleFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public SierpinskiTriangleFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);

        if (canvasWidth <= 0 || canvasHeight <= 0)
            throw new IllegalArgumentException("Width or height cannot be negative.");

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        SierpinskiTriangleFrame.AlgorithmCanvas canvas = new SierpinskiTriangleFrame.AlgorithmCanvas();
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

    private SierpinskiTriangleData data;

    public void render(SierpinskiTriangleData data) {
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
            drawSierpinskiTriangle(g2d, 0, canvasHeight, canvasWidth, 0);
        }

        private void drawSierpinskiTriangle(Graphics2D g2d, int Ax, int Ay, int side, int depth) {

            if (side <= 1) {
                AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.Indigo);
                AlgorithmVisualizationHelper.fillRectangle(g2d, Ax, Ay, 1, 1);
                return;
            }

            int Bx = Ax + side;
            int By = Ay;

            int h = (int) (Math.sin(60 * Math.PI / 180) * side);
            int Cx = Ax + side / 2;
            int Cy = Ay - h;

            if (depth == data.getDepth()) {
                AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.Indigo);
                AlgorithmVisualizationHelper.fillTriangle(g2d, Ax, Ay, Bx, By, Cx, Cy);
                return;
            }

            int AB_centerX = (Ax + Bx) / 2;
            int AB_centerY = (Ay + By) / 2;

            int AC_centerX = (Ax + Cx) / 2;
            int AC_centerY = (Ay + Cy) / 2;

            drawSierpinskiTriangle(g2d, Ax, Ay, side / 2, depth + 1);
            drawSierpinskiTriangle(g2d, AC_centerX, AC_centerY, side / 2, depth + 1);
            drawSierpinskiTriangle(g2d, AB_centerX, AB_centerY, side / 2, depth + 1);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}
