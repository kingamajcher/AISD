import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoundMinimizer {

    public static int minimizeRounds(int[][] adjMatrix, int n, int root) {
        Node[] nodes = new Node[n];

        // adding nodes to node array
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        // adding children to each node
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjMatrix[i][j] == 1) {
                    nodes[i].addChild(nodes[j]);
                }
            }
        }

        // setting parents for each node, root's parent is null
        setParentChildRelations(nodes, root, null);

        // computing maximum number of rounds needed to propagate information starting from the root node
        int maxRound = computePropagationOrder(nodes[root], null);

        return maxRound;
    }

    private static void setParentChildRelations(Node[] nodes, int current, Node parent) {
        nodes[current].setParent(parent);

        for (Node child : nodes[current].getChildren()) {
            if (child != parent) {
                setParentChildRelations(nodes, child.getId(), nodes[current]);
            }
        }
    }

    private static int computePropagationOrder(Node node, Node parent) {
        // node has no children - no further propagation is needed, returns 0
        if (node.getChildren().size() == 0 || (node.getChildren().size() == 1 && node.getChildren().get(0) == parent)) {
            return 0;
        }

        List<Pair<Integer, Node>> childrenRounds = new ArrayList<>();

        // recursively calculating how much round needed for each child
        for (Node child : node.getChildren()) {
            if (child != parent) {
                int childRound = computePropagationOrder(child, node);
                childrenRounds.add(new Pair<>(childRound, child));
            }
        }
        
        // sorting children and their respective rounds in descending order, so the ones requiring the most round starts first
        Collections.sort(childrenRounds, (a, b) -> b.getKey() - a.getKey());

        // computing maximum round required 
        int maxRound = 0;
        int currentRound = 1;

        System.out.println("Node's " + node.getId() + " order of informing children:");

        for (Pair<Integer, Node> pair : childrenRounds) {
            Node childNode = pair.getValue();
            System.out.println("Node " + childNode.getId());
            int childRound = pair.getKey();
            maxRound = Math.max(maxRound, currentRound + childRound);
            currentRound++;
        }
        
        System.out.println();

        return maxRound;
    }

    private static class Node {
        private int id;
        private List<Node> children;
        private Node parent;

        public Node(int id) {
            this.id = id;
            this.children = new ArrayList<>();
        }

        public int getId() {
            return id;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void addChild(Node child) {
            children.add(child);
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getParent() {
            return parent;
        }
    }

    private static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
