public class Edge implements Comparable<Edge> {
    private final int source, destination;
    private final double weight;

    public Edge(int source, int destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }

    public int getOppositeVertex(int vertex) {
        if (vertex == source) {
            return destination;
        } else if (vertex == destination) {
            return source;
        } else {
            throw new IllegalArgumentException("Invalid vertex");
        }
    }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d, %.2f)", source, destination, weight);
    }
}