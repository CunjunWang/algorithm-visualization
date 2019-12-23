package maze_solver.data;

import gui_frame.AlgorithmVisualizationHelper;

import javax.swing.*;
import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-22.
 */
public class MazeSolverFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public MazeSolverFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);

        if (canvasWidth <= 0 || canvasHeight <= 0)
            throw new IllegalArgumentException("Width or height cannot be negative.");

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        MazeSolverFrame.AlgorithmCanvas canvas = new MazeSolverFrame.AlgorithmCanvas();
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

    private MazeData data;

    public void render(MazeData data) {
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
            int w = canvasWidth / data.getC();
            int h = canvasHeight / data.getR();
            for (int i = 0; i < data.getR(); i++)
                for (int j = 0; j < data.getC(); j++) {
                    if (data.getMaze(i, j) == MazeData.WALL)
                        AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.LightBlue);
                    else
                        AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.White);

                    if (data.onPath[i][j])
                        AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.Yellow);

                    if (data.result[i][j])
                        AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.Red);

                    AlgorithmVisualizationHelper.fillRectangle(g2d, j * w, i * h, w, h);
                }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
