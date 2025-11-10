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

// Node class representing a single node in the AVL Tree
class Node {
    int key;               // Value stored in the node
    int height;            // Height of the node
    Node left, right;      // References to left and right child nodes

    Node(int d) {          // Constructor to initialize the node
        key = d;
        height = 1;        // Every new node starts with height = 1
    }
}

// Main AVL Tree class
class AVLTree {

    // Helper method to get height of a node
    int height(Node N) {
        if (N == null) return 0;
        return N.height;
    }

    // Helper method to get balance factor of a node
    int getBalance(Node N) {
        if (N == null) return 0;
        return height(N.left) - height(N.right);
    }

    // Right Rotation (LL Rotation)
    Node rightRotate(Node y) {
        Node x = y.left;     // x becomes new root of this subtree
        Node T2 = x.right;   // Temporarily store right subtree of x

        x.right = y;         // Perform rotation
        y.left = T2;

        // Update heights of y and x
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;            // Return new root after rotation
    }

    // Left Rotation (RR Rotation)
    Node leftRotate(Node x) {
        Node y = x.right;    // y becomes new root of this subtree
        Node T2 = y.left;    // Temporarily store left subtree of y

        y.left = x;          // Perform rotation
        x.right = T2;

        // Update heights of x and y
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;            // Return new root after rotation
    }

    // Insertion method with balancing
    Node insert(Node node, int key) {
        // 1. Perform normal BST insertion
        if (node == null)
            return (new Node(key));

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node; // Duplicates not allowed

        // 2. Update the height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 3. Get the balance factor
        int balance = getBalance(node);

        // 4. Check balance and perform rotations if needed

        // Case 1: Left Left (LL)
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Case 2: Right Right (RR)
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Case 3: Left Right (LR)
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Case 4: Right Left (RL)
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return unchanged node if balanced
        return node;
    }

    // Inorder traversal prints sorted keys
    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }
}

// --------------------------- DRIVER CLASS --------------------------- //
public class Main {
    public static void main(String[] args) throws IOException {

        // Creating object for AVLTree
        AVLTree tree = new AVLTree();
        Node root = null;

        // Insert elements into AVL Tree
        int[] nums = {10, 20, 30, 40, 50, 25};

        // Insert all elements one by one
        for (int num : nums)
            root = tree.insert(root, num);

        // Print inorder traversal (should be sorted)
        System.out.println("Inorder Traversal of the constructed AVL Tree is:");
        tree.inorder(root);
        System.out.println();
    }
}
