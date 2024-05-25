public class Node {
    public int value;
    public Node left;
    public Node right;
    public Node parent;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}
