/**
 * Practice Implementation: Binary Search Tree Insertion and In-Order Traversal
 * Revision: #231 | Date: 2026-10-29 21:55:30
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
