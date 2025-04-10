import java.util.HashSet;
import java.util.Optional;

public class Graph {
    private final HashSet<Node> nodes = new HashSet<>();

    public Node addNode(Node node) {
        nodes.add(node);
        return node;
    }

    public Node addNode(Point p, double f, double g, double h) {
        return addNode(new Node(p, f, g, h));
    }

    public Node addNode(int x, int y) {
        Node node = new Node(x, y);
        nodes.add(node);
        return node;
    }

    public Node addNode(Point p) {
        return addNode(p.x, p.y);
    }

    public Optional<Node> find(Point p) {
        for (Node node : nodes) {
            if (node.point.equals(p)) {
                return Optional.of(node);
            }
        }
        return Optional.empty();
    }
}
