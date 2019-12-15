package gui_frame;

import javax.swing.*;
import java.awt.*;

/**
 * Customized frame to show the algorithm simulation
 * <p>
 * Created by CunjunWang on 2019-12-15.
 */
public class AlgorithmFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgorithmFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);

        if (canvasWidth <= 0 || canvasHeight <= 0)
            throw new IllegalArgumentException("Width or height cannot be negative.");

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgorithmCanvas canvas = new AlgorithmCanvas();
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

    private Circle[] circles;

    public void render(Circle[] circles) {
        this.circles = circles;
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
            AlgorithmVisualizationHelper.setStrokeWidth(g2d, 1);
            AlgorithmVisualizationHelper.setColor(g2d, Color.RED);

            for (Circle circle : circles)
                if (!circle.isFilled)
                    AlgorithmVisualizationHelper.strokeCircle(g2d, circle.x, circle.y, circle.getR());
                else
                    AlgorithmVisualizationHelper.fillCircle(g2d, circle.x, circle.y, circle.getR());
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}
