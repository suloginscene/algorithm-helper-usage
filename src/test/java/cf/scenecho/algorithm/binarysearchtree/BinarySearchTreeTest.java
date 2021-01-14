package cf.scenecho.algorithm.binarysearchtree;

import com.github.suloginscene.algorithmhelper.core.binarysearchtree.BST;
import com.github.suloginscene.algorithmhelper.core.binarysearchtree.BSTProfiler;
import com.github.suloginscene.algorithmhelper.util.BSTUtil;
import com.github.suloginscene.algorithmhelper.util.Integers;
import com.github.suloginscene.algorithmhelper.util.IntegersFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class BinarySearchTreeTest {

    int n = 11;
    Integers integers;

    BST<Integer, String> bst;


    @BeforeEach
    void setup() {
        bst = new BSTProfiler<>(new BinarySearchTree());
        integers = IntegersFactory.stablyShuffled(n, true);
    }

    private void initData() {
        List<BST.Node<Integer, String>> nodes = integers.toNodeList(Integer::toBinaryString);
        BSTUtil.initWithProfiling(bst, nodes, 5000);
    }

    @AfterEach
    void print() {
        bst.printByKey();
        bst.printPaths();
    }


    @Test
    void save() {
        initData();
        assertEquals(n, bst.size());
    }

    @Test
    void save_fail() {
        initData();

        int key = 1;
        String value = Integer.toBinaryString(key);
        assertThrows(IllegalArgumentException.class, () -> bst.save(key, value));
    }


    @Test
    void find_root() {
        initData();

        Integer key = integers.getFirst();
        Object value = bst.findValue(key).orElse(null);

        assertEquals(Integer.toBinaryString(key), value);
    }

    @Test
    void find_mid() {
        initData();

        Integer key = integers.getMid();
        Object value = bst.findValue(key).orElse(null);

        assertEquals(Integer.toBinaryString(key), value);
    }

    @Test
    void find_fail() {
        initData();

        int key = n + 1;
        Object value = bst.findValue(key).orElse(null);

        assertNull(value);
    }


    @Test
    void delete_root() {
        initData();

        Integer key = integers.getFirst();
        bst.delete(key);

        String found = bst.findValue(key).orElse(null);
        assertNull(found);
        assertEquals(n - 1, bst.size());
    }

    @Test
    void delete_mid() {
        initData();

        Integer key = integers.getMid();
        bst.delete(key);

        String found = bst.findValue(key).orElse(null);
        assertNull(found);
        assertEquals(n - 1, bst.size());
    }

    @Test
    void delete_leaf() {
        initData();

        Integer key = integers.getLast();
        bst.delete(key);

        String found = bst.findValue(key).orElse(null);
        assertNull(found);
        assertEquals(n - 1, bst.size());
    }

    @Test
    void delete_fail() {
        initData();

        Integer key = n + 1;
        bst.delete(key);

        assertEquals(n, bst.size());
    }

    @Test
    void inOrder() {
        initData();

        List<Integer> inordered = new ArrayList<>();
        bst.inOrder(n -> inordered.add(n.getKey()));

        List<Integer> sorted = IntegersFactory.increasingFromOne(n).toIntegerList();
        assertEquals(sorted, inordered);
    }

}
