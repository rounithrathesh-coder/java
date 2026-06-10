/**
 * Practice Implementation: Dynamic Resizable Array with Capacity Doubling
 * Summer Revision: #14 | Date: 2026-06-10 20:30:16
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
public class CustomArrayList {
    private int[] data;
    private int size;
    public CustomArrayList() { data = new int[4]; size = 0; }
    public void add(int val) {
        if (size == data.length) resize();
        data[size++] = val;
    }
    public int get(int idx) {
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
        return data[idx];
    }
    public int size() { return size; }
    private void resize() {
        int[] next = new int[data.length * 2];
        System.arraycopy(data, 0, next, 0, data.length);
        data = next;
    }
    public static void main(String[] args) {
        CustomArrayList list = new CustomArrayList();
        list.add(10); list.add(20); list.add(30);
        System.out.println("Size: " + list.size() + ", Element 1: " + list.get(1));
    }
}
