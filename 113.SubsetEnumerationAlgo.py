# Subset Enumeration Algorithm is a fundamental combinatorial 
# technique used to generate all possible subsets of a given set or list.
# It helps explore every combination of elements — 
# essential in brute-force, backtracking, and power set problems.

# Explanation

# If a set contains n elements, it has exactly 2ⁿ subsets — 
# including the empty set and the set itself.
# We can generate these subsets using two main approaches:

# Bitmasking approach — Represent inclusion/exclusion of each element as bits (0 or 1).

# Recursive/backtracking approach — Include or exclude each element recursively.

# Below, we’ll use bitmasking, as it’s efficient and easy to visualize computationally.

def generate_subsets(arr):
    n = len(arr)
    
    total_subsets = 1 << n

    result = []

    for mask in range(total_subsets):
        subset = []

        for i in range(n):
            if mask & (1 << i):
                subset.append(arr[i])

        result.append(subset)

    return result

arr = [1, 2, 3]

subsets = generate_subsets(arr)

for s in subsets:
    print(s)

    

