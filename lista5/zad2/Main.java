public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <number_of_vertices>");
            return;
        }

        int n = 0;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("The number of vertices must be an integer.");
            return;
        }

        if (n <= 0) {
            System.out.println("The number of vertices must be a positive integer.");
            return;
        }

        Graph graph = new Graph(n);
        System.out.println("Graph created with " + graph.getNumberOfVertices() + " vertices and " + graph.getNumberOfEdges() + " edges.\n");

        double[][] adjacencyMatrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (Edge edge : graph.adjacencyLists[i]) {
                int src = edge.getSource();
                int dest = edge.getDestination();
                double weight = edge.getWeight();
                adjacencyMatrix[src][dest] = weight;
                adjacencyMatrix[dest][src] = weight;
            }
        }

        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%.2f ", adjacencyMatrix[i][j]);
            }
            System.out.println();
        }

        System.out.println();

        PrimsMST primMST = new PrimsMST(graph);
        System.out.println("Minimum Spanning Tree (MST) edges using Prim's Algorithm:");
        for (Edge edge : primMST.edges()) {
            System.out.println(edge);
        }

        int[][] primMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (Edge edge : primMST.edges()) {
                int src = edge.getSource();
                int dest = edge.getDestination();
                primMatrix[src][dest] = 1;
                primMatrix[dest][src] = 1;
            }
        }

        int root = 0;
        int[] rounds = RoundMinimizer.minimizeRounds(primMatrix, n, root);
        int maxRound = 0;
        System.out.println("\nRounds needed for each vertex:");
        for (int i = 0; i < n; i++) {
            if (rounds[i] > maxRound) maxRound = rounds[i];
            System.out.println("Vertex " + i + ": " + rounds[i] + " rounds");
        }

        System.out.println("\nMaximum round: " + maxRound);
    }
}
