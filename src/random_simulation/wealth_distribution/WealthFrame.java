package random_simulation.wealth_distribution;

import gui_frame.AlgorithmVisualizationHelper;

import javax.swing.*;
import java.awt.*;

/**
 * Customized frame to show the algorithm simulation
 * <p>
 * Created by CunjunWang on 2019-12-15.
 */
public class WealthFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public WealthFrame(String title, int canvasWidth, int canvasHeight) {
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

    private int[] money;

    public void render(int[] money) {
        this.money = money;
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
            int w = canvasWidth / money.length;
            for (int i = 0; i < money.length; i++) {
                if (money[i] > 0) {
                    AlgorithmVisualizationHelper.setColor(g2d, Color.BLUE);
                    AlgorithmVisualizationHelper.fillRectangle(g2d,
                            i * w + 1, canvasHeight / 2 - money[i], w - 1, money[i]);
                } else {
                    AlgorithmVisualizationHelper.setColor(g2d, Color.RED);
                    AlgorithmVisualizationHelper.fillRectangle(g2d,
                            i * w + 1, canvasHeight / 2, w - 1, -money[i]);
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}
