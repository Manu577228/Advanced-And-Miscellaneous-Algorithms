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
/*    Portfolio : https://manu-bharadwaj-portfolio.vercel.app/portfolio        */
/* -----------------------------------------------------------------------  */

import java.util.*;

// Node structure for the Treap
class Node {
    int key;           // key maintains BST property
    int priority;      // priority maintains Heap property
    Node left, right;  // left and right child pointers

    Node(int key) {
        this.key = key;
        this.priority = new Random().nextInt(100); // assign random priority
        this.left = null;
        this.right = null;
    }
}

public class TreapExample {

    // Right rotation to fix heap property violation
    static Node rotateRight(Node y) {
        Node x = y.left;          // store left child
        Node T = x.right;         // store right subtree of x
        x.right = y;              // perform rotation
        y.left = T;               // attach T as left subtree of y
        return x;                 // new root after rotation
    }

    // Left rotation to fix heap property violation
    static Node rotateLeft(Node x) {
        Node y = x.right;         // store right child
        Node T = y.left;          // store left subtree of y
        y.left = x;               // perform rotation
        x.right = T;              // attach T as right subtree of x
        return y;                 // new root after rotation
    }

    // Insert a key into the Treap
    static Node insert(Node root, int key) {
        // base case: if tree is empty, return new node
        if (root == null)
            return new Node(key);

        // insert key in BST manner
        if (key < root.key) {
            root.left = insert(root.left, key);

            // fix heap property if violated
            if (root.left.priority > root.priority)
                root = rotateRight(root);
        } 
        else if (key > root.key) {
            root.right = insert(root.right, key);

            if (root.right.priority > root.priority)
                root = rotateLeft(root);
        }

        // return the unchanged or rotated root
        return root;
    }

    // Search for a key in Treap (like normal BST)
    static boolean search(Node root, int key) {
        if (root == null)
            return false;
        if (root.key == key)
            return true;
        else if (key < root.key)
            return search(root.left, key);
        else
            return search(root.right, key);
    }

    // Inorder traversal to display the Treap
    static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print("(Key=" + root.key + ", Priority=" + root.priority + ") ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        Node root = null; // start with empty Treap

        // insert elements into the Treap
        int[] keys = {50, 30, 20, 40, 70, 60, 80};
        for (int k : keys)
            root = insert(root, k);

        // print Treap inorder (shows BST property)
        System.out.println("Inorder traversal of Treap (Key, Priority):");
        inorder(root);

        // search operations
        System.out.println("\nSearch for 40: " + search(root, 40));  // expected true
        System.out.println("Search for 90: " + search(root, 90));    // expected false
    }
}
