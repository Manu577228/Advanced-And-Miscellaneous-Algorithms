# MO’s Algorithm is a square-root decomposition technique 
# used to efficiently answer multiple range queries (like sum, frequency, etc.) on a static array.
# It reduces query complexity from O(Q × N) to approximately O((N + Q) × √N).

# Explanation

# Suppose we have N elements and Q range queries (each query asks for some result on subarray [L, R]).

# A brute force approach recalculates the result for every query → O(N × Q).

# MO’s Algorithm reorders the queries smartly using √N block sorting, 
# so consecutive queries have overlapping ranges.

# We adjust our range by adding or removing elements at the edges to compute each query efficiently.

# Steps:

# Divide array indices into blocks of size √N.

# Sort queries:

# First by block number of L.

# Then by R value if both belong to the same block.

# Maintain current range [currL, currR] and current answer.

# For each query:

# Expand or shrink the range.

# Update answer accordingly.

# Example Problem

# Problem:
# Given an array, answer Q queries where each query asks for the sum of elements between indices L and R.

import math

def MO_algorithm(arr, queries):
    n = len(arr)
    q = len(queries)
    block_size = int(math.sqrt(n))

    queries.sort(key = lambda x: (x[0] // block_size, x[1]))

    currL, currR, currSum = 0, 0, 0
    res = [0] * q

    for i in range(q):
        L, R = queries[i]

        while currL < L:
            currSum -= arr[currL]
            currL += 1

        while currL > L:
            currL -= 1
            currSum += arr[currL]

        while currR <= R:
            currSum += arr[currR]
            currR += 1

        while currR > R + 1:
            currR -= 1
            currSum -= arr[currR]

        res[i] = currSum

    return res

# Example Input
arr = [1, 2, 3, 4, 5, 6]
queries = [(0, 2), (1, 3), (2, 5), (0, 5)]

# Run MO’s Algorithm
answers = MO_algorithm(arr, queries)

# Print output
for i, ans in enumerate(answers):
    print(f"Query {i+1}: Sum = {ans}")


    