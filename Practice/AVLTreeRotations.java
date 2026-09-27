/**
 * Practice Implementation: Self-Balancing AVL Tree Rotations and Height Balance
 * Revision: #147 | Date: 2026-09-27 08:15:19
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
public class AVLTreeRotations {
    static class Node { int key, height; Node left, right; Node(int d) { key = d; height = 1; } }
    static int height(Node n) { return n == null ? 0 : n.height; }
    static int getBalance(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }
    static Node rightRotate(Node y) {
        Node x = y.left; Node t2 = x.right;
        x.right = y; y.left = t2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }
    public static void main(String[] args) {
        Node root = new Node(30); root.left = new Node(20); root.left.left = new Node(10);
        root = rightRotate(root);
        System.out.println("Balanced root: " + root.key);
    }
}
