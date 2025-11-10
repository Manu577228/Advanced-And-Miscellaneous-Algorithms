# The Meet in the Middle algorithm is a divide-and-conquer 
# strategy used to solve problems by splitting the input into two halves, solving each half independently, and then combining the results efficiently.
# It’s especially useful when brute-force takes too long — 
# typically reducing O(2ⁿ) complexity to O(2ⁿ/²).

# Explanation

# When dealing with problems like subset sums or combinations 
# where we must try all possibilities, directly generating all 
# subsets of n elements would take 2^n time.
# Instead, we:

# Divide the array into two halves.

# Compute all subset sums of both halves.

# Sort one half and use binary search / two pointers to find valid combinations 
# that meet the required condition (like target sum).

# Thus, the complexity improves from O(2ⁿ) → O(2 × 2ⁿ/² × log(2ⁿ/²)), which is manageable for n ≈ 40.

# Example Problem: Subset Sum using Meet in the Middle

# Problem Statement:
# Given an array of integers and a target sum, find if 
# there exists a subset whose sum equals the target.

from bisect import bisect_left

def subset_sums(arr):
    res = [0]
    for x in arr:
        new_sums = [x + y for y in res]
        res += new_sums
    return res

def meet_in_the_middle(nums, target):
    n = len(nums)
    left = nums[:n//2:]
    right = nums[n//2:]

    left_sums = subset_sums(left)
    right_sums = subset_sums(right)
    right_sums.sort()

    for s in left_sums:
        needed = target - s
        idx = bisect_left(right_sums, needed)
        if idx < len(right_sums) and right_sums[idx] == needed:
            return True
    return False

nums = [3, 34, 4, 12, 5, 2]
target = 9

result = meet_in_the_middle(nums, target)

print("Subset sum equal to target exists?", result)


