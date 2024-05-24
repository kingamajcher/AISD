import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    private Node root;
    private final Node nil;

    private long comparisonCount;
    private long readCount;
    private long assignmentCount;

    public BinarySearchTree() {
        nil = new Node(Integer.MIN_VALUE);
        nil.left = nil.right = nil.parent = nil;
        root = nil;
    }

    public void add(int value) {
        Node addedNode = new Node(value);
        add(addedNode);
    }

    private void add(Node z) {
        Node y = nil;
        Node x = root;

        while (x != nil) {
            comparisonCount++;
            y = x;
            if (z.value < x.value) {
                comparisonCount++;
                x = x.left;
                readCount++;
            }
            else {
                comparisonCount++;
                x = x.right;
                readCount++;
            }
        }

        z.parent = y;
        assignmentCount++;
        if (y == nil) {
            root = z;
            assignmentCount++;
        }
        else if (z.value < y.value) {
            comparisonCount++;
            y.left = z;
            assignmentCount++;
        }
        else {
            comparisonCount++;
            y.right = z;
            assignmentCount++;
        }
        z.left = z.right = nil;
    }

    public void delete(int value) {
        Node nodeToDelete = findNode(root, value);
        if (nodeToDelete != nil) {
            delete(nodeToDelete);
        }
    }

    private void delete(Node z) {
        if (z.left == nil) {
            transplant(z, z.right);
        }
        else if (z.right == nil) {
            transplant(z, z.left);
        }
        else {
            Node y = treeMinimum(z.right);
            if (y.parent != z) {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
                assignmentCount += 2;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            assignmentCount += 2;
        }
    }

    private Node findNode(Node root, int value) {
        Node current = root;
        while (current != nil && current.value != value) {
            comparisonCount += 2;
            if (value < current.value) {
                current = current.left;
                readCount++;
            }
            else {
                current = current.right;
                readCount++;
            }
        }
        return current;
    }

    private void transplant(Node u, Node v) {
        if (u.parent == nil) {
            root = v;
            assignmentCount++;
        }
        else if (u == u.parent.left) {
            u.parent.left = v;
            assignmentCount++;
        }
        else {
            u.parent.right = v;
            assignmentCount++;
        }
        if (v != nil) {
            v.parent = u.parent;
            assignmentCount++;
        }
    }

    private Node treeMinimum(Node x) {
        while (x.left != nil) {
            x = x.left;
            readCount++;
        }
        return x;
    }

    public int height() {
        if (root == nil) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int height = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++;

            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();

                if (currentNode.left != nil) {
                    queue.add(currentNode.left);
                    readCount++;
                }

                if (currentNode.right != nil) {
                    queue.add(currentNode.right);
                    readCount++;
                }
            }
        }
        return height;
    }

    public void printTree() {
        int height = height();
        char[] leftTrace = new char[height];
        char[] rightTrace = new char[height];
        printTreeRecursive(root, 0, '-', leftTrace, rightTrace);
    }

    private void printTreeRecursive(Node node, int depth, char prefix, char[] leftTrace, char[] rightTrace) {
        if (node == nil) {
            return;
        }
        if (node.left != nil) {
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
            }
            else {
                System.out.print("  ");
            }
        }
        if (depth > 0) {
            System.out.print(prefix + "-");
        }
        System.out.println("[" + node.value + "]");
        leftTrace[depth] = ' ';
        if (node.right != nil) {
            rightTrace[depth] = '|';
            printTreeRecursive(node.right, depth + 1, '\\', leftTrace, rightTrace);
        }
    }

    public void resetCounters() {
        comparisonCount = 0;
        readCount = 0;
        assignmentCount = 0;
    }

    public long getComparisons() {
        return comparisonCount;
    }

    public long getReads() {
        return readCount;
    }

    public long getAssignments() {
        return assignmentCount;
    }
}
