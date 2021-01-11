package cf.scenecho.algorithm.impl.binarytree.bst;

import com.github.suloginscene.algorithm.helper.integers.Integers;
import com.github.suloginscene.algorithm.helper.integers.IntegersFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class BinarySearchTreeTest {

    BinarySearchTree bst;

    int n = 15;
    Integers integers;


    @BeforeEach
    void setup() {
        bst = new BinarySearchTree();
        integers = IntegersFactory.stablyShuffled(n);
    }

    @AfterEach
    void print() {
        integers.print();
        bst.print();
        bst.validateBst();
    }


    @Test
    void save() {
        for (Integer key : integers) {
            String value = Integer.toBinaryString(key);
            Node node = new Node(key, value);
            bst.save(node);
        }

        assertEquals(n, bst.size());
    }

    @Test
    void save_fail() {
        save();

        int key = 1;
        String value = Integer.toBinaryString(key);
        Node node = new Node(key, value);
        assertThrows(IllegalArgumentException.class, () -> bst.save(node));
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

        Node found = bst.findNode(key).orElse(null);
        assertNull(found);
        assertEquals(n - 1, bst.size());
    }

    @Test
    void delete_mid() {
        save();

        Integer key = integers.getMid();
        System.out.println(key);
        bst.delete(key);

        Node found = bst.findNode(key).orElse(null);
        assertNull(found);
        assertEquals(n - 1, bst.size());
    }

    @Test
    void delete_leaf() {
        save();

        Integer key = integers.getLast();
        bst.delete(key);

        Node found = bst.findNode(key).orElse(null);
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
