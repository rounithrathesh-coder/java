/**
 * Practice Implementation: Comparison of Byte Stream and Character Stream IO
 * Summer Revision: #181 | Date: 2026-08-24 14:21:39
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
