package java_practice.graphs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java_practice.graphs.Graph.Edge;



// Main class to display the graph in a GUI
public class GraphGUI extends JPanel {
    private List<List<Edge>> graph;

    public GraphGUI(List<List<Edge>> graph) {
        this.graph = graph;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int nodeRadius = 20; // Radius of each node
        int padding = 50;    // Padding around the graph
        int spacing = 100;   // Spacing between nodes

        // Draw nodes and edges
        for (int i = 0; i < graph.size(); i++) {
            int x = padding + i * spacing;
            int y = padding;

            // Draw the node
            g2d.setColor(Color.BLUE);
            g2d.fillOval(x - nodeRadius, y - nodeRadius, 2 * nodeRadius, 2 * nodeRadius);
            g2d.setColor(Color.BLACK);
            g2d.drawString("Node " + i, x - nodeRadius, y - nodeRadius - 5);

            // Draw edges from this node
            for (Edge e : graph.get(i)) {
                int destX = padding + e.dest * spacing;
                int destY = padding;

                // Draw the edge line
                g2d.setColor(Color.RED);
                g2d.drawLine(x, y, destX, destY);

                // Draw the weight label
                int midX = (x + destX) / 2;
                int midY = (y + destY) / 2;
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(e.wt), midX, midY - 5);
            }
        }
    }

    public static void printGraphGUI(List<List<Edge>> graph) {
        JFrame frame = new JFrame("Graph Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Set the size of the window
        frame.add(new GraphGUI(graph));
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Example graph
        List<List<Edge>> graph = List.of(
                List.of(new Edge(1, 2), new Edge(2, 3)), // Node 0
                List.of(new Edge(2, 1)),                 // Node 1
                List.of(new Edge(0, 4), new Edge(3, 5)), // Node 2
                List.of(new Edge(1, 7))                  // Node 3
        );

        // Print the graph in the console
        printGraph(graph);

        // Display the graph in a GUI
        printGraphGUI(graph);
    }

    // Console print method (for reference)
    public static void printGraph(List<List<Edge>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            System.out.print("Node " + i + " -> ");
            for (Edge e : graph.get(i)) {
                System.out.print("(" + e.dest + ", " + e.wt + ") ");
            }
            System.out.println();
        }
    }
}