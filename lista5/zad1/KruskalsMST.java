import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalsMST {
    private List<Edge> mst;
    private double totalWeight;
    private int[] parent;
    private int[] rank;
    private int verticesNum;

    public KruskalsMST(Graph graph) {
        mst = new ArrayList<>();
        totalWeight = 0.0;
        verticesNum = graph.getNumberOfVertices();

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < verticesNum; i++) {
            for (Edge edge : graph.adjacencyLists[i]) {
                if (edge.getSource() < edge.getDestination()) {
                    edges.add(edge);
                }
            }
        }

        makeset(verticesNum);

        Collections.sort(edges);

        for (Edge edge : edges) {
            int u = edge.getSource();
            int v = edge.getDestination();

            if (!(find(u) == find(v))) {
                union(u, v);
                mst.add(edge);
                totalWeight += edge.getWeight();
            }
        }
    }

    public void makeset(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int x = 0; x < n; x++) {
            parent[x] = x;
            rank[x] = 0;
        }
    }

    public int find(int x) {
        while (x != parent[x]) {
            x = parent[x];
        }
        return x;
    }

    public void union(int x, int y) {
        int rX = find(x);
        int rY = find(y);
        if (rX == rY) {
            return;
        }

        if (rank[rX] < rank[rY]) {
            parent[rX] = rY;
        } else if (rank[rX] > rank[rY]) {
            parent[rY] = rX;
        } else {
            parent[rY] = rX;
            rank[rX]++;
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double totalWeight() {
        return totalWeight;
    }
}