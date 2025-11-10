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

import java.io.*;
import java.util.*;

public class KDTreeExample {

    // Step 1: Define a class to represent each node of the K-D Tree
    static class Node {
        int[] point;     // The point in k-dimensional space
        Node left, right; // Left and right child references

        Node(int[] point) {
            this.point = point;
            this.left = null;
            this.right = null;
        }
    }

    // Step 2: Recursive function to build the K-D Tree
    static Node buildKDTree(List<int[]> points, int depth, int k) {
        // Base case: when there are no more points left
        if (points.isEmpty()) {
            return null;
        }

        // Choose splitting axis based on depth
        int axis = depth % k;

        // Sort points based on the chosen axis (x, y, etc.)
        points.sort(Comparator.comparingInt(a -> a[axis]));

        // Pick the median to make the tree balanced
        int median = points.size() / 2;

        // Create a node from the median point
        Node node = new Node(points.get(median));

        // Split the list into left and right sublists for recursive calls
        List<int[]> leftPoints = new ArrayList<>(points.subList(0, median));
        List<int[]> rightPoints = new ArrayList<>(points.subList(median + 1, points.size()));

        // Recursively build the left and right subtrees
        node.left = buildKDTree(leftPoints, depth + 1, k);
        node.right = buildKDTree(rightPoints, depth + 1, k);

        return node;
    }

    // Step 3: Function to print the K-D Tree structure clearly
    static void printKDTree(Node node, int depth) {
        if (node == null) return;

        // Print spaces proportional to depth for hierarchy visualization
        for (int i = 0; i < depth; i++) System.out.print("  ");

        // Print the current node
        System.out.println("Level " + depth + ": (" + node.point[0] + ", " + node.point[1] + ")");

        // Recursively print left and right subtrees
        printKDTree(node.left, depth + 1);
        printKDTree(node.right, depth + 1);
    }

    public static void main(String[] args) throws Exception {

        // Step 4: Example 2D points
        List<int[]> points = Arrays.asList(
            new int[]{2, 3},
            new int[]{5, 4},
            new int[]{9, 6},
            new int[]{4, 7},
            new int[]{8, 1},
            new int[]{7, 2}
        );

        // Step 5: Build the K-D Tree
        Node root = buildKDTree(points, 0, 2); // 2D space => k = 2

        // Step 6: Print the structure of the K-D Tree
        System.out.println("K-D Tree Structure:\n");
        printKDTree(root, 0);
    }
}
