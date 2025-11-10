# An AVL Tree is a self-balancing Binary Search Tree (BST) where 
# the height difference (balance factor) between the left and right subtrees 
# of every node is at most 1.
# This ensures that search, insert, and delete operations always take O(log n) time.

# Explanation

# In a Binary Search Tree (BST), inserting nodes in sorted order 
# makes the tree skewed and degrades performance to O(n).

# An AVL Tree fixes this by rotating nodes whenever the balance 
# factor becomes less than -1 or greater than +1 after insertion or deletion.

# There are four types of rotations used to restore balance:

# Right Rotation (LL Rotation)

# Left Rotation (RR Rotation)

# Left-Right Rotation (LR Rotation)

# Right-Left Rotation (RL Rotation)

# Each rotation ensures that the height of the tree remains balanced, 
# preserving the logarithmic time complexity for operations.

class Node:
    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None
        self.height = 1

class AVLTree:
    def getHeight(self, root):
        if not root:
            return 0
        return root.height
    
    def getBalance(self, root):
        if not root:
            return 0
        return self.getHeight(root.left) - self.getHeight(root.right)
    
    def rightRotate(self, y):
        x = y.left
        T2 = x.right
        x.right = y
        y.left = T2
        y.height = 1 + max(self.getHeight(y.left), self.getHeight(y.right))
        x.height = 1 + max(self.getHeight(x.left), self.getHeight(x.right))
        return x
    
    def leftRotate(self, x):
        y = x.right
        T2 = y.left
        y.left = x
        x.right = T2
        x.height = 1 + max(self.getHeight(x.left), self.getHeight(x.right))
        y.height = 1 + max(self.getHeight(y.left), self.getHeight(y.right))
        return y
    
    def insert(self, root, key):
        if not root:
            return Node(key)
        elif key < root.key:
            root.left = self.insert(root.left, key)
        else:
            root.right = self.insert(root.right, key)

        root.height = 1 + max(self.getHeight(root.left), self.getHeight(root.right))

        balance = self.getBalance(root)

        if balance > 1 and key < root.left.key:
            return self.rightRotate(root)
        
        if balance < -1 and key > root.right.key:
            return self.leftRotate(root)
        
        if balance > 1 and key > root.left.key:
            root.left = self.leftRotate(root.left)
            return self.rightRotate(root)
        
        if balance < -1 and key < root.right.key:
            root.right = self.rightRotate(root.right)
            return self.leftRotate(root)
        
        return root
    
    def inorder(self, root):
        if not root:
            return
        self.inorder(root.left)
        print(root.key, end = " ")
        self.inorder(root.right)

tree = AVLTree()
root = None
nums = [10, 20, 30, 40, 50, 25]

for num in nums:
    root = tree.insert(root, num)

print("Inorder Traversal of the constructed AVL tree is:")
tree.inorder(root)
print()
        