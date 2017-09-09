import static org.junit.Assert.*;
import org.junit.Test;

//constructor(size): return an instance of the class with pre-allocated space for the given number of objects.
//boolean set(key, value): stores the given key/value pair in the hash map. Returns a boolean value indicating success / failure of the operation.
//get(key): return the value associated with the given key, or null if no value is set.
//delete(key): delete the value associated with the given key, returning the value on success or null if the key has no value.
//float load(): return a float value representing the load factor (`(items in hash map)/(size of hash map)`) of the data structure. Since the size of the dat structure is fixed, this should never be greater than 1.

public class TestHM {
    public FixedHashMap<Integer> testInt= new FixedHashMap<>(3);
    public FixedHashMap<String> testStr = new FixedHashMap<>(10);

    public void setUp() {
        testInt.set("First", 1);
        testInt.set("Second", 2);
        testInt.set("Third", 3);
        testInt.set("Fourth", 3);
        testStr.set("01", "serendipity");
        testStr.set("02", "courage");
        testStr.set("03", "laundry");
    }

    @Test
    public void testGet() {
        setUp();
        System.out.println(testInt.get("First"));
        System.out.println(testInt.get("Second"));
        System.out.println(testInt.get("Third"));
        System.out.println(testInt.get("Fourth"));
        System.out.println(testStr.get("03"));

    }

    @Test
    public void testDelete() {
        setUp();
        System.out.println(testStr.load());
        System.out.println(testInt.load());
        System.out.println(testInt.delete("First"));
        System.out.println(testInt.delete("Second"));
        System.out.println(testInt.delete("Fourth"));
        System.out.println(testStr.delete("03"));
        System.out.println(testStr.delete("02"));
    }


    public static void main(String[] args) {
    }
}
