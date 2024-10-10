
import java.util.Optional;

public class Main {
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

        // Create the graph
        Graph<String> graph = new Graph<>(nodeA);

        // Perform BFS to find node with the value O starting from Node A
        Optional<Node<String>> result = graph.bfsSearch("O", n);

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
