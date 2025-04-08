import java.util.ArrayList;
import java.util.Random;

import becker.robots.*;

public class MazeBot extends RobotSE{

    private Thing treasure;
    private City maze;
    private ArrayList<String> directions = new ArrayList<>();

    public MazeBot(City arg0, int arg1, int arg2, Direction arg3, int arg4) {
        super(arg0, arg1, arg2, arg3, arg4);
        maze= arg0;
        Random rG = new Random();
        int rS=rG.nextInt(10);
        int rA =rG.nextInt(10);
        treasure = new Thing(maze,rS,rA);
    }

    public void findThing() {

        System.out.println("LET'S FIND IT!");
    }

    public void goHome() {
      
        System.out.println("GOING HOME!");
        
    }
}