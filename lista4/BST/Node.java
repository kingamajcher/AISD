public class Node {
    public int value;
    public Node left;
    public Node right;
    public Node parent;

    public Node(int value) {
        this.value = value;
        left = null;
        right = null;
        parent = null;
    }
}
