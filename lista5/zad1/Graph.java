import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {
    final List<Edge>[] adjacencyLists;
    private final int verticesNum;
    private int edgesNum;

    public Graph(int verticesNum) {
        this.verticesNum = verticesNum;
        this.edgesNum = 0;
        this.adjacencyLists = (List<Edge>[]) new List[verticesNum];

        for (int i = 0; i < verticesNum; i++) {
            adjacencyLists[i] = new ArrayList<Edge>();
        }

        Random rand = new Random();

        for (int i = 0; i < verticesNum; i++) {
            for (int j = i + 1; j < verticesNum; j++) {
                double weight = 0;
                while (weight == 0) {
                    weight = rand.nextDouble();
                }
                Edge edge = new Edge(i, j, weight);
                addEdge(edge);
            }
        }
    }

    public int getNumberOfEdges() {
        return edgesNum;
    }

    public int getNumberOfVertices() {
        return verticesNum;
    }

    public void addEdge(Edge edge) {
        int edgeSource = edge.getSource();
        int edgeDestination = edge.getDestination();
        adjacencyLists[edgeSource].add(edge);
        adjacencyLists[edgeDestination].add(edge);
        edgesNum++;
    }
}
