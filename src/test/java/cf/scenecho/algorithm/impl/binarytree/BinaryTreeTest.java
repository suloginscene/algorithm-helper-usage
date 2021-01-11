package cf.scenecho.algorithm.impl.binarytree;

import com.github.suloginscene.algorithm.helper.integers.Integers;
import com.github.suloginscene.algorithm.helper.integers.IntegersFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class BinaryTreeTest {

    BinaryTree binaryTree;

    int n = 15;
    Integers integers;


    @BeforeEach
    void setup() {
        binaryTree = new BinaryTree();
        integers = IntegersFactory.increasingFromOne(n);
    }

    @AfterEach
    void print() {
        binaryTree.print();
    }


    @Test
    void save() {
        for (Integer i : integers) {
            Node node = new Node(i);
            binaryTree.save(node);
        }

        assertEquals(n, binaryTree.size());
    }


    @Test
    void find_root() {
        save();

        Node target = new Node(integers.getFirst());
        Node found = binaryTree.find(target).orElse(null);

        assertEquals(target, found);
    }

    @Test
    void find_mid() {
        save();

        Node target = new Node(integers.getMid());
        Node found = binaryTree.find(target).orElse(null);

        assertEquals(target, found);
    }

    @Test
    void find_fail() {
        save();

        Node target = new Node(n + 1);
        Node found = binaryTree.find(target).orElse(null);

        assertNull(found);
    }


    @Test
    void delete_root() {
        save();

        Node target = new Node(integers.getFirst());
        binaryTree.delete(target);

        Node found = binaryTree.find(target).orElse(null);
        assertNull(found);
        assertEquals(n - 1, binaryTree.size());
    }

    @Test
    void delete_mid() {
        save();

        Node target = new Node(integers.getMid());
        binaryTree.delete(target);

        Node found = binaryTree.find(target).orElse(null);
        assertNull(found);
        assertEquals(n - 1, binaryTree.size());
    }

    @Test
    void delete_leaf() {
        save();

        Node target = new Node(integers.getLast());
        binaryTree.delete(target);

        Node found = binaryTree.find(target).orElse(null);
        assertNull(found);
        assertEquals(n - 1, binaryTree.size());
    }

    @Test
    void delete_fail() {
        save();

        Node target = new Node(n + 1);
        binaryTree.delete(target);

        assertEquals(n, binaryTree.size());
    }

}