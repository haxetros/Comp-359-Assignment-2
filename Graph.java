package bfs;

import java.util.Map;

public class Graph<T> {
    private Node<T> startNode;
    private Map<String, Node<T>> nodes; // Store all nodes in a map

    public Graph(Node<T> startNode, Map<String, Node<T>> nodes) {
        this.startNode = startNode;
        this.nodes = nodes;
    }

    public Node<T> getStartNode() {
        return startNode;
    }

    public Map<String, Node<T>> getNodes() {
        return nodes;
    }
}
