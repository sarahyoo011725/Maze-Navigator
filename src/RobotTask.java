import becker.robots.*;

public class RobotTask {

    public void run() {
        final int streets = 10;
        final int avenues = 10;
        City maze = new MazeCity(streets, avenues);

        MazeBot mB = new MazeBot(maze, 3,4, streets, avenues);
        mB.takeShortestPath();
        mB.goHome();
    }
}
