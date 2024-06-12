import java.util.*;

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
                int src = edge.getSrc();
                int dest = edge.getDest();
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
                int src = edge.getSrc();
                int dest = edge.getDest();
                primMatrix[src][dest] = 1;
                primMatrix[dest][src] = 1;
            }
        }

        int root = 0;
        int[] rounds = minimizeRounds(primMatrix, n, root);
        int maxRound = 0;
        System.out.println("Rounds needed for each vertex:");
        for (int i = 0; i < n; i++) {
            if (rounds[i] > maxRound) maxRound = rounds[i];
            System.out.println("Vertex " + i + ": " + rounds[i] + " rounds");
        }

        System.out.println("Maximum round: " + maxRound);
    }

    public static int[] minimizeRounds(int[][] mst, int n, int root) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mst[i][j] == 1) {
                    adjList.get(i).add(j);
                }
            }
        }

        int[] rounds = new int[n];
        Arrays.fill(rounds, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        rounds[root] = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int u = queue.poll();
                
                List<Integer> children = adjList.get(u);
                children.sort((a, b) -> adjList.get(b).size() - adjList.get(a).size());
                int r = rounds[u] + 1;

                for (int v : children) {
                    if (rounds[v] == -1) {
                        rounds[v] = r;
                        queue.offer(v);
                        r++;
                    }
                }
            }
        }

        return rounds;
    }
}
