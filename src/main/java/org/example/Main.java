package org.example;

// create a class for a red black tree
class RedBlackTree {
    public Node root;

    public Node getRoot() {
        return this.root;
    }

    static class Node {
        int value;
        Node left;
        Node right;
        boolean isRed;

        Node(int value) {
            this.value = value;
            this.isRed = true;
        }
    }

    public void insert(int value) {
        root = insert(root, value);
        root.isRed = false;
    }

    private Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.isRed;
    }

    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.isRed = node.isRed;
        node.isRed = true;
        return x;
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.isRed = node.isRed;
        node.isRed = true;
        return x;
    }

    private void flipColors(Node node) {
        node.isRed = !node.isRed;
        if (node.left != null) node.left.isRed = !node.left.isRed;
        if (node.right != null) node.right.isRed = !node.right.isRed;
    }

    // create inorder traversal
    public void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.value + " ");
            printInOrder(node.right);
        }
    }

    // create posorder traversal
    public void printPostOrder(Node node) {
        if (node != null) {
            printPostOrder(node.left);
            printPostOrder(node.right);
            System.out.print(node.value + " ");
        }
    }

    // create preorder traversal
    public void printPreOrder(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }

    public void printTree() {
        printTree(this.root, "", true);
    }

    private void printTree(Node node, String prefix, boolean isTail) {
        if (node == null) {
            return;
        }

        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.value + (node.isRed ? "(R)" : "(B)"));

        if (node.left != null || node.right != null) {
            if (node.left != null) {
                printTree(node.right, prefix + (isTail ? "    " : "│   "), false);
                printTree(node.left, prefix + (isTail ? "    " : "│   "), true);
            } else {
                printTree(node.right, prefix + (isTail ? "    " : "│   "), true);
            }
        }
    }

    public void delete(int key) {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.isRed = true;
        }
        root = delete(root, key);
        if (root != null) root.isRed = false;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node delete(Node node, int key) {
        if (key < node.value) {
            if (!isRed(node.left) && !isRed(node.left != null ? node.left.left : null)) {
                node = moveRedLeft(node);
            }
            node.left = delete(node.left, key);
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
            }
            if (key == node.value && (node.right == null)) {
                return null;
            }
            if (!isRed(node.right) && !isRed(node.right.left)) {
                node = moveRedRight(node);
            }
            if (key == node.value) {
                Node min = findMin(node.right);
                node.value = min.value;
                node.right = deleteMin(node.right);
            } else {
                node.right = delete(node.right, key);
            }
        }
        return fixUp(node);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return null;
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left);
        return fixUp(node);
    }

    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private Node fixUp(Node node) {
        if (isRed(node.right)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);
        return node;
    }
}

public class Main {
    public static void main(String[] args) {
        // create a red black tree
        RedBlackTree tree = new RedBlackTree();

        // insert some values
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);

        // print the tree in order
        System.out.print("In Order: ");
        tree.printInOrder(tree.getRoot());
        System.out.println();

        // print the tree in preorder
        System.out.print("Pre Order: ");
        tree.printPreOrder(tree.getRoot());
        System.out.println();

        // print the tree in postorder
        System.out.print("Post Order: ");
        tree.printPostOrder(tree.getRoot());
        System.out.println();

        tree.printTree();

        tree.delete(20);
        tree.delete(40);

        tree.printTree();
    }
}