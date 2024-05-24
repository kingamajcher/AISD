public class RedBlackBinarySearchTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    private Node nil;

    public RedBlackBinarySearchTree() {
        nil = new Node(0);
        nil.color = BLACK;
        nil.left = nil.right = nil.parent = nil;
        root = nil;
    }

    public void add(int value) {
        Node addedNode = new Node(value);
        addedNode.left = addedNode.right = addedNode.parent = nil;
        add(addedNode);
        System.out.println(value + " added to tree");
    }

    private void add(Node z) {
        Node y = nil;
        Node x = root;

        while (x != nil) {
            y = x;
            if (z.value < x.value) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        z.parent = y;
        if (y == nil) {
            root = z;
        } else if (z.value < y.value) {
            y.left = z;
        } else {
            y.right = z;
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
            System.out.println(value + " deleted from tree");
        } else {
            System.out.println(value + " not found in tree");
        }
    }

    private void delete(Node z) {
        Node y = z;
        boolean yOriginalColor = y.color;
        Node x;

        if (z.left == nil) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == nil) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = treeMinimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == BLACK) {
            deleteFixup(x);
        }
    }

    private void deleteFixup(Node x) {
        while (x != root && x.color == BLACK) {
            if (x == x.parent.left) {
                Node w = x.parent.right;
                if (w.color == RED) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == BLACK && w.right.color == BLACK) {
                    w.color = RED;
                    x = x.parent;
                } else {
                    if (w.right.color == BLACK) {
                        w.left.color = BLACK;
                        w.color = RED;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                Node w = x.parent.left;
                if (w.color == RED) {
                    w.color = BLACK;
                    x.parent.color = RED;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if (w.right.color == BLACK && w.left.color == BLACK) {
                    w.color = RED;
                    x = x.parent;
                } else {
                    if (w.left.color == BLACK) {
                        w.right.color = BLACK;
                        w.color = RED;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = BLACK;
    }

    private Node findNode(Node root, int value) {
        Node current = root;
        while (current != nil && current.value != value) {
            if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current;
    }

    private void transplant(Node u, Node v) {
        if (u.parent == nil) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private Node treeMinimum(Node x) {
        while (x.left != nil) {
            x = x.left;
        }
        return x;
    }

    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != nil) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == nil) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != nil) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == nil) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    private void fixup(Node z) {
        while (z.parent.color == RED) {
            if (z.parent == z.parent.parent.left) {
                Node y = z.parent.parent.right;
                if (y.color == RED) {
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    rightRotate(z.parent.parent);
                }
            } else {
                Node y = z.parent.parent.left;
                if (y.color == RED) {
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.color = BLACK;
    }

    public int height() {
        return heightRecursive(root);
    }

    private int heightRecursive(Node current) {
        if (current == nil) {
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
}
