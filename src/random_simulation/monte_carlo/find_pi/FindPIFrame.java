package random_simulation.monte_carlo.find_pi;

import gui_frame.AlgorithmVisualizationHelper;

import javax.swing.*;
import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-19.
 * <p>
 * Compute the approximate value of PI with Monte Carlo
 */
public class FindPIFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public FindPIFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);

        if (canvasWidth <= 0 || canvasHeight <= 0)
            throw new IllegalArgumentException("Width or height cannot be negative.");

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        FindPIFrame.AlgorithmCanvas canvas = new FindPIFrame.AlgorithmCanvas();
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

    private MonteCarloPIData data;

    public void render(MonteCarloPIData data) {
        this.data = data;
        this.repaint();
    }

    /**
     * internal canvas class
     */
    private class AlgorithmCanvas extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            // anti-aliased
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);

            // paint
            Circle circle = data.getCircle();
            AlgorithmVisualizationHelper.setStrokeWidth(g2d, 3);
            AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.Blue);
            AlgorithmVisualizationHelper.strokeCircle(g2d, circle.getX(), circle.getY(), circle.getR());

            for (int i = 0; i < data.getNumberOfPoints(); i++) {
                Point p = data.getPoint(i);
                if (circle.contains(p))
                    AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.Red);
                else
                    AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.Green);

                AlgorithmVisualizationHelper.fillCircle(g2d, p.x, p.y, 3);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
