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
            // Dequeue
            Node<T> currentNode = queue[head++];

            //System.out.print("Current Node: ");
            //System.out.println(currentNode.getValue());

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

    public static void main(String[] args) {
        // Create nodes
        Node<String> nodeA = new Node<>("A");
        Node<String> nodeB = new Node<>("B");
        Node<String> nodeC = new Node<>("C");
        Node<String> nodeD = new Node<>("D");

        // Number of nodes
        int n = 4;

        // Connect nodes
        nodeA.connect(nodeB);
        nodeA.connect(nodeC);
        nodeB.connect(nodeD);

        // Perform BFS to find node with the value D starting from Node A
        Optional<Node<String>> result = BFS.search("D", nodeA, n);

        if (result.isPresent()) {
            System.out.println("Found node with value: " + result.get().getValue());
        } else {
            System.out.println("Node not found.");
        }
    }
}
