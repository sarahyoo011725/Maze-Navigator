import becker.robots.*;

public class RobotTask {

    public void run() {
        City maze = new MazeCity(10, 10);

        MazeBot mB = new MazeBot(maze,5,5,Direction.NORTH,0);
        mB.findThing();  
        mB.goHome();

    }

}
