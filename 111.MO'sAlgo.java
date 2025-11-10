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

public class MosAlgorithm {

    // Query class to represent each query range
    static class Query {
        int L, R, idx;
        Query(int L, int R, int idx) {
            this.L = L;
            this.R = R;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {

        // Fast Input/Output
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        // Example array and queries
        int[] arr = {1, 2, 3, 4, 5, 6};

        // Queries in form of (L, R)
        Query[] queries = {
            new Query(0, 2, 0),
            new Query(1, 3, 1),
            new Query(2, 5, 2),
            new Query(0, 5, 3)
        };

        int n = arr.length;
        int q = queries.length;
        int blockSize = (int) Math.sqrt(n);  // Divide array into √N blocks

        // Sort queries by block of L and then by R
        Arrays.sort(queries, (a, b) -> {
            int blockA = a.L / blockSize;
            int blockB = b.L / blockSize;
            if (blockA == blockB)
                return Integer.compare(a.R, b.R);
            return Integer.compare(blockA, blockB);
        });

        int currL = 0, currR = 0, currSum = 0;
        int[] ans = new int[q];

        // Process each query one by one
        for (Query query : queries) {
            int L = query.L;
            int R = query.R;

            // Move left pointer to L
            while (currL < L) {
                currSum -= arr[currL];  // Remove element going out of range
                currL++;
            }
            while (currL > L) {
                currL--;
                currSum += arr[currL];  // Add element coming into range
            }

            // Move right pointer to R
            while (currR <= R) {
                currSum += arr[currR];  // Add element entering range
                currR++;
            }
            while (currR > R + 1) {
                currR--;
                currSum -= arr[currR];  // Remove element going out of range
            }

            // Store result for this query
            ans[query.idx] = currSum;
        }

        // Print all answers in the order of queries
        for (int i = 0; i < q; i++) {
            out.println("Query " + (i + 1) + ": Sum = " + ans[i]);
        }

        out.flush();
    }
}

/*
-------------------- LINE-BY-LINE EXPLANATION --------------------
1️⃣ Query class: stores L, R, and original query index for sorting.
2️⃣ blockSize: defines how large each block is (≈ √N).
3️⃣ Queries are sorted by block number of L, then by R.
4️⃣ currL, currR maintain current range, currSum maintains the running sum.
5️⃣ The four while-loops adjust current window to target [L, R].
6️⃣ Add or remove elements from currSum as window moves.
7️⃣ After processing all queries, print each sum.
-------------------------------------------------------------------
*/

/*
------------------------ OUTPUT ------------------------

Query 1: Sum = 6
Query 2: Sum = 9
Query 3: Sum = 18
Query 4: Sum = 21
---------------------------------------------------------
*/

/*
--------------------- OUTPUT EXPLANATION ---------------------
Query 1 → range [0,2] → [1,2,3] → sum = 6  
Query 2 → range [1,3] → [2,3,4] → sum = 9  
Query 3 → range [2,5] → [3,4,5,6] → sum = 18  
Query 4 → range [0,5] → [1,2,3,4,5,6] → sum = 21  
--------------------------------------------------------------
*/

/*
------------------------- SUMMARY -------------------------
✅ MO’s Algorithm optimizes multiple range queries.
✅ Works best for static arrays (no updates).
✅ Time Complexity: O((N + Q) * √N)
✅ Space Complexity: O(N + Q)
✅ Ideal for sum/frequency/count range problems.
------------------------------------------------------------
*/
