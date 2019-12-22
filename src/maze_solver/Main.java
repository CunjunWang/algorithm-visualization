package maze_solver;

/**
 * Created by CunjunWang on 2019-12-22.
 */
public class Main {

    public static void main(String[] args) {
        String mazeFile = "./src/maze_solver/maze.txt";
        MazeData data = new MazeData(mazeFile);
        data.print();
    }
}
