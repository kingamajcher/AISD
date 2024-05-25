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
            y = x;
            comparisonCount++;
            if (x.value < z.value) {
                x = x.right;
                readCount++;
            } else {
                x = x.left;
                readCount++;
            }
        }

        x = z;
        x.parent = y;
        assignmentCount += 2;
        if (y == null) {
            root = x;
            assignmentCount++;
        } else if (y.value < x.value) {
            y.right = x;
            assignmentCount++;
        } else {
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
            return;
        }
        splay(z);

        if (z.left == null) {
            transplant(z, z.right);
        } else if (z.right == null) {
            transplant(z, z.left);
        } else {
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
        while (current != null && current.value != value) {
            comparisonCount += 2;
            if (value < current.value) {
                current = current.left;
                readCount++;
            } else {
                current = current.right;
                readCount++;
            }
        }
        return current;
    }

    private void transplant(Node u, Node v) {
        if (u.parent == null) {
            root = v;
            assignmentCount++;
        } else if (u == u.parent.left) {
            u.parent.left = v;
            assignmentCount++;
        } else {
            u.parent.right = v;
            assignmentCount++;
        }
        if (v != null) {
            v.parent = u.parent;
            assignmentCount++;
        }
    }

    private Node treeMinimum(Node x) {
        while (x.left != null) {
            x = x.left;
            readCount++;
        }
        return x;
    }

    private void leftRotate(Node x) {
        Node y = x.right;
        readCount++;
        if (y != null) {
            x.right = y.left;
            assignmentCount++;
            if (y.left != null) {
                y.left.parent = x;
                assignmentCount++;
            }
            y.parent = x.parent;
            assignmentCount++;
        }
        if (x.parent == null) {
            root = y;
            assignmentCount++;
        } else if (x == x.parent.left) {
            x.parent.left = y;
            assignmentCount++;
        } else {
            x.parent.right = y;
            assignmentCount++;
        }
        if (y != null) {
            y.left = x;
            x.parent = y;
            assignmentCount += 2;
        }
    }

    private void rightRotate(Node x) {
        Node y = x.left;
        readCount++;
        if (y != null) {
            x.left = y.right;
            assignmentCount++;
            if (y.right != null) {
                y.right.parent = x;
                assignmentCount++;
            }
            y.parent = x.parent;
            assignmentCount++;
        }
        if (x.parent == null) {
            root = y;
            assignmentCount++;
        } else if (x == x.parent.left) {
            x.parent.left = y;
            assignmentCount++;
        } else {
            x.parent.right = y;
            assignmentCount++;
        }
        if (y != null) {
            y.right = x;
            x.parent = y;
            assignmentCount += 2;
        }
    }

    private void splay(Node x) {
        while (x.parent != null) {
            comparisonCount++;
            if (x.parent.parent == null) {
                if (x.parent.left == x) {
                    rightRotate(x.parent);
                } else {
                    leftRotate(x.parent);
                }
            } else if (x.parent.left == x && x.parent.parent.left == x.parent) {
                rightRotate(x.parent.parent);
                rightRotate(x.parent);
            } else if (x.parent.right == x && x.parent.parent.right == x.parent) {
                leftRotate(x.parent.parent);
                leftRotate(x.parent);
            } else if (x.parent.left == x && x.parent.parent.right == x.parent) {
                rightRotate(x.parent);
                leftRotate(x.parent);
            } else {
                leftRotate(x.parent);
                rightRotate(x.parent);
            }
        }
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
                readCount++;

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                    readCount++;
                }

                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                    readCount++;
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
