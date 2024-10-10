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

    public static void main(String[] args) {
        // Get the instance of the runtime
        Runtime runtime = Runtime.getRuntime();

        // Run garbage collection
        runtime.gc();

        // Get memory usage
        long memoryUsedBefore = runtime.totalMemory() - runtime.freeMemory();

        // Create nodes
        Node<String> nodeA = new Node<>("A");
        Node<String> nodeB = new Node<>("B");
        Node<String> nodeC = new Node<>("C");
        Node<String> nodeD = new Node<>("D");
        Node<String> nodeE = new Node<>("E");
        Node<String> nodeF = new Node<>("F");
        Node<String> nodeG = new Node<>("G");
        Node<String> nodeH = new Node<>("H");
        Node<String> nodeI = new Node<>("I");
        Node<String> nodeJ = new Node<>("J");
        Node<String> nodeK = new Node<>("K");
        Node<String> nodeL = new Node<>("L");
        Node<String> nodeM = new Node<>("M");
        Node<String> nodeN = new Node<>("N");
        Node<String> nodeO = new Node<>("O");

        // Number of nodes
        int n = 15;

        // Connect nodes
        nodeA.connect(nodeB);
        nodeA.connect(nodeC);
        nodeA.connect(nodeD);
        nodeA.connect(nodeE);

        nodeB.connect(nodeF);
        nodeB.connect(nodeG);
        nodeB.connect(nodeA);

        nodeC.connect(nodeH);
        nodeC.connect(nodeI);
        nodeC.connect(nodeJ);
        nodeC.connect(nodeA);

        nodeD.connect(nodeK);
        nodeD.connect(nodeL);
        nodeD.connect(nodeA);

        nodeE.connect(nodeN);
        nodeE.connect(nodeM);
        nodeE.connect(nodeA);

        nodeF.connect(nodeG);

        nodeL.connect(nodeM);
        nodeL.connect(nodeO);

        nodeM.connect(nodeL);

        // Perform BFS to find node with the value O starting from Node A
        Optional<Node<String>> result = BFS.search("O", nodeA, n);

        if (result.isPresent()) {
            System.out.println("Found node with value: " + result.get().getValue());
        } else {
            System.out.println("Node not found.");
        }
        System.out.println("Memory used before: " + memoryUsedBefore + " bytes");
        long memoryUsedAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory used after: " + memoryUsedAfter + " bytes");

        // Calculate the difference
        long memoryDifference = memoryUsedAfter - memoryUsedBefore;
        System.out.println("Memory used by the array: " + memoryDifference + " bytes");
    }
}