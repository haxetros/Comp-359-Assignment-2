package bfs;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GraphPanel extends JFrame {
    private Graph<String> graph; // The graph structure, storing nodes and edges
    private JComboBox<String> nodeSelector; // Dropdown for selecting a node to search
    private JButton searchButton; // Button to trigger the search
    private InnerGraphPanel graphPanel; // The panel used to draw the graph visualization
    private JLabel memoryLabel; // Label to display memory usage information

    // Constructor to initialize the window and components
    public GraphPanel(Graph<String> graph) {
        this.graph = graph; // Assign the graph passed in to the instance variable
        setTitle("Graph BFS Visualization"); // Set the window title
        setSize(800, 600); // Set the window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the window is closed

        initComponents(); // Initialize all components
        layoutComponents(); // Arrange the components

        setVisible(true); // Make the window visible
    }

    // Initialize the components
    private void initComponents() {
    	
    	// Create a dropdown with node names as selectable items
        nodeSelector = new JComboBox<>(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"});
        searchButton = new JButton("Search");
        graphPanel = new InnerGraphPanel();
        memoryLabel = new JLabel("Memory used: 0 bytes");

        // Add an action listener to the search button to trigger the search when clicked
        searchButton.addActionListener(e -> performSearch());
    }

    
    // Arrange the components
    private void layoutComponents() {
        setLayout(new BorderLayout());

        // Create a control panel to hold the dropdown and button
        JPanel controlPanel = new JPanel();
        controlPanel.add(nodeSelector);
        controlPanel.add(searchButton);

        // Add different parts to the window in different areas
        add(controlPanel, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);
        add(memoryLabel, BorderLayout.SOUTH);
    }

    // This method executes the search operation
    private void performSearch() {
    	
    	// Get the node selected by the user
        String targetValue = (String) nodeSelector.getSelectedItem();
        Runtime runtime = Runtime.getRuntime(); // Get the JVM runtime object
        runtime.gc(); // Manually trigger garbage collection to ensure accurate memory stats

        // Reset node colors before starting the search
        for (Node<String> node : graph.getNodes().values()) {
            node.setColor(Color.BLUE);
        }
        graphPanel.repaint();

        // Get memory usage before the search
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        // Perform BFS search with visualization
        Optional<Node<String>> result = search(targetValue);

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        // Get memory usage after the search
        long memoryUsedByArray = memoryAfter - memoryBefore;

        // Build the output string containing the search result and memory usage
        String output = "<html>Found node with value: " + targetValue + "<br/>" +
                "Memory used before: " + memoryBefore + " bytes<br/>" +
                "Memory used after: " + memoryAfter + " bytes<br/>" +
                "Memory used by the array: " + memoryUsedByArray + " bytes</html>"; // \n doesn't work here, we use <br/>

        // Update the memory label to display the information
        memoryLabel.setText(output);

        // Update the UI based on the search result
        if (!result.isPresent()) {
            JOptionPane.showMessageDialog(this, "Node not found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        }

        // Repaint the graph panel to update the graph display
        graphPanel.repaint();
    }

    // Modified BFS search algorithm with visualization
      public Optional<Node<String>> search(String value) {
        int n = 15;
        Node<String>[] queue = new Node[n];
        int head = 0;
        int tail = 0;

        Set<Node<String>> visited = new HashSet<>();

        Node<String> start = graph.getStartNode();
        queue[tail++] = start;
        visited.add(start);

        start.setColor(Color.ORANGE);
        updateGraphVisual();

        while (head < tail) {
            Node<String> currentNode = queue[head++];

            currentNode.setColor(Color.RED);
            updateGraphVisual();

            if (currentNode.getValue().equals(value)) {
                currentNode.setColor(Color.GREEN);
                updateGraphVisual();
                return Optional.of(currentNode);
            }

            for (Node<String> neighbor : currentNode.getNeighbors()) {
                if (!visited.contains(neighbor)) {
                    queue[tail++] = neighbor;
                    visited.add(neighbor);

                    neighbor.setColor(Color.ORANGE);
                    updateGraphVisual();
                }
            }

            currentNode.setColor(Color.GRAY);
            updateGraphVisual();
        }

        return Optional.empty();
    }

    // Inner class for drawing the graph visualization
    private class InnerGraphPanel extends JPanel {

        // Override the paintComponent method of JPanel to draw the graph and nodes
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Draw edges
            g2d.setColor(Color.BLACK); // Set the edge color to black
            drawEdges(g2d);

            // Draw nodes
            for (Node<String> node : graph.getNodes().values()) {
                drawNode(g2d, node);
            }
        }

        // Method to draw edges between two nodes
        private void drawEdge(Graphics2D g2d, Node<String> from, Node<String> to) {
            Point p1 = getNodePosition(from);
            Point p2 = getNodePosition(to);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        // Method to draw all edges
        private void drawEdges(Graphics2D g2d) {
            Set<String> drawnEdges = new HashSet<>();
            for (Node<String> node : graph.getNodes().values()) {
                for (Node<String> neighbor : node.getNeighbors()) {
                    String edgeKey = node.getValue() + "-" + neighbor.getValue();
                    String reverseEdgeKey = neighbor.getValue() + "-" + node.getValue();
                    if (!drawnEdges.contains(edgeKey) && !drawnEdges.contains(reverseEdgeKey)) {
                        drawEdge(g2d, node, neighbor);
                        drawnEdges.add(edgeKey);
                    }
                }
            }
        }

        // Method to draw a node
        private void drawNode(Graphics2D g2d, Node<String> node) {
            Point p = getNodePosition(node);
            int diameter = 30;

            g2d.setColor(node.getColor());

            g2d.fillOval(p.x - diameter / 2, p.y - diameter / 2, diameter, diameter);
            g2d.setColor(Color.WHITE);
            g2d.drawString(node.getValue(), p.x - 5, p.y + 5);
        }

        // Method to get node position on the panel, exact position created by chatgpt.
        private Point getNodePosition(Node<String> node) {
            String value = node.getValue();
            // Define positions for each node
            switch (value) {
                case "A": return new Point(400, 50);
                case "B": return new Point(200, 150);
                case "C": return new Point(400, 150);
                case "D": return new Point(600, 150);
                case "E": return new Point(700, 150);
                case "F": return new Point(100, 250);
                case "G": return new Point(300, 250);
                case "H": return new Point(350, 250);
                case "I": return new Point(450, 250);
                case "J": return new Point(550, 250);
                case "K": return new Point(600, 250);
                case "L": return new Point(700, 250);
                case "M": return new Point(750, 350);
                case "N": return new Point(800, 250);
                case "O": return new Point(650, 350);
                default: return new Point(0, 0);
            }
        }
    }
}
