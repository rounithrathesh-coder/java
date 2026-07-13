/**
 * Practice Implementation: Comparison of Byte Stream and Character Stream IO
 * Revision: #35 | Date: 2026-07-13 17:23:25
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
import java.io.*;
public class ByteVsCharStreamDemo {
    public static void main(String[] args) throws IOException {
        byte[] raw = "Sample binary stream bytes".getBytes();
        try (ByteArrayInputStream bis = new ByteArrayInputStream(raw)) {
            int b;
            while ((b = bis.read()) != -1) {
                // Read byte-by-byte
            }
        }
        try (StringReader sr = new StringReader("Sample character reader text")) {
            int c;
            while ((c = sr.read()) != -1) {
                // Read char-by-char
            }
        }
        System.out.println("Stream IO completed.");
    }
}
