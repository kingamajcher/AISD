import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class PrimsMST {
    private boolean[] visited;
    private List<Edge> mst;
    private double totalWeight;
    private PriorityQueue<Edge> pq;

    public PrimsMST(Graph graph) {
        visited = new boolean[graph.getNumberOfVertices()];
        mst = new ArrayList<>();
        totalWeight = 0.0;
        pq = new PriorityQueue<>(Comparator.comparingDouble(Edge::getWeight));

        visit(graph, 0);
        while (!pq.isEmpty() && mst.size() < graph.getNumberOfVertices() - 1) {
            Edge edge = pq.poll();
            int u = edge.getSource();
            int v = edge.getDestination();

            if (visited[u] && visited[v]) continue;

            mst.add(edge);
            totalWeight += edge.getWeight();

            if (!visited[u]) visit(graph, u);
            if (!visited[v]) visit(graph, v);
        }
    }

    private void visit(Graph graph, int vertex) {
        visited[vertex] = true;
        for (Edge edge : graph.adjacencyLists[vertex]) {
            if (!visited[edge.getOppositeVertex(vertex)]) {
                pq.offer(edge);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double totalWeight() {
        return totalWeight;
    }
}