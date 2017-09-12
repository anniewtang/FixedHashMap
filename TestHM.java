import static org.junit.Assert.*;
import org.junit.Test;

//constructor(size): return an instance of the class with pre-allocated space for the given number of objects.
//boolean set(key, value): stores the given key/value pair in the hash map. Returns a boolean value indicating success / failure of the operation.
//get(key): return the value associated with the given key, or null if no value is set.
//delete(key): delete the value associated with the given key, returning the value on success or null if the key has no value.
//float load(): return a float value representing the load factor (`(items in hash map)/(size of hash map)`) of the data structure. Since the size of the dat structure is fixed, this should never be greater than 1.

public class TestHM {
    public FixedHashMap testInt= new FixedHashMap(3);
    public FixedHashMap testStr = new FixedHashMap(10);

    public void setUp() {
        assertEquals(testInt.set("First", 1), true);
        assertEquals(testInt.set("Second", 2), true);
        assertEquals(testInt.set("Third", 3), true);
        assertEquals(testInt.set("Fourth", 3), false);
        assertEquals(testStr.set("01", "serendipity"), true);
        assertEquals(testStr.set("02", "courage"), true);
        assertEquals(testStr.set("03", "laundry"), true);
    }

    @Test
    public void testGet() {
        setUp();
        assertEquals(testInt.get("First"), 1);
        assertEquals(testInt.get("Second"), 2);
        assertEquals(testInt.get("Third"), 3);
        assertEquals(testInt.get("Fourth"), null);
        assertEquals(testStr.get("03"), "laundry");

    }

    @Test
    public void testDelete() {
        setUp();
        assertEquals(testStr.load(), 0.3, 1);
        assertEquals(testInt.load(), 1, 0);
        assertEquals(testInt.delete("First"), 1);
        assertEquals(testInt.delete("Second"), 2);
        assertEquals(testInt.delete("Fourth"), null);
        assertEquals(testStr.delete("03"), "laundry");
        assertEquals(testStr.delete("02"), "courage");
    }


    public static void main(String[] args) {
    }
}
