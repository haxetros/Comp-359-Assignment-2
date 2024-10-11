package bfs;

import java.util.LinkedList;
import java.util.List;

// Modified code based on https://www.baeldung.com/java-breadth-first-search
public class Node<T> {

    private T value;
    private List<Node<T>> neighbors;

    public Node(T value) {
        this.value = value;
        // We implement connections using linked lists as per assignment instructions
        this.neighbors = new LinkedList<>();
    }

    public T getValue() {
        return value;
    }

    public List<Node<T>> getNeighbors() {
        return neighbors;
    }

    public void connect(Node<T> node) {
        if (this == node) throw new IllegalArgumentException("Can't connect node to itself");
        this.neighbors.add(node);
        node.neighbors.add(this);
    }
}


