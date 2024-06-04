import java.util.LinkedList;
import java.util.Queue;

public class SplayTree {
    private Node root;

    private long comparisonCount;
    private long readCount;
    private long assignmentCount;

    public SplayTree() {
        root = null;
    }

    public void add(int value) {
        Node addedNode = new Node(value);
        add(addedNode);
    }

    public void add(Node z) {
        Node x = root;
        Node y = null;

        while (x != null) {
            comparisonCount++;
            y = x;
            assignmentCount++;
            if (x.value < z.value) {
                comparisonCount++;
                x = x.right;
                readCount++;
            } else {
                comparisonCount++;
                x = x.left;
                readCount++;
            }
        }

        x = z;
        x.parent = y;
        assignmentCount += 2;
        if (y == null) {
            comparisonCount++;
            root = x;
            assignmentCount++;
        } else if (y.value < x.value) {
            comparisonCount += 2;
            y.right = x;
            assignmentCount++;
        } else {
            comparisonCount += 2;
            y.left = x;
            assignmentCount++;
        }

        splay(x);
    }

    public void delete(int value) {
        Node nodeToDelete = findNode(root, value);
        if (nodeToDelete != null) {
            delete(nodeToDelete);
        }
    }

    public void delete(Node z) {
        if (z == null) {
            comparisonCount++;
            return;
        }
        splay(z);

        if (z.left == null) {
            comparisonCount++;
            transplant(z, z.right);
        } else if (z.right == null) {
            comparisonCount += 2;
            transplant(z, z.left);
        } else {
            comparisonCount += 2;
            Node y = treeMinimum(z.right);
            if (y.parent != z) {
                comparisonCount++;
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
        while (current != null && current.value != value) {
            comparisonCount += 2;
            if (value < current.value) {
                comparisonCount++;
                current = current.left;
                readCount++;
            } else {
                comparisonCount++;
                current = current.right;
                readCount++;
            }
        }
        comparisonCount++;
        return current;
    }

    private void transplant(Node u, Node v) {
        if (u.parent == null) {
            comparisonCount++;
            root = v;
            assignmentCount++;
        } else if (u == u.parent.left) {
            comparisonCount += 2;
            u.parent.left = v;
            assignmentCount++;
        } else {
            comparisonCount += 2;
            u.parent.right = v;
            assignmentCount++;
        }
        if (v != null) {
            comparisonCount++;
            v.parent = u.parent;
            assignmentCount++;
        }
    }

    private Node treeMinimum(Node x) {
        while (x.left != null) {
            comparisonCount++;
            x = x.left;
            readCount++;
        }
        comparisonCount++;
        return x;
    }

    private void leftRotate(Node x) {
        Node y = x.right;
        readCount++;
        if (y != null) {
            comparisonCount++;
            x.right = y.left;
            assignmentCount++;
            if (y.left != null) {
                comparisonCount++;
                y.left.parent = x;
                assignmentCount++;
            }
            y.parent = x.parent;
            assignmentCount++;
        }
        if (x.parent == null) {
            comparisonCount++;
            root = y;
            assignmentCount++;
        } else if (x == x.parent.left) {
            comparisonCount += 2;
            x.parent.left = y;
            assignmentCount++;
        } else {
            comparisonCount += 2;
            x.parent.right = y;
            assignmentCount++;
        }
        if (y != null) {
            comparisonCount++;
            y.left = x;
            x.parent = y;
            assignmentCount += 2;
        }
    }

    private void rightRotate(Node x) {
        Node y = x.left;
        readCount++;
        if (y != null) {
            comparisonCount++;
            x.left = y.right;
            assignmentCount++;
            if (y.right != null) {
                comparisonCount++;
                y.right.parent = x;
                assignmentCount++;
            }
            y.parent = x.parent;
            assignmentCount++;
        }
        if (x.parent == null) {
            comparisonCount++;
            root = y;
            assignmentCount++;
        } else if (x == x.parent.left) {
            comparisonCount += 2;
            x.parent.left = y;
            assignmentCount++;
        } else {
            comparisonCount += 2;
            x.parent.right = y;
            assignmentCount++;
        }
        if (y != null) {
            comparisonCount++;
            y.right = x;
            x.parent = y;
            assignmentCount += 2;
        }
    }

    private void splay(Node x) {
        while (x.parent != null) {
            comparisonCount++;
            if (x.parent.parent == null) {
                comparisonCount++;
                if (x.parent.left == x) {
                    comparisonCount++;
                    rightRotate(x.parent);
                } else {
                    comparisonCount++;
                    leftRotate(x.parent);
                }
            } else if (x.parent.left == x && x.parent.parent.left == x.parent) {
                comparisonCount += 3;
                rightRotate(x.parent.parent);
                rightRotate(x.parent);
            } else if (x.parent.right == x && x.parent.parent.right == x.parent) {
                comparisonCount += 5;
                leftRotate(x.parent.parent);
                leftRotate(x.parent);
            } else if (x.parent.left == x && x.parent.parent.right == x.parent) {
                comparisonCount += 7;
                rightRotate(x.parent);
                leftRotate(x.parent);
            } else {
                comparisonCount += 7;
                leftRotate(x.parent);
                rightRotate(x.parent);
            }
        }
        comparisonCount++;
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

    public int height() {
        if (root == null) {
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

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
        }

        return height;
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
