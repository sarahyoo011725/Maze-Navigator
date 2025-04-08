import becker.robots.*;

public class RobotTask {

    public void run() {
        City maze = new MazeCity(10, 10);

        MazeBot mB = new MazeBot(maze, 3,4);
        mB.findThing();  
        mB.goHome();
    }
}
