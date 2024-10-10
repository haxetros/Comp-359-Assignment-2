import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

// Modified code based on https://www.baeldung.com/java-breadth-first-search
public class BFS {

    public static <T> Optional<Node<T>> search(T value, Node<T> start, int n) {
        // The original code from baeldung uses the Java ArrayDeque API, however the assignment instructions state:
        // "just use an array to implement the queue you will need while performing BFS"
        // So we define a raw Java array with a predefined size based on the expected number of nodes we need (n)
        // And then use head and tail pointers so we know where to enqueue and dequeue from the array
        // Modified code based on https://medium.com/omarelgabrys-blog/stacks-and-queues-d96c6f35fae3
        Node<T>[] queue = new Node[n];
        int head = 0;
        int tail = 0;

        Set<Node<T>> visited = new HashSet<>();

        // Enqueue
        queue[tail++] = start;

        visited.add(start);

        while (head < tail) {
//            // For debugging: Check the queue
//            System.out.print("Queue: [");
//            for (int i = head; i < tail; i++) {
//                Node<T> node = queue[i];
//                System.out.print(node.getValue() + " ");
//            }
//            System.out.println("]");

            // Dequeue
            Node<T> currentNode = queue[head++];

//            // For debugging: Check the current node
//            System.out.print("Current Node: ");
//            System.out.println(currentNode.getValue());

            if (currentNode.getValue().equals(value)) {
                return Optional.of(currentNode);
            }

            for (Node<T> neighbor : currentNode.getNeighbors()) {
                if (!visited.contains(neighbor)) {
                    queue[tail++] = neighbor;
                    visited.add(neighbor);
                }
            }
        }

        return Optional.empty();
    }

 
