# A K-D Tree (k-dimensional tree) is a space-partitioning data structure
# used to organize points in a k-dimensional space.
# It helps efficiently perform operations like nearest neighbor search,
# range search, and clustering in multidimensional datasets.

# Explanation

# A K-D Tree is a binary tree where each node represents a point in k-dimensional space.

# The splitting dimension alternates at each level (e.g., x, y, x, y for 2D).

# During insertion, at each level, we compare the point’s coordinate
# in the current dimension to decide whether to go left or right.

# This structure divides the space recursively — each node acts as
# a boundary that splits the dataset into subregions.

# K-D Trees are especially useful in applications like computer vision,
# machine learning (KNN), and geospatial indexing.

class Node:
    def __init__(self, point, left=None, right=None):
        self.point = point
        self.left = left
        self.right = right


def build_kd_tree(points, depth=0):
    if not points:
        return None

    k = len(points[0])
    axis = depth % k

    points.sort(key=lambda x: x[axis])

    median = len(points) // 2

    return Node(
        point=points[median],
        left=build_kd_tree(points[:median], depth + 1),
        right=build_kd_tree(points[median + 1:], depth + 1)
    )

def print_kd_tree(node, depth = 0):
    if not node:
        return
    print(" " * depth + f"Level {depth}: {node.point}")
    print_kd_tree(node.left, depth + 1)
    print_kd_tree(node.right, depth + 1)


points = [(2, 3), (5, 4), (9, 6), (4, 7), (8, 1), (7, 2)]

root = build_kd_tree(points)

print("K-D Tree Structure:\n")
print_kd_tree(root)

