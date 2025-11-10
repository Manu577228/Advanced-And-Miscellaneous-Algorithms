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

public class MeetInTheMiddle {

    // Function to generate all possible subset sums of a given list
    static List<Long> subsetSums(long[] arr) {
        List<Long> res = new ArrayList<>();
        res.add(0L); // Start with empty subset sum = 0

        // For every number, add it to all existing subset sums
        for (long x : arr) {
            List<Long> newSums = new ArrayList<>();
            for (long y : res) newSums.add(x + y); // Add current element to all previous sums
            res.addAll(newSums); // Combine with previous results
        }
        return res;
    }

    // Function implementing the Meet in the Middle algorithm
    static boolean meetInMiddle(long[] nums, long target) {
        int n = nums.length;
        long[] left = Arrays.copyOfRange(nums, 0, n / 2); // Split into left half
        long[] right = Arrays.copyOfRange(nums, n / 2, n); // Split into right half

        List<Long> leftSums = subsetSums(left);   // All subset sums of left half
        List<Long> rightSums = subsetSums(right); // All subset sums of right half
        Collections.sort(rightSums);              // Sort right half for binary search

        // Check if any pair of (sum_left, sum_right) meets the target
        for (long s : leftSums) {
            long needed = target - s;
            int idx = Collections.binarySearch(rightSums, needed); // Binary search
            if (idx >= 0) return true; // Found a valid subset pair
        }
        return false; // No subset pair found
    }

    public static void main(String[] args) throws IOException {
        // Example input
        long[] nums = {3, 34, 4, 12, 5, 2};
        long target = 9;

        // Running the function
        boolean result = meetInMiddle(nums, target);

        // Printing output
        System.out.println("Subset sum equal to target exists? " + result);
    }
}

/*
Explanation (Line-by-Line):

1. subsetSums() builds all possible sums of subsets using iterative addition.
2. Split nums into two halves for independent processing.
3. Compute leftSums and rightSums — each having 2^(n/2) sums.
4. Sort rightSums to enable fast binary search.
5. For every sum in the left half, compute needed = target - s.
6. Use Collections.binarySearch() to check if needed exists in right half.
7. If found, a valid subset combination exists → return true.
8. Otherwise, return false.

Output:
Subset sum equal to target exists? true

Output Explanation:
Subsets {4, 5} or {3, 4, 2} both sum up to 9.
The algorithm efficiently checks 2^(n/2) combinations per half instead of 2^n.
Hence, the program returns true.

Summary:
Approach: Divide & Conquer (Meet in the Middle)
Use Case: Subset sum, knapsack variants, and counting subsets.
Time Complexity: O(2^(n/2) * log(2^(n/2)))
Space Complexity: O(2^(n/2))
Key Idea: Split → Compute Subsets → Merge with Binary Search
*/
