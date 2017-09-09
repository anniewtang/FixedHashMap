public class FixedHashMap<T> {

    private int capacity;
    private int fill;
    private int load;
    private Pair[] arr;

    private class Pair {
        String key;
        T value;
        Pair next;
        Pair prev;

        private Pair(String k, T v) {
            this.key = k;
            this.value = v;
        }

        private Pair(String k, T v, Pair n) {
            this(k, v);
            this.next = n;
        }

       /* private method to abstract away reassignment/value-deletion */
        private void reassign() {
            if (this.next != null) {
                this.next = this.next.next;
            }
        }

    }

    // return an instance of the class with pre-allocated space for the given number of objects.
    public FixedHashMap(int size) {
        this.capacity = size;
        arr = (Pair[]) new Object[capacity];
//        arr = new Pair<T>[capacity];
        fill = 0;
        updateLoad();
    }

    /** set()
     * stores the given key/value pair in the hash map.
     * returns a boolean value indicating success / failure of the operation.
     * also updates fill count & load if insertion was successful **/
    public boolean set(String key, T value) {
        if (isFull()) {
            return false;
        }
        int index = hashIndex(key);
        insertPair(key, value, index);
        this.fill++;
        updateLoad();
        return true;
    }

    /** return the value associated with the given key, or null if no value is set. **/
    public T get(String key) {
        Pair p = findPair(key);
        if (p != null) {
            return p.value;
        }
        return null;
    }

    /** delete()
     *  > delete the value associated with the given key,
     * >  returning the value on success or null if the key has no value. **/
    public T delete(String key) {
        Pair p = findPair(key);
        if (p != null) {
            return deletePair(p);
        }
        return null;
    }

    /** return a float value representing load factor of data structure
     *  > items in hash map / size of hash map
     *  > if size of structure is fixed, load needs to be <= 1 at all times
     */
    public float load() {
        return this.load;
    }


    /* --------- PRIVATE HELPER METHODS --------- */
    /** updateLoad()
     *  > maintains an updated 'load' field to make load() faster
     *  > no need re-calculate 'load' each time load() is called
     */
    private void updateLoad() {
        this.load = this.fill / this.capacity;
    }

    /** isFull()
     *  boolean helper to determine if the data structure is at its full capacity
     */
    private boolean isFull() {
        return this.capacity == this.fill;
    }

    /** hashIndex
     *  > calculates the index in the array to put the Pair
     */
    private int hashIndex(String key) {
        int stringHash = key.hashCode();
        return (stringHash & 0x7FFFFF) % this.capacity;
    }

    /** private helper: insertPair
     * 1. checks if bucket is empty, if so inserts a new Pair
     * 2. if bucket is not empty (contains other pairs), iterate through Pairs
     *    > if we find a Pair with the same key, update that Pair
     *    > if we don't find a Pair with the same key, add that Pair to the "front" of the "linked Pairs"
     *      > update the now-second Pair.prev in the bucket to point to the new Pair
     */
    private void insertPair(String k, T v, int i) {
        Pair p = arr[i];
        if (p == null) {
            arr[i] = new Pair(k, v);
            return;
        }
        while (p != null) {
            if (p.key.equals(k)) {
                p.value = v;
                return;
            }
            p = p.next;
        }
        Pair unique = new Pair(k, v);
        Pair first = arr[i];
        unique.next = first;
        first.prev = unique;
        arr[i] = unique;
    }

    /** deletePair
     *  > will always return SOME value
     *  > this is only called in the context of p being a Pair that matches with given key
     *  1. checks if Pair p is the first pair in the bucket
     *     > if so, sets bucket content to be p.next
     *     > if not, readjust pointers so p is excluded
     *  2. return p.value
     */
    private T deletePair(Pair p) {
        Pair prev = p.prev;
        Pair next = p.next;
        T value = p.value;

        if (prev == null) {
            int index = hashIndex(p.key);
            arr[index] = next;
            next.prev = null;
        } else {
            prev.next = next;
            next.prev = prev;
        }
        return value;

    }

    /** helper method: findPair
     *  used for get and delete
     *  1. computes bucket index
     *  2. iterates through bucket to find the Pair with the matching key
     *  3. if found, return the Pair. otherwise, return null.
     */
    private Pair findPair(String k) {
        int index = hashIndex(k);
        Pair p = arr[index];
        while (p != null) {
            if (p.key.equals(k)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    public static void main(String[] args) {
    }

}