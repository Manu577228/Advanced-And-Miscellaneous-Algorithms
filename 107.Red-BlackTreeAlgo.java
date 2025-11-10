/* ----------------------------------------------------------------------------  */
/*   ( The Authentic JS/JAVA CodeBuff )
 ___ _                      _              _ 
 | _ ) |_  __ _ _ _ __ _ __| |_ __ ____ _ (_)
 | _ \ ' \/ _` | '_/ _` / _` \ V  V / _` || |
 |___/_||_\__,_|_| \__,_\__,_|\_/\_/\__,_|/ |
                                        |__/ 
 */
/* --------------------------------------------------------------------------   */
/*    Youtube: https://youtube.com/@code-with-Bharadwaj                        */
/*    Github : https://github.com/Manu577228                                  */
/*    Portfolio : https://manu-bharadwaj-portfolio.vercel.app/portfolio       */
/* -----------------------------------------------------------------------  */

import java.io.*;

// Step 1: Define color constants
class Color {
    public static final String RED = "RED";
    public static final String BLACK = "BLACK";
}

// Step 2: Define a Node class
class Node {
    int data;               // Node value
    String color;           // RED or BLACK
    Node left, right, parent;

    Node(int data) {
        this.data = data;
        this.color = Color.RED;  // New nodes are always RED initially
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}

// Step 3: Define the Red-Black Tree class
class RedBlackTree {
    private Node root;
    private Node NIL;   // Sentinel NIL node for leaves

    // Constructor
    public RedBlackTree() {
        NIL = new Node(0);
        NIL.color = Color.BLACK;
        root = NIL;
    }

    // Left rotation around node x
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != NIL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    // Right rotation around node y
    private void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        if (x.right != NIL) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == null) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }
        x.right = y;
        y.parent = x;
    }

    // Fix the tree to maintain red-black properties after insertion
    private void fixInsert(Node k) {
        while (k.parent != null && k.parent.color.equals(Color.RED)) {
            if (k.parent == k.parent.parent.left) {
                Node u = k.parent.parent.right; // Uncle
                if (u.color.equals(Color.RED)) {  // Case 1: Uncle is RED
                    k.parent.color = Color.BLACK;
                    u.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {    // Case 2: Node is right child
                        k = k.parent;
                        leftRotate(k);
                    }
                    // Case 3: Node is left child
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    rightRotate(k.parent.parent);
                }
            } else {
                Node u = k.parent.parent.left;
                if (u.color.equals(Color.RED)) {
                    k.parent.color = Color.BLACK;
                    u.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    leftRotate(k.parent.parent);
                }
            }
        }
        root.color = Color.BLACK; // Root must always be black
    }

    // Insert a new node into the Red-Black Tree
    public void insert(int key) {
        Node node = new Node(key);
        node.left = NIL;
        node.right = NIL;

        Node y = null;
        Node x = root;

        while (x != NIL) {
            y = x;
            if (node.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data < y.data) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = Color.BLACK;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    // Inorder traversal to verify structure and colors
    public void inorder(Node node) {
        if (node != NIL) {
            inorder(node.left);
            System.out.print(node.data + "(" + node.color + ") ");
            inorder(node.right);
        }
    }

    public Node getRoot() {
        return this.root;
    }
}

// Step 4: Driver class
public class Main {
    public static void main(String[] args) throws IOException {
        RedBlackTree rbt = new RedBlackTree();

        int[] elements = {10, 20, 30, 15, 25};
        for (int e : elements) {
            rbt.insert(e);
        }

        System.out.println("Inorder Traversal (value(color)):");
        rbt.inorder(rbt.getRoot());
    }
}
