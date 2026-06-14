/**
 * Practice Implementation: Consistent Override of Equals, HashCode, and ToString
 * Summer Revision: #20 | Date: 2026-06-14 10:15:07
 * Author: Rounith Rathesh <rounithrathesh@gmail.com>
 */
import java.util.Objects;
public class ObjectContractEqualsHashCode {
    private final int id;
    private final String code;
    public ObjectContractEqualsHashCode(int id, String code) { this.id = id; this.code = code; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ObjectContractEqualsHashCode)) return false;
        ObjectContractEqualsHashCode that = (ObjectContractEqualsHashCode) o;
        return id == that.id && Objects.equals(code, that.code);
    }
    @Override
    public int hashCode() { return Objects.hash(id, code); }
    @Override
    public String toString() { return "Item(id=" + id + ", code=" + code + ")"; }
    public static void main(String[] args) {
        ObjectContractEqualsHashCode a = new ObjectContractEqualsHashCode(1, "A");
        ObjectContractEqualsHashCode b = new ObjectContractEqualsHashCode(1, "A");
        System.out.println("Equals: " + a.equals(b) + ", HashMatch: " + (a.hashCode() == b.hashCode()));
    }
}
