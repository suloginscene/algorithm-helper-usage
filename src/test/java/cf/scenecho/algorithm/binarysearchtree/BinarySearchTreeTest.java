package cf.scenecho.algorithm.binarysearchtree;

import com.github.suloginscene.algorithmhelper.util.numbergenerator.Integers;
import com.github.suloginscene.algorithmhelper.util.numbergenerator.IntegersFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class BinarySearchTreeTest {

    int n = 7;
    Integers integers;

    BinarySearchTree bst;


    @BeforeEach
    void setup() {
        bst = new BinarySearchTree();
        integers = IntegersFactory.stablyShuffled(n);
    }

    @AfterEach
    void print() {
        bst.print();
    }


    @Test
    void save() {
        for (Integer key : integers) {
            String value = Integer.toBinaryString(key);
            bst.save(key, value);
        }

        assertEquals(n, bst.size());
    }

    @Test
    void save_fail() {
        save();

        int key = 1;
        String value = Integer.toBinaryString(key);
        assertThrows(IllegalArgumentException.class, () -> bst.save(key, value));
    }


    @Test
    void find_root() {
        save();

        Integer key = integers.getFirst();
        Object value = bst.findValue(key).orElse(null);

        assertEquals(Integer.toBinaryString(key), value);
    }

    @Test
    void find_mid() {
        save();

        Integer key = integers.getMid();
        Object value = bst.findValue(key).orElse(null);

        assertEquals(Integer.toBinaryString(key), value);
    }

    @Test
    void find_fail() {
        save();

        int key = n + 1;
        Object value = bst.findValue(key).orElse(null);

        assertNull(value);
    }


    @Test
    void delete_root() {
        save();

        Integer key = integers.getFirst();
        bst.delete(key);

        String found = bst.findValue(key).orElse(null);
        assertNull(found);
        assertEquals(n - 1, bst.size());
    }

    @Test
    void delete_mid() {
        save();

        Integer key = integers.getMid();
        bst.delete(key);

        String found = bst.findValue(key).orElse(null);
        assertNull(found);
        assertEquals(n - 1, bst.size());
    }

    @Test
    void delete_leaf() {
        save();

        Integer key = integers.getLast();
        bst.delete(key);

        String found = bst.findValue(key).orElse(null);
        assertNull(found);
        assertEquals(n - 1, bst.size());
    }

    @Test
    void delete_fail() {
        save();

        Integer key = n + 1;
        bst.delete(key);

        assertEquals(n, bst.size());
    }

}
