/**
 * Practice Implementation: Binary Search Tree Insertion and In-Order Traversal
 * Revision: #75 | Date: 2026-09-01 08:05:44
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
public class BinarySearchTreeOps {
    static class Node { int key; Node left, right; Node(int k) { key = k; } }
    Node root;
    public void insert(int k) { root = insertRec(root, k); }
    private Node insertRec(Node root, int k) {
        if (root == null) return new Node(k);
        if (k < root.key) root.left = insertRec(root.left, k);
        else if (k > root.key) root.right = insertRec(root.right, k);
        return root;
    }
    public static void main(String[] args) {
        BinarySearchTreeOps bst = new BinarySearchTreeOps();
        bst.insert(50); bst.insert(25); bst.insert(75);
        System.out.println("Root: " + bst.root.key);
    }
}
