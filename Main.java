package bfs;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
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
        
        // Connect nodes
        nodeA.connect(nodeB);
        nodeA.connect(nodeC);
        nodeA.connect(nodeD);
        nodeA.connect(nodeE);
        
        nodeB.connect(nodeF);
        nodeB.connect(nodeG);
        
        nodeC.connect(nodeH);
        nodeC.connect(nodeI);
        nodeC.connect(nodeJ);
        
        nodeD.connect(nodeK);
        nodeD.connect(nodeL);
        
        nodeE.connect(nodeN);
        nodeE.connect(nodeM);
        
        nodeF.connect(nodeG);
        
        nodeL.connect(nodeM);
        nodeL.connect(nodeO);

        // Create the graph
        Graph<String> graph = new Graph<>(nodeA);

        // Create and show the GraphPanel
        SwingUtilities.invokeLater(() -> new GraphPanel(graph));
    }
}
