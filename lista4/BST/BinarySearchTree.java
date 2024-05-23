public class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
    }

    public void add(int value) {
        Node addedNode = new Node(value);
        add(addedNode);
        System.out.println(value + " added to tree");
    }

    private void add(Node z) {
        Node y = null;
        Node x = root;

        while (x != null) {
            y = x;
            if (z.value < x.value) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        z.parent = y;
        if (y == null) {
            root = z;
        } else if (z.value < y.value) {
            y.left = z;
        } else {
            y.right = z;
        }
    }

    public void delete(int value) {
        Node nodeToDelete = findNode(root, value);
        if (nodeToDelete != null) {
            delete(nodeToDelete);
            System.out.println(value + " deleted from tree");
        } else {
            System.out.println(value + " not found in tree");
        }
    }

    private void delete(Node z) {
        if (z.left == null) {
            transplant(z, z.right);
        }
        else if (z.right == null) {
            transplant(z, z.left);
        }
        else {
            Node y = treeMinimum(z.right);
            if (y.parent != z) {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
        }
    }

    private Node findNode(Node root, int value) {
        Node current = root;
        while (current != null && current.value != value) {
            if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current;
    }

    private void transplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        }
        else if (u == u.parent.left) {
            u.parent.left = v;
        }
        else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    private Node treeMinimum(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public int height() {
        return heightRecursive(root);
    }

    private int heightRecursive(Node current) {
        if (current == null) {
            return 0;
        }

        int leftHeight = heightRecursive(current.left);
        int rightHeight = heightRecursive(current.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public void printTree() {
        int height = height();
        char[] leftTrace = new char[height];
        char[] rightTrace = new char[height];
        printTreeRecursive(root, 0, '-', leftTrace, rightTrace);
    }

    private void printTreeRecursive(Node node, int depth, char prefix, char[] leftTrace, char[] rightTrace) {
        if (node == null) {
            return;
        }

        if (node.left != null) {
            printTreeRecursive(node.left, depth + 1, '/', leftTrace, rightTrace);
        }

        if (prefix == '/') {
            leftTrace[depth - 1] = '|';
        }
        if (prefix == '\\') {
            rightTrace[depth - 1] = ' ';
        }
        if (depth == 0) {
            System.out.print("-");
        }
        if (depth > 0) {
            System.out.print(" ");
        }
        for (int i = 0; i < depth - 1; i++) {
            if (leftTrace[i] == '|' || rightTrace[i] == '|') {
                System.out.print("| ");
            } else {
                System.out.print("  ");
            }
        }
        if (depth > 0) {
            System.out.print(prefix + "-");
        }
        System.out.println("[" + node.value + "]");
        leftTrace[depth] = ' ';
        if (node.right != null) {
            rightTrace[depth] = '|';
            printTreeRecursive(node.right, depth + 1, '\\', leftTrace, rightTrace);
        }
    }
}
