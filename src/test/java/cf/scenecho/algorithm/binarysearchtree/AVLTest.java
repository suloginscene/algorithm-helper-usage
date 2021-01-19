package cf.scenecho.algorithm.binarysearchtree;

import com.github.suloginscene.algorithmhelper.core.binarysearchtree.BinarySearchTreeProfiler;
import com.github.suloginscene.algorithmhelper.core.binarysearchtree.BinarySearchTree;
import com.github.suloginscene.algorithmhelper.core.binarysearchtree.Node;
import com.github.suloginscene.algorithmhelper.util.BinarySearchTreeUtil;
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


class AVLTest {

    int n = 27;
    Integers integers;

    BinarySearchTree<Integer, String> bst;

    @BeforeEach
    void setup() {
        bst = new BinarySearchTreeProfiler<>(new AVL());
        integers = IntegersFactory.stablyShuffled(n, true);
    }

    private void initData() {
        List<AVL.N> nodes = integers.toTypeList(integer -> new AVL.N(integer, Integer.toHexString(integer)));
        BinarySearchTreeUtil.saveDataLoggingProgress(bst, nodes, 2000);
    }

    @AfterEach
    void print() {
        bst.print(Node::toString);
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

        assertEquals(Integer.toHexString(key), value);
    }

    @Test
    void find_mid() {
        initData();

        Integer key = integers.getMid();
        Object value = bst.findValue(key).orElse(null);

        assertEquals(Integer.toHexString(key), value);
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