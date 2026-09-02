/**
 * Practice Implementation: Nested Interface Contract for Event Handling Callbacks
 * Revision: #83 | Date: 2026-09-02 20:43:18
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
public class NestedInterfaceCallback {
    public interface OnClickListener {
        void onClick(String event);
    }
    public static class Button {
        private OnClickListener listener;
        public void setOnClickListener(OnClickListener l) { this.listener = l; }
        public void click() { if (listener != null) listener.onClick("Button clicked!"); }
    }
    public static void main(String[] args) {
        Button btn = new Button();
        btn.setOnClickListener(event -> System.out.println("Handled: " + event));
        btn.click();
    }
}
