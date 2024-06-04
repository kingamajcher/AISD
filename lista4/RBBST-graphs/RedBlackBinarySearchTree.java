import java.util.LinkedList;
import java.util.Queue;

public class RedBlackBinarySearchTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    private Node nil;

    private long comparisonCount;
    private long readCount;
    private long assignmentCount;

    public RedBlackBinarySearchTree() {
        nil = new Node(Integer.MIN_VALUE);
        nil.color = BLACK;
        nil.left = nil.right = nil.parent = nil;
        root = nil;
    }

    public void add(int value) {
        Node addedNode = new Node(value);
        addedNode.left = addedNode.right = addedNode.parent = nil;
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
            } else {
                comparisonCount++;
                x = x.right;
                readCount++;
            }
        }
        comparisonCount++;

        z.parent = y;
        assignmentCount++;
        if (y == nil) {
            comparisonCount++;
            root = z;
            assignmentCount++;
        } else if (z.value < y.value) {
            comparisonCount += 2;
            y.left = z;
            assignmentCount++;
        } else {
            comparisonCount += 2;
            y.right = z;
            assignmentCount++;
        }
        z.left = nil;
        z.right = nil;
        z.color = RED;
        fixup(z);
    }

    public void delete(int value) {
        Node nodeToDelete = findNode(root, value);
        if (nodeToDelete != nil) {
            delete(nodeToDelete);
        }
    }

    private void delete(Node z) {
        Node y = z;
        boolean yOriginalColor = y.color;
        Node x;
        readCount++;

        if (z.left == nil) {
            comparisonCount++;
            x =z.right;
            transplant(z, z.right);
            readCount++;
        } else if (z.right == nil) {
            comparisonCount += 2;
            x = z.left;
            transplant(z, z.left);
            readCount++;
        } else {
            comparisonCount += 2;
            y = treeMinimum(z.right);
            yOriginalColor = y.color;
            readCount++;
            x = y.right;
            readCount++;
            if (y.parent == z) {
                comparisonCount++;
                x.parent = y;
                assignmentCount++;
            } else {
                comparisonCount++;
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
                assignmentCount += 2;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
            assignmentCount += 3;
        }
        if (yOriginalColor == BLACK) {
            deleteFixup(x);
        }
    }

    private void deleteFixup(Node x) {
        while (x != root && x.color == BLACK) {
            comparisonCount += 2;
            if (x == x.parent.left) {
                comparisonCount++;
                Node w = x.parent.right;
                readCount++;
                if (w.color == RED) {
                    comparisonCount++;
                    w.color = BLACK;
                    x.parent.color = RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                    assignmentCount += 2;
                    readCount++;
                }
                if (w.left.color == BLACK && w.right.color == BLACK) {
                    comparisonCount += 2;
                    w.color = RED;
                    x = x.parent;
                    assignmentCount++;
                    readCount++;
                } else {
                    comparisonCount += 2;
                    if (w.right.color == BLACK) {
                        comparisonCount++;
                        w.left.color = BLACK;
                        w.color = RED;
                        rightRotate(w);
                        w = x.parent.right;
                        assignmentCount += 2;
                        readCount++;
                    }
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    leftRotate(x.parent);
                    x = root;
                    assignmentCount += 4;
                }
            } else {
                comparisonCount++;
                Node w = x.parent.left;
                readCount++;
                if (w.color == RED) {
                    comparisonCount++;
                    w.color = BLACK;
                    x.parent.color = RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                    assignmentCount += 2;
                    readCount++;
                }
                if (w.right.color == BLACK && w.left.color == BLACK) {
                    comparisonCount += 2;
                    w.color = RED;
                    x = x.parent;
                    assignmentCount++;
                    readCount++;
                } else {
                    comparisonCount += 2;
                    if (w.left.color == BLACK) {
                        comparisonCount++;
                        w.right.color = BLACK;
                        w.color = RED;
                        leftRotate(w);
                        w = x.parent.left;
                        assignmentCount += 2;
                        readCount++;
                    }
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rightRotate(x.parent);
                    x = root;
                    assignmentCount += 4;
                }
            }
        }
        comparisonCount++;
        x.color = BLACK;
        assignmentCount++;
    }

    private Node findNode(Node root, int value) {
        Node current = root;
        while (current != nil && current.value != value) {
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
        if (u.parent == nil) {
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
        v.parent = u.parent;
        assignmentCount++;
    }

    private Node treeMinimum(Node x) {
        while (x.left != nil) {
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
        x.right = y.left;
        assignmentCount++;
        if (y.left != nil) {
            comparisonCount++;
            y.left.parent = x;
            assignmentCount++;
        }
        y.parent = x.parent;
        assignmentCount++;
        if (x.parent == nil) {
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
        y.left = x;
        x.parent = y;
        assignmentCount += 2;
    }

    private void rightRotate(Node x) {
        Node y = x.left;
        readCount++;
        x.left = y.right;
        assignmentCount++;
        if (y.right != nil) {
            comparisonCount++;
            y.right.parent = x;
            assignmentCount++;
        }
        y.parent = x.parent;
        assignmentCount++;
        if (x.parent == nil) {
            comparisonCount++;
            root = y;
            assignmentCount++;
        } else if (x == x.parent.right) {
            comparisonCount += 2;
            x.parent.right = y;
            assignmentCount++;
        } else {
            comparisonCount += 2;
            x.parent.left = y;
            assignmentCount++;
        }
        y.right = x;
        x.parent = y;
        assignmentCount += 2;
    }

    private void fixup(Node z) {
        while (z.parent.color == RED) {
            comparisonCount++;
            if (z.parent == z.parent.parent.left) {
                comparisonCount++;
                Node y = z.parent.parent.right;
                readCount++;
                if (y.color == RED) {
                    comparisonCount++;
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                    assignmentCount += 3;
                    readCount++;
                } else {
                    comparisonCount++;
                    if (z == z.parent.right) {
                        comparisonCount++;
                        z = z.parent;
                        leftRotate(z);
                        readCount++;
                    }
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    rightRotate(z.parent.parent);
                    assignmentCount += 2;
                }
            } else {
                comparisonCount++;
                Node y = z.parent.parent.left;
                readCount++;
                if (y.color == RED) {
                    comparisonCount++;
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                    assignmentCount += 3;
                    readCount++;
                } else {
                    comparisonCount++;
                    if (z == z.parent.left) {
                        comparisonCount++;
                        z = z.parent;
                        rightRotate(z);
                        readCount++;
                    }
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    leftRotate(z.parent.parent);
                    assignmentCount += 2;
                }
            }
        }
        comparisonCount++;
        root.color = BLACK;
        assignmentCount++;
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
                }

                if (currentNode.right != nil) {
                    queue.add(currentNode.right);
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
            } else {
                System.out.print("  ");
            }
        }
        if (depth > 0) {
            System.out.print(prefix + "-");
        }
        if (node.color == RED) {
            System.out.println("\033[31m[" + node.value + "]\033[0m");
        } else {
            System.out.println("[" + node.value + "]");
        }
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
