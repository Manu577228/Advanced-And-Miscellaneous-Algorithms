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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

public class BloomFilter {
    
    private int size;             // Total size of the bit array
    private int hashCount;        // Number of hash functions
    private BitSet bitArray;      // Java BitSet to store bits efficiently

    // Constructor: Initialize Bloom Filter with given size and number of hash functions
    public BloomFilter(int size, int hashCount) {
        this.size = size;                         // Set size of bit array
        this.hashCount = hashCount;               // Set number of hash functions
        this.bitArray = new BitSet(size);         // Initialize bit array with all bits as 0
    }

    // Private helper function to compute multiple hash values for the given input string
    private int[] getHashes(String item) {
        int[] indices = new int[hashCount];       // Array to store the generated indices
        try {
            // Using SHA-256 hashing algorithm to generate hashes
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            for (int i = 0; i < hashCount; i++) {
                // Combine the string with hash index to create unique hash for each function
                String combined = item + i;
                // Compute the digest
                byte[] hashBytes = digest.digest(combined.getBytes());
                // Convert hash bytes to positive integer
                BigInteger hashInt = new BigInteger(1, hashBytes);
                // Map to a valid bit index using modulo operation
                indices[i] = hashInt.mod(BigInteger.valueOf(size)).intValue();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return indices;
    }

    // Method to add an element to the Bloom Filter
    public void add(String item) {
        int[] indices = getHashes(item);          // Generate k hash indices for the item
        for (int index : indices) {
            bitArray.set(index, true);            // Set corresponding bit positions to 1
        }
    }

    // Method to check if an element may be present in the Bloom Filter
    public boolean check(String item) {
        int[] indices = getHashes(item);          // Generate k hash indices for the item
        for (int index : indices) {
            if (!bitArray.get(index)) {           // If any bit is 0 → item definitely not present
                return false;
            }
        }
        return true;                              // All bits are 1 → item possibly present
    }

    // Main method to test the Bloom Filter
    public static void main(String[] args) {
        BloomFilter bf = new BloomFilter(50, 3);  // Create Bloom Filter of size 50 with 3 hash functions

        // Add elements to Bloom Filter
        bf.add("apple");
        bf.add("banana");
        bf.add("cherry");

        // Check membership of elements
        System.out.println("apple in filter: " + bf.check("apple"));   // Expected True
        System.out.println("banana in filter: " + bf.check("banana")); // Expected True
        System.out.println("grape in filter: " + bf.check("grape"));   // Expected False (most likely)
    }
}
