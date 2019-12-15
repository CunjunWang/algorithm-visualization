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


    /**
     * internal canvas class
     */
    private class AlgorithmCanvas extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            AlgorithmVisualizationHelper.setStrokeWidth(g2d, 5);

            // 基于状态的绘制，设置的红色会保持下去，直到显式改变状态
            AlgorithmVisualizationHelper.setColor(g2d, Color.BLUE);
            AlgorithmVisualizationHelper.fillCircle(g2d, canvasWidth / 2, canvasHeight / 2, 200);

            AlgorithmVisualizationHelper.setColor(g2d, Color.RED);
            AlgorithmVisualizationHelper.strokeCircle(g2d, canvasWidth / 2, canvasHeight / 2, 200);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}
