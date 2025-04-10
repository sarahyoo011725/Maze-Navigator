import becker.robots.*;
import java.awt.Color;
import java.util.Stack;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class MazeBot extends DaveSoftware{
    private final Thing treasure;
    private final Point thingPoint;
    private final Point home; 
    
    private final int streets = 10, avenues = 10;
    
    public MazeBot(City city, int y, int x) {
        super(city, y, x);
        home = new Point(x, y);
        thingPoint = Point.createRandomPoint(0, 10, 0, 10);
        treasure = new Thing(city, thingPoint.y, thingPoint.x);
    }

    HashMap<Point, Direction> directions = new HashMap<>(){{
        put(new Point(0, 1), Direction.SOUTH);
        put(new Point(0, -1), Direction.NORTH);
        put(new Point(-1, 0), Direction.WEST);
        put(new Point(1, 0), Direction.EAST);
    }};

    public void Astar() {
        PriorityQueue<Node> toVisit = new PriorityQueue<>(); //priority queue orders node from smallest f --> todo: makes the queue orders with f  
        HashSet<Point> visited = new HashSet<>();

        double start_g = 0;
        double start_h = home.distanceTo(thingPoint);
        double start_f = start_g + start_h;
        Node startNode = new Node(home, start_f, start_g, start_h);
        startNode.parent = null;

        toVisit.offer(startNode);
        
        while (!toVisit.isEmpty()) {
            //get the node with smallest F
            Node minNode = toVisit.poll();
            visited.add(minNode.point);

            //if the adjacent point == thingPoint, stop searching and solve the maze
            if (minNode.point.equals(thingPoint)) {
                System.out.println(minNode.point.toString());
                Stack<Point> path = createPath(minNode);
                path.addFirst(minNode.point);
                solve(path);
                return;
            }

            //check for 4 adjacents 
            for (int dx = -1; dx <= 1; ++dx) {
                for (int dy = -1; dy <= 1; ++dy) {
                    Direction dir = directions.get(new Point(dx, dy)); //get directions to check walls in 4 directions
                    if (dir == null) continue; // we don't check adjacent points diagonally 

                    //adjacent point of minNode
                    Point adjacentPoint = new Point(minNode.point.x + dx, minNode.point.y + dy);
                    if (!valid(adjacentPoint) || visited.contains(adjacentPoint)) continue; //checks if the point is within the maze

                    //creates dummy bot to check for wall in front of it
                    RobotSE dummy = new RobotSE(getCity(), minNode.point.y, minNode.point.x, dir);
                    dummy.setTransparency(0.9);
                    dummy.setColor(Color.blue);
                    if (!dummy.frontIsClear()) continue; //if there is wall in front, don't check that adjacent point in that direction

                    //tentative G = minNode.g + distance between minNode and adjacent Node which is always 1
                    double G = minNode.g + 1;
                    double H = adjacentPoint.distanceTo(thingPoint);
                    double F = G + H;
                    
                    //get or create node instance for the adjacent point
                    Node adjacentNode = new Node(adjacentPoint);
                    if (!toVisit.contains(adjacentNode)) {
                        toVisit.offer(adjacentNode);
                    }
                    
                    if (adjacentNode.f >= F) continue; //still not the best path, so skip
                    
                    //new Thing(getCity(), adjacentPoint.y, adjacentPoint.x).setColor(Color.DARK_GRAY); //node
                    adjacentNode.setParent(minNode); 
                    adjacentNode.g = G;
                    adjacentNode.h = H; 
                    adjacentNode.h = F;
                }
            }
        }
    }

    public Stack<Point> createPath(Node node) {
        Stack<Point> path = new Stack<>();
        while (node.parent != null) {
            path.add(node.parent.point);
            node = node.parent;
        }
        return path;
    }

    public void solve(Stack<Point> path) {
        while (!path.isEmpty()) {
            Point p = path.getLast();
            path.pop();
            goTo(p);
        }
        pickAllThings();
        System.out.println("found the treasure!");
    }

    private boolean valid(Point p) {
        return p.x >= 0 && p.x < avenues && p.y >= 0 && p.y < streets;
    }

    public void goHome() {
        System.out.println("GOING HOME!");
    }
}