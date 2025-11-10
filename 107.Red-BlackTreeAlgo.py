# A Red-Black Tree is a self-balancing binary search tree where 
# each node contains an extra bit for color (either red or black).
# It ensures that the tree remains approximately balanced during insertions and deletions, keeping 
# operations like search, insert, and delete in O(log n) time.

# Explanation

# A Red-Black Tree maintains the following five key properties:

# Every node is either red or black.

# The root is always black.

# All leaves (NIL nodes) are black.

# If a node is red, then both its children are black (no two reds in a row).

# Every path from a node to its descendant NIL nodes has the 
# same number of black nodes (called the black-height).

# These properties guarantee that the longest path is at most 
# twice as long as the shortest one — keeping the tree balanced.

RED = "RED"
BLACK = "BLACK"


class Node:
    def __init__(self, data):
        self.data = data   
        self.color = RED         
        self.left = None      
        self.right = None       
        self.parent = None      


class RedBlackTree:
    def __init__(self):
        self.NIL = Node(0)         
        self.NIL.color = BLACK
        self.root = self.NIL      

    def left_rotate(self, x):
        y = x.right
        x.right = y.left
        if y.left != self.NIL:
            y.left.parent = x
        y.parent = x.parent
        if x.parent == None:
            self.root = y
        elif x == x.parent.left:
            x.parent.left = y
        else:
            x.parent.right = y
        y.left = x
        x.parent = y


    def right_rotate(self, y):
        x = y.left
        y.left = x.right
        if x.right != self.NIL:
            x.right.parent = y
        x.parent = y.parent
        if y.parent == None:
            self.root = x
        elif y == y.parent.right:
            y.parent.right = x
        else:
            y.parent.left = x
        x.right = y
        y.parent = x


    def fix_insert(self, k):
        while k.parent and k.parent.color == RED:
            if k.parent == k.parent.parent.left:
                u = k.parent.parent.right  # Uncle
                if u.color == RED:  # Case 1: Uncle is RED
                    k.parent.color = BLACK
                    u.color = BLACK
                    k.parent.parent.color = RED
                    k = k.parent.parent
                else:
                    if k == k.parent.right: 
                        k = k.parent
                        self.left_rotate(k)
                    # Case 3: Node is left child
                    k.parent.color = BLACK
                    k.parent.parent.color = RED
                    self.right_rotate(k.parent.parent)
            else:
                u = k.parent.parent.left
                if u.color == RED:
                    k.parent.color = BLACK
                    u.color = BLACK
                    k.parent.parent.color = RED
                    k = k.parent.parent
                else:
                    if k == k.parent.left:
                        k = k.parent
                        self.right_rotate(k)
                    k.parent.color = BLACK
                    k.parent.parent.color = RED
                    self.left_rotate(k.parent.parent)
        self.root.color = BLACK  


    def insert(self, key):
        node = Node(key)
        node.parent = None
        node.left = self.NIL
        node.right = self.NIL

        y = None
        x = self.root

        while x != self.NIL:
            y = x
            if node.data < x.data:
                x = x.left
            else:
                x = x.right

        node.parent = y
        if y == None:
            self.root = node
        elif node.data < y.data:
            y.left = node
        else:
            y.right = node

        if node.parent == None:
            node.color = BLACK
            return

        if node.parent.parent == None:
            return

        self.fix_insert(node)


    def inorder(self, node):
        if node != self.NIL:
            self.inorder(node.left)
            print(f"{node.data}({node.color})", end=" ")
            self.inorder(node.right)


if __name__ == "__main__":
    rbt = RedBlackTree()
    elements = [10, 20, 30, 15, 25]
    for e in elements:
        rbt.insert(e)

    print("Inorder Traversal (value(color)):")
    rbt.inorder(rbt.root)
