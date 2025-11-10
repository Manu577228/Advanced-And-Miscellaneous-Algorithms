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
import java.util.*;

public class SubsetEnumeration {

    // Function to generate all subsets using bitmasking
    static List<List<Integer>> generateSubsets(int[] arr) {
        // Get number of elements in the array
        int n = arr.length;

        // Calculate total number of subsets = 2^n
        int totalSubsets = 1 << n;  // same as Math.pow(2, n)

        // Initialize list to store all subsets
        List<List<Integer>> result = new ArrayList<>();

        // Loop through all possible bitmasks
        for (int mask = 0; mask < totalSubsets; mask++) {

            // Temporary list for current subset
            List<Integer> subset = new ArrayList<>();

            // For each bit in the bitmask
            for (int i = 0; i < n; i++) {

                // Check if i-th bit is set (means include arr[i])
                if ((mask & (1 << i)) != 0) {
                    subset.add(arr[i]);
                }
            }

            // Add this subset to result list
            result.add(subset);
        }

        // Return all subsets
        return result;
    }

    public static void main(String[] args) throws IOException {

        // Using BufferedReader for fast input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Example input array
        int[] arr = {1, 2, 3};

        // Generate all subsets
        List<List<Integer>> subsets = generateSubsets(arr);

        // Print each subset
        for (List<Integer> s : subsets) {
            System.out.println(s);
        }
    }
}

/*
---------------- Line-by-Line Explanation ----------------

1. int n = arr.length; → Finds the number of elements in the array.
2. int totalSubsets = 1 << n; → Calculates total subsets (2^n) using left shift.
3. for (int mask = 0; mask < totalSubsets; mask++): Iterates through all possible binary masks.
4. for (int i = 0; i < n; i++): Loops through each bit of the mask.
5. if ((mask & (1 << i)) != 0): Checks if the i-th bit is set → includes arr[i].
6. subset.add(arr[i]); → Adds the element into the current subset.
7. result.add(subset); → Appends this subset to the final result list.
8. Prints all subsets line by line.

---------------- Output ----------------
[]
[1]
[2]
[1, 2]
[3]
[1, 3]
[2, 3]
[1, 2, 3]

---------------- Output Explanation ----------------
Each subset corresponds to a binary bitmask representation:
000 → []
001 → [1]
010 → [2]
011 → [1, 2]
100 → [3]
101 → [1, 3]
110 → [2, 3]
111 → [1, 2, 3]
*/
