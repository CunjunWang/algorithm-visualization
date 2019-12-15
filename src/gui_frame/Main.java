package gui_frame;

import java.awt.*;

/**
 * Created by CunjunWang on 2019-12-15.
 */
public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            AlgorithmFrame frame = new AlgorithmFrame("Welcome", 500, 500);
        });
    }
}
