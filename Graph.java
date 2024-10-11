package bfs;

import java.util.Optional;

public class Graph<T> {
    private Node<T> startNode;

    public Graph(Node<T> startNode) {
        this.startNode = startNode;
    }

    public Optional<Node<T>> bfsSearch(T value, int n) {
        return BFS.search(value, startNode, n);
    }

    public Node<T> getStartNode() {
        return startNode;
    }
}
