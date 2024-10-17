package bfs;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

// Modified code based on https://www.baeldung.com/java-breadth-first-search

// NOTE:
// This class is not used at all by Main.java because the color switching visualization was too complicated to implement
// between two classes, so we reimplemented the BFS algorithm within GraphPanel to make the visualization code simpler
// So this class is used as a working reference for the rest of the java code
// Use git to checkout the commit 4c5e696 to see a working BFS example using this class
public class BFS {

    public static <T> Optional<Node<T>> search(T value, Node<T> start, int n) {
    	
    	// The original code from baeldung uses the Java ArrayDeque API, however the assignment instructions state:
        // "just use an array to implement the queue you will need while performing BFS"
        // So we define a raw Java array with a predefined size based on the expected number of nodes we need (n)
        // And then use head and tail pointers so we know where to enqueue and dequeue from the array
        // Modified code based on https://medium.com/omarelgabrys-blog/stacks-and-queues-d96c6f35fae3
        Node<T>[] queue = new Node[n];
        int head = 0; // Points to the front of the queue (dequeue)
        int tail = 0; // Points to the end of the queue (enqueue)

        Set<Node<T>> visited = new HashSet<>();

        // Enqueue the starting node
        queue[tail++] = start;
        visited.add(start);

        while (head < tail) {
            // Dequeue the next node
            Node<T> currentNode = queue[head++];

            // Check if we've found the value
            if (currentNode.getValue().equals(value)) {
                return Optional.of(currentNode);
            }

            // Enqueue unvisited neighbors
            for (Node<T> neighbor : currentNode.getNeighbors()) {
                if (!visited.contains(neighbor)) {
                    // Check for queue overflow before enqueueing
                    if (tail >= n) {
                        throw new IllegalStateException("Queue overflow: too many nodes to process.");
                    }
                    queue[tail++] = neighbor;
                    visited.add(neighbor);
                }
            }
        }
        return Optional.empty();
    }
}
