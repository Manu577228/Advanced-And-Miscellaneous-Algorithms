# A Treap (Tree + Heap) is a randomized balanced binary search tree (BST) that combines
# the properties of both a Binary Search Tree (BST) and a Heap.
# Each node has a key (satisfying BST property) and a priority
# (satisfying Heap property), ensuring balance through randomization.

# Explanation

# A Treap maintains two conditions:

# BST Property:
# For every node, all keys in the left subtree are smaller, and all keys in the right subtree are larger.

# Heap Property:
# For every node, its priority is higher (or lower, depending on convention) than that of its children.

# When inserting:

# Insert the node as in a normal BST by key.

# Then rotate to fix the heap property if violated.

# When deleting:

# Rotate down the node to be deleted until it becomes a leaf, then remove it.

# Because priorities are assigned randomly, Treap remains balanced on average
# with an expected height of O(log n).

import random


class Node:
    def __init__(self, key):
        self.key = key
        self.priority = random.randint(1, 100)
        self.left = None
        self.right = None


def rotate_right(y):
    x = y.left
    T = x.right
    x.right = y
    y.left = T
    return x


def rotate_left(x):
    y = x.right
    T = y.left
    y.left = x
    x.right = T
    return y


def insert(root, key):
    if root is None:
        return Node(key)

    if key < root.key:
        root.left = insert(root.left, key)
        if root.left.priority > root.priority:
            root = rotate_right(root)

    elif key > root.key:
        root.right = insert(root.right, key)
        if root.right.priority > root.priority:
            root = rotate_left(root)

    return root


def search(root, key):
    if root is None:
        return False
    if root.key == key:
        return True
    elif key < root.key:
        return search(root.left, key)
    else:
        return search(root.right, key)


def inorder(root):
    if root:
        inorder(root.left)
        print(f"(key = {root.key}, Priority={root.priority})", end=" ")
        inorder(root.right)


root = None
# Insert keys into Treap
for k in [50, 30, 20, 40, 70, 60, 80]:
    root = insert(root, k)

print("Inorder traversal of Treap (Key, Priority):")
inorder(root)
print("\nSearch for 40:", search(root, 40))
print("Search for 90:", search(root, 90))
