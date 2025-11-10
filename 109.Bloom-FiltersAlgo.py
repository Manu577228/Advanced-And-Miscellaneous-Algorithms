# A Bloom Filter is a probabilistic data structure that efficiently tests
# whether an element is a member of a set.
# It can tell you if an element is “definitely not in the set” or “possibly in the set”,
# but never guarantees exactness (allowing false positives, but not false negatives).

# Explanation

# A Bloom Filter uses a bit array of size m, initially all 0s.

# It uses k independent hash functions to map an
# element to k positions in the bit array.

# When inserting an element, we set those k positions to 1.

# When checking membership, if all k positions are 1, the element may
# exist, otherwise it definitely does not.

# This allows memory-efficient approximate set membership checks —
# widely used in databases, caches, and network filters.

import hashlib
import bitarray

class BloomFilter:
    def __init__(self, size, hash_count):
        self.size = size
        self.hash_count = hash_count
        self.bit_array = bitarray.bitarray(size)
        self.bit_array.setall(0)

    def _hashes(self, item):
        results = []
        for i in range(self.hash_count):
            digest = hashlib.sha256(
                (item + str(i)).encode('utf-8')).hexdigest()
            index = int(digest, 16) % self.size
            results.append(index)
        return results

    def add(self, item):
        for index in self._hashes(item):
            self.bit_array[index] = 1

    def check(self, item):
        for index in self._hashes(item):
            if self.bit_array[index] == 0:
                return False
        return True
    
bf = BloomFilter(size=50, hash_count=3)

# Step 6: Add items to the filter
bf.add("apple")
bf.add("banana")
bf.add("cherry")

# Step 7: Check for membership
print("apple in filter:", bf.check("apple"))   # Expected True
print("banana in filter:", bf.check("banana")) # Expected True
print("grape in filter:", bf.check("grape"))   # Expected False (most likely)
