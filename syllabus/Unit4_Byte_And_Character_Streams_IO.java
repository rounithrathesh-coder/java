/**
 * REC R2023 IT Curriculum - Unit IV: I/O and Collections
 * Topic: Byte Streams vs. Character Streams and Collection Framework Classes
 *
 * Concepts Demonstrated:
 * 1. Byte Streams (InputStream / OutputStream) for raw binary data.
 * 2. Character Streams (Reader / Writer) for Unicode text decoding.
 * 3. Buffered Streams for efficient chunked I/O.
 * 4. Collection Classes: TreeSet (Sorted unique set) and PriorityQueue (Heap).
 */

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Unit4_Byte_And_Character_Streams_IO {

    /**
     * Demonstrates Byte Stream processing using ByteArrayInputStream and BufferedInputStream.
     */
    public static int countByteOccurrences(byte[] rawData, byte target) throws IOException {
        try (InputStream byteIn = new BufferedInputStream(new ByteArrayInputStream(rawData))) {
            int currentByte;
            int count = 0;
            while ((currentByte = byteIn.read()) != -1) {
                if (currentByte == target) {
                    count++;
                }
            }
            return count;
        }
    }

    /**
     * Demonstrates Character Stream processing with Line-by-Line BufferedReader.
     */
    public static List<String> processTextStream(String text) throws IOException {
        List<String> upperCaseLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new StringReader(text))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    upperCaseLines.add(line.trim().toUpperCase());
                }
            }
        }
        return upperCaseLines;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("=======================================================================");
        System.out.println("REC R2023 IT - Unit IV: Byte vs. Character Streams & Collections");
        System.out.println("=======================================================================");

        // Test 1: Byte Streams (Binary I/O)
        System.out.println("Test 1: Byte Stream processing...");
        byte[] binaryPayload = {0x10, 0x20, 0x42, 0x30, 0x42, 0x50, 0x42};
        int matches = countByteOccurrences(binaryPayload, (byte) 0x42);
        System.out.printf("  Occurrences of 0x42: %d (Expected: 3)%n", matches);
        assert matches == 3 : "Byte stream matching error!";

        // Test 2: Character Streams (Unicode Text Processing)
        System.out.println("\nTest 2: Character Stream processing...");
        String sampleText = "java streams\n\nio architecture\ncollections framework\n";
        List<String> processed = processTextStream(sampleText);
        System.out.println("  Processed lines: " + processed);
        assert processed.size() == 3 : "Should process 3 non-empty lines!";
        assert "JAVA STREAMS".equals(processed.get(0)) : "First line transformation error!";

        // Test 3: Collection Classes - TreeSet and PriorityQueue
        System.out.println("\nTest 3: Collection classes (TreeSet & PriorityQueue)...");
        // TreeSet orders elements naturally (Red-Black tree)
        Set<Integer> sortedSet = new TreeSet<>(List.of(42, 10, 88, 5, 23));
        List<Integer> fromSet = new ArrayList<>(sortedSet);
        assert List.of(5, 10, 23, 42, 88).equals(fromSet) : "TreeSet must maintain sorted order!";

        // PriorityQueue (Min-Heap)
        Queue<Integer> minHeap = new PriorityQueue<>(List.of(42, 10, 88, 5, 23));
        assert minHeap.poll() == 5 : "PriorityQueue min-heap root must be smallest element!";
        assert minHeap.poll() == 10 : "Next poll must be second smallest!";

        System.out.println("\nAll Unit IV assertions passed successfully!");
    }
}
