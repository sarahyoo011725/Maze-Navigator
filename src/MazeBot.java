import java.util.ArrayList;
import java.util.Random;

import becker.robots.*;

public class MazeBot extends DaveSoftware{
    private Thing treasure;
    private City maze;
    private ArrayList<String> directions = new ArrayList<>();

    public MazeBot(City city, int y, int x) {
        super(city, y, x);
        maze= city;
        Random rG = new Random();
        int rS = rG.nextInt(10);
        int rA = rG.nextInt(10);
        treasure = new Thing(maze, rS, rA);
    }

    public void findThing() {

    }

    public void goHome() {
        System.out.println("GOING HOME!");
    }
}