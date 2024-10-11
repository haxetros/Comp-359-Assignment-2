package bfs;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class GraphPanel extends JFrame {
    private Graph<String> graph; //The graph structure, storing nodes and edges
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

        // Get memory usage before the search
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        Optional<Node<String>> result = graph.bfsSearch(targetValue, 15); // 执行BFS搜索
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        // Get memory usage after the search
        long memoryUsedByArray = memoryAfter - memoryBefore;

        // Build the output string containing the search result and memory usage
        String output = "Found node with value: " + targetValue + "\n" +
                        "Memory used before: " + memoryBefore + " bytes\n" +
                        "Memory used after: " + memoryAfter + " bytes\n" +
                        "Memory used by the array: " + memoryUsedByArray + " bytes";

        // Update the memory label to display the information
        memoryLabel.setText(output);

        // Update the UI based on the search result
        if (result.isPresent()) {
            // If the target node is found, highlight it
            graphPanel.setHighlightedNode(targetValue);
        } else {
            // If the target node is not found, clear any highlights and show a popup message
            graphPanel.setHighlightedNode(null);
            JOptionPane.showMessageDialog(this, "Node not found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
        }

        // Repaint the graph panel to update the graph display
        graphPanel.repaint();
    }

    // Inner class for drawing the graph visualization
    private class InnerGraphPanel extends JPanel {
        private String highlightedNode; // The node to be highlighted
        private boolean highlightAllNodes; // Whether to highlight all nodes

        // Set the node to be highlighted
        public void setHighlightedNode(String node) {
            this.highlightedNode = node; // Set the target node
            this.highlightAllNodes = false; // Disable full highlight
        }

        // Set whether to highlight all nodes
        public void setHighlightAllNodes(boolean highlight) {
            this.highlightAllNodes = highlight; // Update whether to highlight all nodes
            if (highlight) {
                this.highlightedNode = null; // If highlighting all nodes, clear individual highlight
            }
        }

        // Override the paintComponent method of JPanel to draw the graph and nodes
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Draw edges
            g2d.setColor(Color.BLACK); // Set the edge color to black
            drawEdge(g2d, "A", "B");
            drawEdge(g2d, "A", "C");
            drawEdge(g2d, "A", "D");
            drawEdge(g2d, "A", "E");
            drawEdge(g2d, "B", "F");
            drawEdge(g2d, "B", "G");
            drawEdge(g2d, "C", "H");
            drawEdge(g2d, "C", "I");
            drawEdge(g2d, "C", "J");
            drawEdge(g2d, "D", "K");
            drawEdge(g2d, "D", "L");
            drawEdge(g2d, "E", "N");
            drawEdge(g2d, "E", "M");
            drawEdge(g2d, "F", "G");
            drawEdge(g2d, "L", "M");
            drawEdge(g2d, "L", "O");

            // Draw nodes
            for (String node : new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"}) {
                drawNode(g2d, node);
            }
        }

        // Method to draw edges between two nodes
        private void drawEdge(Graphics2D g2d, String from, String to) {
            Point p1 = getNodePosition(from);
            Point p2 = getNodePosition(to);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        // Method to draw a node
        private void drawNode(Graphics2D g2d, String node) {
            Point p = getNodePosition(node);
            int diameter = 30;

            // Set color based on whether to highlight
            if (highlightAllNodes || node.equals(highlightedNode)) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(Color.BLUE);
            }

            g2d.fillOval(p.x - diameter/2, p.y - diameter/2, diameter, diameter);
            g2d.setColor(Color.WHITE);
            g2d.drawString(node, p.x - 5, p.y + 5);
        }

        private Point getNodePosition(String node) {
            // Define positions for each node 
            switch (node) {
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
