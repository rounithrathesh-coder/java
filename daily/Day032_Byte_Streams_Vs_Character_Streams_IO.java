/**
 * Daily Java Challenge - Day 032 (2026-11-22)
 * Topic: REC Unit IV - Byte Streams vs Character Streams (Buffered I/O)
 * Category: I/O and Collections
 * Curriculum: REC R2023 IT - Unit IV (I/O and Collections)
 * 
 * Description:
 * Demonstrates the architectural difference between Byte Streams (InputStream/OutputStream)
 * and Character Streams (Reader/Writer), buffer sizing, and UTF-8 charset decoding.
 */
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Day032_Byte_Streams_Vs_Character_Streams_IO {

    // 1. Byte Stream Processing: Raw 8-bit binary data
    public static byte[] processByteStream(byte[] rawData) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
             BufferedInputStream bis = new BufferedInputStream(bais)) {

            byte[] buffer = new byte[8];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        }
    }

    // 2. Character Stream Processing: 16-bit Unicode characters with Charset
    public static String processCharacterStream(byte[] rawData) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(rawData);
             InputStreamReader isr = new InputStreamReader(bais, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (sb.length() > 0) sb.append("\n");
                sb.append(line.trim());
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("=== REC Unit IV: Byte Streams vs Character Streams ===");

        String sampleText = "Rajalakshmi Engineering College (REC)\nR2023 IT Syllabus: Java I/O Streams 🚀";
        byte[] inputBytes = sampleText.getBytes(StandardCharsets.UTF_8);

        // Verify Byte Stream
        byte[] byteOutput = processByteStream(inputBytes);
        System.out.println("Byte Stream Bytes Transferred: " + byteOutput.length + " bytes");
        assert Arrays.equals(inputBytes, byteOutput) : "Byte stream data corrupted!";

        // Verify Character Stream
        String charOutput = processCharacterStream(inputBytes);
        System.out.println("Character Stream Decoded Output:\n" + charOutput);
        assert charOutput.contains("REC") && charOutput.contains("🚀") : "Character stream decoding failed!";

        System.out.println("\nAll I/O Stream validations PASSED successfully!");
    }
}
