package sortings.selection_sort;

import gui_frame.AlgorithmVisualizationHelper;

import javax.swing.*;
import java.awt.*;

/**
 * Customized frame to show the algorithm simulation
 * <p>
 * Created by CunjunWang on 2019-12-15.
 */
public class SelectionSortFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public SelectionSortFrame(String title, int canvasWidth, int canvasHeight) {
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

    private SelectionSortData data;

    public void render(SelectionSortData data) {
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
            int w = canvasWidth / data.N();
            for (int i = 0; i < data.N(); i++) {
                if (i < data.sortedIndex)
                    AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.Red);
                else
                    AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.Grey);

                if (i == data.currentCompareIndex)
                    AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.LightBlue);

                if (i == data.currentMinIndex)
                    AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.Indigo);

                AlgorithmVisualizationHelper.fillRectangle(g2d, i * w, canvasHeight - data.get(i), w - 1, data.get(i));
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}
